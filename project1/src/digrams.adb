with Ada.Strings.Hash;
with Ada.Text_IO; use Ada.Text_IO;
with Ada.Float_Text_IO; use Ada.Float_Text_IO;
with Ada.Strings.Equal_Case_Insensitive;

with Decrypter.Trace;

package body Digrams is

   Element_Count : Natural := 0;

   function Equivalent_Keys (Left, Right : Digram) return Boolean is
   begin
      return Ada.Strings.Equal_Case_Insensitive (Left => String (Left),
                                                 Right => String (Right));
   end Equivalent_Keys;

   procedure Image (Item : Digram);

   procedure Add (D : in Digram) is
      Context : constant String := Package_Name & ".Add";

      procedure Update (Key     : in Digram;
                        Element : in out Natural) is
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

   procedure Image (Item : Digram) is
   begin
      Put (String (Item) & " => ");
      Ada.Float_Text_IO.Put
        (Item => Frequency (D => Item),
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
            Image (Key (C));
         end if;
         C := Next (C);
      end loop;

   end Show_Contents;

end Digrams;
