with Ada.Text_IO; use Ada.Text_IO;

procedure Contract_Test is
   
   procedure Test (Item : in Natural) with
     Pre => Item > 10,
     Post => Item = Item'Old+1;
   
   procedure Test (Item : in Natural) is
   begin
      Put_Line (Item'Img);
   end Test;
   
begin
   
   Test (20); --  This is ok for pre, but should fail post.
   Test (2);  --  This fails pre.
end Contract_Test;
