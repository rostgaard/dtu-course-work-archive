with Ada.Text_IO; use Ada.Text_IO;
--  with Ada.Strings.Unbounded;

with Multiset;

package body Railval.Validator is
   type Train_Track_Multiplicity is range 0 .. 1;

   function Check (Transition : in Transitions) return Boolean is
   begin
      return
        Transition (1) < Transition (2);
   end Check;

   function Image (Item : in Transitions) return String is
   begin
      return Image (Item (1)) & Image (Item (2));
   end Image;

   function Image (Item : in Schedules) return String is
      --  use Ada.Strings.Unbounded;

      --  Buffer : Unbounded_String;
   begin
      for I in Item'Range (1) loop
         for J in Item'Range (2) loop
            Put (Image (Item (I, J)) & ',' & ' ');
         end loop;
         New_Line;
      end loop;

      return "";
   end Image;

   procedure Validate (Schedule : in Schedules) is

   begin
      for J in Schedule'Range (2) loop
         declare
            package Traincount is new Multiset
              (Element      => Transitions,
               Null_Element => "00",
               Max_Elements => 32,
               Count        => Train_Track_Multiplicity);
         begin
            for I in Schedule'Range (1) loop
               Traincount.Insert (Schedule (I,J));
            end loop;
         end;
      end loop;
   end Validate;

end Railval.Validator;
