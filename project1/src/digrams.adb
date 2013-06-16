with Ada.Strings.Hash;
with Ada.Text_IO; use Ada.Text_IO;
with Ada.Float_Text_IO; use Ada.Float_Text_IO;
with Ada.Strings.Equal_Case_Insensitive;

with Decrypter.Trace;

package body Digrams is

   Element_Count : Natural := 0;

   procedure Add (D : in Digram) is
      Context : constant String := Package_Name & ".Add";

      procedure Update (Key     : in Digram;
                        Element : in out Natural);

      procedure Update (Key     : in Digram;
                        Element : in out Natural) is
         pragma Unreferenced (Key);
      begin
         Element := Element + 1;
      end Update;

   begin
      Decrypter.Trace.Debug (Message => "Adding """ & String (D) & """",
                             Context => Context);
      if not Frequencies.Contains (D) then
         Frequencies.Insert (D, 0);
      else

         Frequencies.Update_Element (Frequencies.Find (D), Update'Access);
      end if;

      Element_Count := Element_Count + 1;
   end Add;

   procedure Clear is
   begin
      Frequencies.Clear;
   end Clear;

   function Equivalent_Keys (Left, Right : Digram) return Boolean is
   begin
      return Ada.Strings.Equal_Case_Insensitive (Left => String (Left),
                                                 Right => String (Right));
   end Equivalent_Keys;

   function Frequency (D : in Digram) return Float
   is
   begin
      if not Frequencies.Contains (D) then
         return 0.0;
      else
         return Float (Frequencies.Element (D)) /
           Float (Element_Count);
      end if;
   end Frequency;

   function Hash_Trigram (Item : in Digram) return Hash_Type is
   begin
      return Ada.Strings.Hash (String (Item));
   end Hash_Trigram;

   function Image (Item : in Digram_Frequency) return String is
      Buffer : String (1 .. 6);
   begin
      Ada.Float_Text_IO.Put
        (To   => Buffer,
         Item => Item.Frequency,
         Aft  => 4,
         Exp  => 0);

      return String (Item.Key) & " => " & Buffer;
   end Image;

   function To_Ordered_Table (Reverse_Order : in Boolean := False)
                              return Frequency_Count.Vector is
      use Count_Storage;
      Vec : Frequency_Count.Vector;

      function Comparison (Left, Right : in Digram_Frequency)
                                   return Boolean;

      function Comparison (Left, Right : in Digram_Frequency)
                           return Boolean is
      begin
         return Left.Frequency < Right.Frequency;
      end Comparison;

      function Reverse_Comparison (Left, Right : in Digram_Frequency)
                                   return Boolean;

      function Reverse_Comparison (Left, Right : in Digram_Frequency)
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
         Vec.Append ((Key => Key (C),
                      Frequency => Frequency (Key (C))));
         C := Next (C);
      end loop;

      if Reverse_Order then
         Sorting_Reverse.Sort (Vec);
      else
         Sorting.Sort (Vec);
      end if;

      return Vec;
   end To_Ordered_Table;
end Digrams;
