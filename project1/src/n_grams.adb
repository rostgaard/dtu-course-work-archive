with Ada.Text_IO; use Ada.Text_IO;
with Ada.Float_Text_IO; use Ada.Float_Text_IO;
with Ada.Strings.Hash_Case_Insensitive;
with Ada.Strings.Equal_Case_Insensitive;

package body N_Grams is

   Element_Count : Natural := 0;

   procedure Add (Item : in Element_String) is
      use Count_Storage;

      --  Context : constant String := Package_Name & ".Add";

      procedure Update (Key   : in     Element_String;
                        Value : in out Element);

      procedure Update (Key   : in     Element_String;
                        Value : in out Element) is
         pragma Unreferenced (Key);
      begin
         Value.Count := Value.Count + 1;
      end Update;

   begin
      if not Frequencies.Contains (Item) then
         Frequencies.Insert (Key      => Item,
                             New_Item => (Key => Item, Count => 0));
      else
         Frequencies.Update_Element
           (Position => Frequencies.Find (Key => Item),
            Process  => Update'Access);
      end if;

      Element_Count := Element_Count + 1;
   end Add;

   function Calculate_Frequency (Item : in Element_String) return Float
   is
   begin
      if not Frequencies.Contains (Item) then
         return 0.0;
      else
         return Float (Frequencies.Element (Item).Count) /
           Float (Element_Count);
      end if;
   end Calculate_Frequency;

   procedure Clear is
   begin
      Frequencies.Clear;
      Element_Count := 0;
   end Clear;

   function Equivalent_Keys (Left, Right : in Element_String) return Boolean is
   begin
      return Ada.Strings.Equal_Case_Insensitive (Left  => String (Left),
                                                 Right => String (Right));
   end Equivalent_Keys;

   function Hash (Item : in Element_String) return Hash_Type is
   begin
      return Ada.Strings.Hash_Case_Insensitive (String (Item));
   end Hash;

   function Image (Item : in Frequency) return String is
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

      function Comparison (Left, Right : in Frequency)
                                   return Boolean;

      function Comparison (Left, Right : in Frequency)
                           return Boolean is
      begin
         return Left.Frequency < Right.Frequency;
      end Comparison;

      function Reverse_Comparison (Left, Right : in Frequency)
                                   return Boolean;

      function Reverse_Comparison (Left, Right : in Frequency)
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
         Vec.Append ((Key       => Key (C),
                      Frequency => Calculate_Frequency (Key (C))));
         C := Next (C);
      end loop;

      if Reverse_Order then
         Sorting_Reverse.Sort (Vec);
      else
         Sorting.Sort (Vec);
      end if;

      return Vec;
   end To_Ordered_Table;

end N_Grams;
