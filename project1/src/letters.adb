with Ada.Float_Text_IO; use Ada.Float_Text_IO;
with Ada.Strings.Equal_Case_Insensitive;

with Decrypter.Trace;

package body Letters is

   Element_Count : Natural := 0;

   procedure Add (C : in Character) is
   begin
      Add ((1 => C));
   end Add;

   procedure Add (C : in Letter) is
      use Character_Counter;

      Context : constant String := Package_Name & ".Add";

      procedure Update (Key     : in     Letter;
                        Element : in out Natural);

      procedure Update (Key     : in     Letter;
                        Element : in out Natural) is
         pragma Unreferenced (Key);
      begin
         Element := Element + 1;
      end Update;

      Cur : constant Cursor := Frequencies.Find (C);
   begin
      if Cur = No_Element then
         Frequencies.Insert (Key      => C,
                             New_Item => 0);
      else
         Frequencies.Update_Element (Position => Cur,
                                     Process  => Update'Access);
      end if;

      Element_Count := Element_Count + 1;
   end Add;

   procedure Clear is
   begin
      Frequencies.Clear;
      Element_Count := 0;
   end Clear;

   function Equivalent_Keys (Left, Right : in Letter) return Boolean is
   begin
      return Ada.Strings.Equal_Case_Insensitive (Left  => String (Left),
                                                 Right => String (Right));
   end Equivalent_Keys;

   function Frequency (C : in Letter) return Float is
   begin
      if Frequencies.Contains (Key => C) then
         return
           Float (Frequencies.Element (C)) /
           Float (Element_Count);
      else
         return 0.0;
      end if;
   end Frequency;

   function Hash_Letter (Item : in Letter) return Hash_Type is
   begin
      return Character'Pos (Item (Item'First));
   end Hash_Letter;

   function Image (Item : in Letter_Frequency) return String is
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
      use Character_Counter;

      Vec : Frequency_Count.Vector;

      function Comparison (Left, Right : in Letter_Frequency)
                           return Boolean;

      function Reverse_Comparison (Left, Right : in Letter_Frequency)
                                   return Boolean;

      function Comparison (Left, Right : in Letter_Frequency)
                                   return Boolean is
      begin
         return Left.Frequency < Right.Frequency;
      end Comparison;

      function Reverse_Comparison (Left, Right : in Letter_Frequency)
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
         Vec.Append (New_Item =>
                       (Key       => Key (C),
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

end Letters;
