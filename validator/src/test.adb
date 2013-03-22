with Ada.Text_IO; use Ada.Text_IO;
with Schedule_Validator; use Schedule_Validator;
with Ada.Assertions; use Ada.Assertions;
with Ada.Exceptions; use Ada.Exceptions;

procedure Test is
   Sched1 : constant Schedule :=
     (
      ('a', 'b', 'c', 'd'),
      ('e', 'b', 'g', 'h'),
      ('i', 'j', 'k', 'l'),
      ('l', 'm', 'n', 'o'),
      ('p', 'q', 'r', 's')
     );
   
   procedure Test_Pairs (Parameter1, Parameter2 : in Pairs;
                         Expect_Exception       : in Boolean) is
      
   begin
      declare
         Pair : Valid_Square := Create (Parameter1, Parameter2);
      begin
         if not Expect_Exception then
            raise Constraint_Error with "Test failed";
         end if;
      end;
   exception
      when Assertion_Error =>
         if Expect_Exception then
            Put_Line ("ok");
         end if;
   end Test_Pairs;
   
begin
   Test_Pairs (Create ('a','b'), Create ('c','d'), false);
   
   Put_Line (Image (Create (Create ('a','b'), Create ('c','d'))));
--   Put_Line (Image (Create (Create ('b','b'), Create ('a','b'))));
   Validate (Sched1);
   
exception

   when E : Assertion_Error => 
      Put_Line ("OK");
      Put_Line (Exception_Information(E));
      
end Test;
