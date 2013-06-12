with Ada.Text_IO; use Ada.Text_IO;

procedure Exercise_09 is

   type Test_Paramaters is
      record
         Value    : Float;
         Expected : Float;
      end record;

   type Test_Sets is array (Natural range <>) of Test_Paramaters;

   function Chi_Squared (Test_Set : in Test_Sets) return Float;

   function Chi_Squared (Test_Set : in Test_Sets) return Float is
      Sum : Float := 0.0;
   begin
      for I in Test_Set'Range loop

         Sum := Sum + ((Test_Set (I).Value - Test_Set (I).Expected)**2
                       / Test_Set (I).Expected);
      end loop;

      return Sum;
   end Chi_Squared;

   Sample : constant Test_Sets :=
     ((Value => 231.0, Expected => 250.0),
      (Value => 271.0, Expected => 250.0),
      (Value => 270.0, Expected => 250.0),
      (Value => 228.0, Expected => 250.0));

begin
   Put_Line (Chi_Squared (Sample)'Img);

end Exercise_09;
