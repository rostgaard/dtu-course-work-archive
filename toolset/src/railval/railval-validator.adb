with Ada.Strings.Fixed;

with Railval.Trace;

with Crude_Containers.Multiset;

package body Railval.Validator is
   use Railval.Trace;
   type Train_Track_Multiplicity is range 0 .. 1;

   function "=" (Left, Right : in Row_Element_Container.Vector)
                 return Boolean is
      use Row_Element_Container;
      C1 : Cursor := First (Left);
      C2 : Cursor := First (Right);
   begin
      if Left.Length /= Right.Length then
         return False;
      end if;

      while Has_Element (C1) or Has_Element (C2) loop
         if Element (C1) /= Element (C2) then
            return False;
         end if;
         C1 := Next (C1);
         C2 := Next (C2);
      end loop;

      return True;
   end "=";

   function Check (Transition : in Transitions) return Boolean is
   begin
      if Transition (1) = Transition (2) then
         return True;
      end if;

      return
        Character'Pos (Character (Transition (1)))
        < Character'Pos (Character (Transition (2)));
   end Check;

   function Create (Item : in String) return Transitions is
      Transition : Transitions;
   begin
      if Item'Length /= 2 then
         raise Constraint_Error with "Bad length: " & Item;
      end if;

      if
        Character'Pos (Item (Item'First)) >  Character'Pos (Item (Item'Last))
      then
         Transition :=
           (1 => Identifications (Item (Item'Last)),
            2 => Identifications (Item (Item'First)));
      else
         Transition :=
           (1 => Identifications (Item (Item'First)),
            2 => Identifications (Item (Item'Last)));
      end if;

      return Transition;
   end Create;

   function Create (From : in Row_Container.Vector) return Schedules is
      Previous_Field : Unbounded_String := To_Unbounded_String ("0");
      Width          : Natural := 0;
      Height         : Natural := 0;
      Count          : Natural := 0;
   begin
      --  Convert
      for Row of From loop
         Height := Height + 1;
         for Item of Row loop
            if Item = "STOP" then --  Bad case!
               if Previous_Field = "0" then
                  raise Constraint_Error with "Bad input.";
               end if;
               Count := Count + 1;
            else
               --  Skip first field.
               if Previous_Field /= "0" then
                  Count := Count + 1;
               end if;

               Previous_Field := Item;
            end if;
            Count := Count + 1;

         end loop;
         if Count > Width then
            Width := Count;
         end if;
         Count := 0;
         Previous_Field := To_Unbounded_String ("0");
      end loop;
      Debug (Context => "null",
             Message => "Creating matrix of size:"
             & Height'Img & "x" & Width 'Img);

      declare
         Schedule : Schedules (1 .. Height, 1 .. Width) :=
           Create_Empty (X => Height, Y => Width);
         Row_Num      : Natural := 1;
         Column_Num   : Natural := 1;
      begin
         for Row of From loop
            Debug ("null", Column_Num'Img & " => (");
            Previous_Field := To_Unbounded_String ("0");
            for Item of Row loop
               if Item = "STOP" then
                  if Previous_Field = "0" then
                     raise Constraint_Error with "Bad input.";
                  end if;

                  Debug ("null", Row_Num'Img & " => ");
                  Debug ("null", To_String (Previous_Field & Previous_Field));
                  Schedule (Column_Num, Row_Num) :=
                    Create (To_String (Previous_Field & Previous_Field));

                  Row_Num := Row_Num + 1;
                  Debug ("null", ", ");
                  Debug ("null", Row_Num'Img & " => ");
                  Debug ("null", To_String (Previous_Field & Previous_Field));
                  Schedule (Column_Num, Row_Num) :=
                    Create (To_String (Previous_Field & Previous_Field));
                  Row_Num := Row_Num + 1;
               else
                  --  Skip first field.
                  if Previous_Field /= "0" then
                     Debug ("null", Row_Num'Img & " => ");
                     Debug ("null", To_String (Item & Previous_Field));
                     Schedule (Column_Num, Row_Num) :=
                    Create (To_String (Item & Previous_Field));
                     Debug ("null", ", ");
                     Row_Num := Row_Num + 1;
                  end if;

                  Debug ("null", Row_Num'Img & " => ");
                  Debug ("null", To_String (Item & Item));
                  Schedule (Column_Num, Row_Num) :=
                    Create (To_String (Item & Item));
                  Row_Num := Row_Num + 1;
                  Previous_Field := Item;
               end if;
               Debug ("null", ", ");

            end loop;

            --  Pad the matrix.
            while Row_Num <= Width loop
               Schedule (Column_Num, Row_Num) :=
                 Create (To_String (Previous_Field & Previous_Field));
               Row_Num := Row_Num + 1;
            end loop;

            Row_Num := 1;
            Column_Num := Column_Num + 1;
            Debug ("null", ")");
         end loop;

         return Schedule;

      end;
   end Create;

   function Create_Empty (X, Y : in Positive) return Schedules is
      Schedule : Schedules (1 .. X, 1 .. Y);
   begin
      for I in Schedule'Range (1) loop
         for J in Schedule'Range (2) loop
            Schedule (I, J) := "00";
         end loop;
      end loop;
      return Schedule;
   end Create_Empty;

   function Image (Item : in Transitions) return String is
   begin
      return Image (Item (1)) & Image (Item (2));
   end Image;

   function Image (Item : in Schedules) return String is
      --  use Ada.Strings.Unbounded;

      Buffer : Unbounded_String;
   begin
      for I in Item'Range (1) loop
         for J in Item'Range (2) loop
            Append (Buffer, Image (Item (I, J)) & ',' & ' ');
         end loop;
      end loop;

      return To_String (Buffer);
   end Image;

   function Next_Word (Item : in String) return String is
      use Ada.Strings.Fixed;
      Start : Natural := Item'First;
      Seek  : Natural := Item'First;
   begin
      Start := Skip_Whitespace (Item);
      Seek  := Start;

      if Start = Item'Last then
         return Null_Token;
      end if;

      while not (Item (Seek) = ' '
                 or
                   Item (Seek) = ASCII.HT
                 or
                   Seek = Item'Last)
      loop
         Seek := Seek + 1;
      end loop;

      return Trim (Source => Item (Start .. Seek),
                   Side   => Ada.Strings.Both);
   end Next_Word;

   function Parse_Line (Item : in String)
                     return Row_Element_Container.Vector is
      Current_Token : Unbounded_String;
      Pos : Natural := Item'First;
      Vector : Row_Element_Container.Vector;
   begin
      while Pos < Item'Last loop
         Pos := Skip_Whitespace (Item (Pos .. Item'Last));
         Current_Token :=
           To_Unbounded_String (Next_Word (Item (Pos .. Item'Last)));
         if Current_Token /= Null_Token then
            Debug ("null", "Parse_line: Got word : """ &
                        To_String (Current_Token) & """");
            Pos := Pos +  To_String (Current_Token)'Length;
            Vector.Append (New_Item => Current_Token);
         end if;
      end loop;
      return Vector;
   end Parse_Line;

   function Skip_Whitespace (Item : in String) return Natural is
      Seek : Natural := Item'First;
   begin
      while Item (Seek) = ' ' or Item (Seek) = ASCII.HT loop
         Seek := Seek + 1;
      end loop;
      return Seek;
   end Skip_Whitespace;

   procedure Validate (Schedule : in Schedules) is
      use Crude_Containers;
   begin
      if Schedule = Null_Schedule then
         raise Constraint_Error with "Empty schedule";
      end if;

      for J in Schedule'Range (2) loop
         declare
            package Traincount is new Multiset
              (Element      => Transitions,
               Null_Element => "00",
               Max_Elements => 32,
               Count        => Train_Track_Multiplicity);
         begin
            for I in Schedule'Range (1) loop
               Traincount.Insert (Schedule (I, J));
            end loop;
         end;
      end loop;
   end Validate;

end Railval.Validator;
