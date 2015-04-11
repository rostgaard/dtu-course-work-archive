open System.Windows.Forms 
open System.Drawing 

let window =
  new Form(Text="Web Source Length", Size=Size(525,225))

let urlBox =
  new TextBox(Location=Point(50,25),Size=Size(400,25))

let ansBox =
  new TextBox(Location=Point(150,150),Size=Size(200,25))

let startButton =
  new Button(Location=Point(50,65),MinimumSize=Size(100,50),
              MaximumSize=Size(100,50),Text="START")

let clearButton =
  new Button(Location=Point(200,65),MinimumSize=Size(100,50),
              MaximumSize=Size(100,50),Text="CLEAR")

let cancelButton =
  new Button(Location=Point(350,65),MinimumSize=Size(100,50),
              MaximumSize=Size(100,50),Text="CANCEL")

let disable bs = 
    for b in [startButton;clearButton;cancelButton] do 
        b.Enabled  <- true
    for (b:Button) in bs do 
        b.Enabled  <- false
