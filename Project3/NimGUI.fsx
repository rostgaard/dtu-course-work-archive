module NimGUI

open System.Windows.Forms 
open System.Drawing 

System.IO.Directory.SetCurrentDirectory __SOURCE_DIRECTORY__;;


// The window part
let matchW = 50
let matchH = 80;
let buttonW = 200
let buttonH = 100
let matchIcon = Image.FromFile("Match_Icon_small.png")
let kittenW = 570
let kittenH = 456

let updatePanels matches (matchPanel : Control) (buttonPanel : Control) (form : Form) =
    let maxMatches = List.max matches
    let totalMatchW = (maxMatches + 1) * matchW
    let totalMatchH = (maxMatches + 1) * matchH
    matchPanel.Location <- Point(0,0)
    matchPanel.Size <- Size(max (totalMatchW + buttonW) kittenW, (max totalMatchH kittenH))
    matchPanel.BackColor <- Color.Black
    buttonPanel.Location <- Point(0,matchPanel.Height)
    buttonPanel.Size <- Size(matchPanel.Width, buttonH)
    buttonPanel.BackColor <- Color.White
    form.Text <- "Nim game"
    form.Size <- Size(max (matchPanel.Width + 50) (kittenW + 50),
                        (max matchPanel.Height kittenH) + 50 + buttonPanel.Height)
    form.AutoScroll <- true;;


let matchButton (x : int) (y : int) z onClick = let btn = new Button(Location = Point(x,y), MinimumSize=Size(25,matchH),
                                                                        MaximumSize=Size(20,100),Text= z, BackColor = Color.Black, Image = matchIcon, FlatStyle = FlatStyle.Flat)
                                                btn.Click.Add (onClick)
                                                btn;;

let resetBtn (buttonPanel : Control) =
        new Button(Location=Point((buttonPanel.Width-100)/2,(buttonPanel.Height-50)/2),MinimumSize=Size(100,50),
                    MaximumSize=Size(100,50),Text="Reset Game");;


let generateButtonMatches matches postFunction = 
    let handleMove (row:int, column:int) = postFunction row column
    let rec generateMatches level state = 
        match state with
        | []    -> []
        |  x::xs -> generateHeap level x @ generateMatches (level+1) xs
    and generateHeap level = function
      | 0   -> []
      | n   -> ((upcast (matchButton (n*matchW-matchW/2) (level*matchH-matchH/2) (string level + "." + string n) (fun (_) -> handleMove (level,n)) )) : Control)::(generateHeap level (n-1))
    let (matchButtons : Control list) = (generateMatches 1 matches)
    List.toArray (matchButtons);;

let reduceState matches index position = let newArray = List.toArray matches
                                         Array.set newArray (index-1) (position-1)
                                         Array.toList  newArray;;
                                         
let updateFinishScreen hasWon (matchPanel : Control) = 
        if hasWon then
            let image = Image.FromFile("won.jpg")
            matchPanel.BackgroundImage <- image
        else
            let image = Image.FromFile("lost.jpg")
            matchPanel.BackgroundImage <- image
        matchPanel.Size <- Size(kittenW, kittenH)


// Launcher functions
let winX = 640;;
let winY = 800;;
let margin = 100;;

let form =
  new Form(Text="Nim game launcher", Size=Size(winX,winY), AutoScroll = true)

let urlBox =
  new TextBox(Location=Point(30,30),Size=Size(200,25));;

let downloadButton = 
  new Button(Location=Point(30, 70),MinimumSize=Size(60,35),
              MaximumSize=Size(90,35),Text="Download your game");;

let launchButton =
  new Button(Location=Point(110,70),MinimumSize=Size(60,35),
              MaximumSize=Size(90,35),Text="Launch your game");;
let status  =
  new Label (Location=Point(30,margin+30),Size=Size(200,30))

let makeDownloadButton x y text = 
  new Button(Location=Point(x,y),MinimumSize=Size(60,35),
              MaximumSize=Size(90,35),Text=text)

let makeLaunchButton x y = 
  new Button(Location=Point(x,y),MinimumSize=Size(60,35),
              MaximumSize=Size(90,35),Text="Launch");;

let cancelButton =
  new Button(Location=Point(250,margin+30),MinimumSize=Size(60,30),
              MaximumSize=Size(90,30),Text="Abort download")

let disable bs = 
    for (b : Control) in bs do 
        b.Enabled  <- false

let rec generateBtns n = function 
 | []   -> []
 | x::xs -> let dwBtn = (upcast (makeDownloadButton 30 ((n+1)*40+margin+25)  ("Download game" + string (n+1))) : Control)
            let lncBtn = (upcast (makeLaunchButton 115 ((n+1)*40+margin+25)) : Control)
            (dwBtn, lncBtn, x) :: generateBtns (n+1) xs

let rec generateClickOptions dwnClickFunction cancelButton = function
    | []    -> ()
    | (dwBtn : Control, lncBtn, x) :: xs -> dwBtn.Click.Add (fun (_) -> dwnClickFunction x lncBtn cancelButton)
                                            generateClickOptions dwnClickFunction cancelButton xs;;
let bgImage = Image.FromFile("kitty_cover.jpg");;