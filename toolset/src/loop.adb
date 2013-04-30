with Ada.Text_IO; use Ada.Text_IO;

procedure Test_Loop is
   
   type Colours is (Red, Green, Yellow);
   
begin
   for I in Colours'Range loop
      Put_Line (I'Img);
   end loop;
   
   
end Test_Loop;
