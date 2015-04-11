with Ada.Text_IO; use Ada.Text_IO;

procedure Exercise_08 is

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

   Sample : constant Test_Sets := ((Value => 542.0, Expected => 500.0),
                                   (Value => 458.0, Expected => 500.0));

begin

   Put_Line (Chi_Squared (Sample)'Img);

end Exercise_08;
