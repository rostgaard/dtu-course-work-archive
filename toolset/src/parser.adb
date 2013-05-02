with Ada.Text_IO; use Ada.Text_IO;
with Ada.Text_IO.Unbounded_IO; use Ada.Text_IO.Unbounded_IO;
with Ada.Strings.Unbounded;      use Ada.Strings.Unbounded;
with Ada.Containers.Vectors;     use Ada.Containers;

procedure Parser is
   package IO renames Ada.Text_IO;
   package SUIO renames Ada.Text_IO.Unbounded_IO;
   package Quote_Container is new Vectors (Natural, Unbounded_String);
   use Quote_Container;
   Routes : Vector;
   Input  : IO.File_Type;
   ConvertedRoutes : Vector;

   function RouteConverter (Routes : in Vector) return Vector;

   function RouteConverter (Routes : in Vector) return Vector is
      --   Used in function RouteConverter
      --  CurrentRoute : Unbounded_String;
      --  ConvertedRoute : String := "";
      ConvertedRoutes : Vector;
      CurrentStation : Unbounded_String;
      NextStation : Unbounded_String;
      SplittedRoute : Vector;
      Letter : Character;
      DoubleString : Unbounded_String;
   begin

      --  runs only one time - dont know why?
      for Item of Routes loop
         New_Line;
         --  Split CurrentRoute on spaces (Use a method or create our own?)
         for I in To_String (Item)'Range loop
            --  get char at position j from current route
            Letter := To_String (Item) (I);

            --  Put_Line(CurrentRoute'First + j));
            if Letter /= ' ' then
               CurrentStation := CurrentStation & Letter;
            elsif Letter = ' ' then
               Put (CurrentStation);
               SplittedRoute.Append (CurrentStation);
               CurrentStation := To_Unbounded_String ("");
               --  Put_Line(CurrentStation);
            end if;
         end loop;

         for J in Natural range 0 .. Integer (Length (SplittedRoute)) - 1 loop
            --  Get current station
            CurrentStation := SplittedRoute (J);

            Put_Line (CurrentStation);
            if CurrentStation /= "STOP" or J /=
              Integer (Length (SplittedRoute)) - 1 then
               Put_Line ("Not stop or not splitted size");
               --  Get next station as string
               NextStation := SplittedRoute (J + 1);
               Put_Line ("Nextstation: " & NextStation);
               --  Append to ConvertedRoutes
               DoubleString := DoubleString & CurrentStation &
                 CurrentStation  & " " & CurrentStation & NextStation & " ";
               Put_Line ("DoubleString: " & DoubleString);
               ConvertedRoutes.Append(DoubleString);
            else --  if j = Integer(Length(CurrentRoute))-1 then
               DoubleString := DoubleString & CurrentStation & CurrentStation;
               Put_Line ("End double string: " & DoubleString);
               ConvertedRoutes.Append (DoubleString);
            end if;
         end loop;
         Put_Line ("DoubleString: " & DoubleString);
         ConvertedRoutes.Append (DoubleString);
      end loop;

      return ConvertedRoutes;

   end RouteConverter;

begin
   Put_Line ("Parser started...");
   IO.Open (File => Input,
            Mode => IO.In_File,
            Name => "../schedules/test1.schedule");
   while not IO.End_Of_File (File => Input) loop
      Routes.Append (New_Item => SUIO.Get_Line (File => Input));
   end loop;
   IO.Close (Input);

   --  TEST
   Put_Line ("Input file contains:");
   for I in Integer range 0 .. Integer (Length (Routes)) - 1 loop
      Put_Line (Routes (I));
   end loop;

   Put_Line ("File has been read");

   --  TEST
   Put_Line ("Routes Converted:");
   ConvertedRoutes := RouteConverter (Routes);
   for I in Integer range 0 .. ConvertedRoutes'Size loop
      Put_Line (ConvertedRoutes (I));
   end loop;

   Put_Line ("Parser has ended!");

end Parser;
