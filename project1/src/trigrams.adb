--  with Ada.Strings.Hash;
with Ada.Text_IO; use Ada.Text_IO;
with Ada.Float_Text_IO; use Ada.Float_Text_IO;
--  with Ada.Strings.Equal_Case_Insensitive;

with Decrypter.Trace;

package body Trigrams is

   Element_Count : Natural := 0;

--     function "<" (Left, Right : in Trigram) return Boolean is
--     begin
--        return Left.Count < Right.Count;
--     end "<";
--
--     function "=" (Left, Right : in Trigram) return Boolean is
--     begin
--        return Left.Count = Right.Count;
--     end "=";

   procedure Add (T : in Trigram_String) is
      use Count_Storage;

      Context : constant String := Package_Name & ".Add";

      procedure Update (Key     : in     Trigram_String;
                        Element : in out Trigram);

      procedure Update (Key     : in     Trigram_String;
                        Element : in out Trigram) is
         pragma Unreferenced (Key);
      begin
         Element.Count := Element.Count + 1;
      end Update;

   begin
      Decrypter.Trace.Debug (Message => "Adding """ & String (T) & """",
                             Context => Context);
      if not Frequencies.Contains (T) then
         Frequencies.Insert (Key      => T,
                             New_Item => (Key => T, Count => 0));
      else
         Frequencies.Update_Element (Position => Frequencies.Find (Key => T),
                                     Process  => Update'Access);
      end if;

      Element_Count := Element_Count + 1;
   end Add;

   procedure Clear is
   begin
      Frequencies.Clear;
   end Clear;

--     function Equivalent_Keys (Left, Right : Trigram) return Boolean is
--     begin
--        return Ada.Strings.Equal_Case_Insensitive (Left => String (Left),
--                                                   Right => String (Right));
--     end Equivalent_Keys;

   function Frequency (T : in Trigram_String) return Float
   is
   begin
      if not Frequencies.Contains (T) then
         return 0.0;
      else
         return Float (Frequencies.Element (T).Count) /
           Float (Element_Count);
      end if;
   end Frequency;

--     function Hash_Trigram (Item : in Trigram) return Hash_Type is
--     begin
--        return Ada.Strings.Hash (Key => Item (1) & Item (2) & Item (3));
--     end Hash_Trigram;

   procedure Image (Item : Trigram) is
   begin
      Put (String (Item.Key) & " => ");
      Ada.Float_Text_IO.Put
        (Item => Frequency (T => Item.Key),
         Fore => 0,
         Aft  => 4,
         Exp  => 0);
      New_Line;
   end Image;

   function Image (Item : in Trigram_Frequency) return String is
      Buffer : String (1 .. 6);
   begin
      Ada.Float_Text_IO.Put
        (To   => Buffer,
         Item => Item.Frequency,
         Aft  => 4,
         Exp  => 0);

      return String (Item.Key) & " => " & Buffer;
   end Image;

   procedure Show_Contents (Threshold : in Float := 0.0001) is
      use Count_Storage;

      C : Cursor := Frequencies.First;
   begin
      while Has_Element (C) loop
         if Frequency (Key (C)) > Threshold then

            Image (Element (C));
         end if;
         C := Next (C);
      end loop;
   end Show_Contents;

   function To_Ordered_Table (Reverse_Order : in Boolean := False)
                              return Frequency_Count.Vector is
      use Count_Storage;
      Vec : Frequency_Count.Vector;

      function Comparison (Left, Right : in Trigram_Frequency)
                                   return Boolean;

      function Comparison (Left, Right : in Trigram_Frequency)
                           return Boolean is
      begin
         return Left.Frequency < Right.Frequency;
      end Comparison;

      function Reverse_Comparison (Left, Right : in Trigram_Frequency)
                                   return Boolean;

      function Reverse_Comparison (Left, Right : in Trigram_Frequency)
                                   return Boolean is
      begin
         return Left.Frequency > Right.Frequency;
      end Reverse_Comparison;

      package Sorting_Reverse is new Frequency_Count.Generic_Sorting
        ("<" => Reverse_Comparison);
      package Sorting is new Frequency_Count.Generic_Sorting
        ("<" => Comparison);

      C : Cursor := Frequencies.First;
   begin
      while C /= No_Element loop
         Vec.Append ((Key => Key (C), Frequency => Frequency (Key (C))));
         C := Next (C);
      end loop;

      if Reverse_Order then
         Sorting_Reverse.Sort (Vec);
      else
         Sorting.Sort (Vec);
      end if;

      return Vec;
   end To_Ordered_Table;

end Trigrams;
