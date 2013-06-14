with Ada.Strings.Hash;
with Ada.Text_IO; use Ada.Text_IO;
with Ada.Float_Text_IO; use Ada.Float_Text_IO;
with Ada.Strings.Equal_Case_Insensitive;

with Decrypter.Trace;

package body Trigrams is

   Element_Count : Natural := 0;

   function "<" (Left, Right : in Trigram) return Boolean is
   begin
      return Left.Count < Right.Count;
   end "<";

   function "=" (Left, Right : in Trigram) return Boolean is
   begin
      return Left.Count = Right.Count;
   end "=";

   procedure Add (T : in Trigram_String) is
      use Count_Storage;

      Context : constant String := Package_Name & ".Add";

      procedure Update (Key     : in     Trigram_String;
                        Element : in out Trigram);

      procedure Update (Key     : in     Trigram_String;
                        Element : in out Trigram) is
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

--     function Value (C1, C2, C3 : in Character) return Trigram is
--     begin
--        return (1 => C1, 2 => C2, 3 => C3);
--     end Value;

--     function To_Ordered_Array (Max_Size : in Natural := 25)
--                                return Ordered_Trigram_List is
--        use Count_Storage;
--
--        Result_List : Ordered_Trigram_List (1 .. Max_Size);
--        First       : constant access Trigram
--          := Result_List (Result_List'Last)'Access;
--        Count : Natural := 0;
--
--        procedure Insert (Element : in     Trigram;
--                          Into    : in out Ordered_Trigram_List) is
--        begin
--
--        end Insert;
--
--        C : Cursor := Frequencies.First;
--
--     begin
--        while Has_Element (C) loop
--           Insert (Element => Key (C),
--                   Into    => Result_List);
--           Count := Count + 1;
--
--           C := Next (C);
--        end loop;
--
--        return Result_List;
--     end To_Ordered_Array;

end Trigrams;
