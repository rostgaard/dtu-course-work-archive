with Ada.Text_IO; use Ada.Text_IO;

package body Schedule_Validator is
   
   function Create (C1,C2 :  in Character) return Pairs is
   begin
      return (C1, C2);
   end Create;

   
   -- type Schedule is array (Positive range <>, Positive range <>) of Character;
   
   function Check (Item : in Pairs) return Boolean is
   begin
      return True;
   end Check;
   
   procedure Image (Item : in Schedule)  is
   begin
      for J in Item'Range(1) loop
         for I in Item'Range(2) loop
            Put (Item (J,I) & ',' & ' ');
         end loop;
         New_Line;
      end loop;
   end Image;
   
   
   function Image (Item : Pairs) return String is
   begin
      return Item.Left & ", " & Item.Right;
   end Image;
   
   function Image (Item : in Valid_Square) return String is
   begin
      return Image (Item.Top)  & Image (Item.Bottom);
   end Image;
   
   
   procedure Validate (Item : in Schedule) is
      Last_I, Last_J : Natural := 0;
   begin
      for J in 1 .. (Item'Last(2)-1) loop
         for I in 1 .. (Item'last(1)-1) loop
            Last_I := I;
            Last_J := J;
            declare
               Test : Valid_Square := Create ((Item (I,J), Item (I,J+1)),
                                              (Item (I+1,J), Item (I+1,J+1)));
            begin
               Put_Line (Image (Test));
               
               Put_Line ("Testing  " & Item (Last_I,Last_J)'Img & "," & Item (Last_I,Last_J+1)'Img & " " &
                                           Item (Last_I+1,Last_J)'Img & "," & Item (Last_I+1,Last_J+1)'Img);
            end;
         end loop;
      end loop;
   exception
      when others =>
         Put_Line ("Failed On " & Item (Last_I,Last_J)'Img & "," & Item (Last_I,Last_J+1)'Img & " " &
                                           Item (Last_I+1,Last_J)'Img & "," & Item (Last_I+1,Last_J+1)'Img);
      
      
   end Validate;
   
   
   function Create (Pair1, Pair2 : in Pairs) return Valid_Square is
   begin
      return (Pair1, Pair2);
   end Create;

      
   function Colliding (Pair1, Pair2 : in Pairs) return Boolean is
      Test : Valid_Square := (Pair1, Pair2);
   begin
      Put_Line (Image (Test));
      

         
      
--      Put ("Checking " & Image (Pair1) & " and " & Image (Pair2));
      
--        if
--          Pair1.Track1 = Pair2.Track1 or
--          Pair1.Track2 = Pair2.Track2
--        then
--  --         Put_Line (" - failed");
--           return True;
--        elsif 
--          Pair1.Track1 = Pair2.Track2 and
--          Pair1.Track2 = Pair2.Track1
--        then
--  --         Put_Line (" - failed");
--           return True;
--        end if;
      
--      Put_Line (" - OK");
      return False;
   end Colliding;
   
   
--  begin
--     Image (Sched1);
   
--     for J in 1 .. (Sched1'Last(2)-1) loop
--        for I in 1 .. (Sched1'last(1)-1) loop
--  --         Put_Line ("I =" & I'Img & " J=" & J'Img);
         
--           if Colliding ((Sched1 (I,J), Sched1 (I,J+1)),
--                         (Sched1 (I+1,J), Sched1 (I+1,J+1)))  then
--              --  Put (Sched1 (J,I) & ',' & ' ');
--              --  Put_Line ("Colliding!");
--              null;
--           end if;
--        end loop;
--        New_Line;
--     end loop;
      
   
   --  Put_Line (Colliding (('a','b'), ('c','b'))'Img);
   --  Put_Line (Colliding (('a','b'), ('b','a'))'Img);
   --  Put_Line (Colliding (('a','b'), ('b','c'))'Img);
   --  Put_Line (Colliding (('a','b'), ('a','c'))'Img);
   
   
end Schedule_Validator;
   
