with Ada.Text_IO; use Ada.Text_IO;
with Ada.Float_Text_IO; use Ada.Float_Text_IO;

with Decrypter.Trace;

package body Letters is

   Element_Count : Natural := 0;

   procedure Image (Item : in Letter);

   function "=" (Left, Right : in Letter) return Boolean is
   begin
      return Left.Count = Right.Count;
   end "=";

   procedure Add (C : in Character) is
      use Character_Counter;

      Context : constant String := Package_Name & ".Add";

      procedure Update (Key     : in     Character;
                        Element : in out Letter) is
      begin
         Element.Count := Element.Count + 1;
      end Update;

      Cur : constant Cursor := Frequencies.Find (C);
   begin
      Decrypter.Trace.Debug (Message => "Adding """ & C & """",
                             Context => Context);

      if Cur = No_Element then
         Frequencies.Insert (Key      => C,
                             New_Item => (Letter => C, Count => 0));
      else
         Frequencies.Update_Element (Position => Cur,
                                     Process  => Update'Access);
      end if;

      Element_Count := Element_Count + 1;
   end Add;

   function Frequency (C : in Character) return Float is
   begin
      if Frequencies.Contains (Key => C) then
         return
           Float (Frequencies.Element (C).Count) /
           Float (Element_Count);
      else
         return 0.0;
      end if;
   end Frequency;

   function Hash_Character (Item : in Character) return Positive is
   begin
      return Character'Pos (Item);
   end Hash_Character;

   procedure Image (Item : in Letter) is
   begin
      Put (Item.Letter & " => ");
      Ada.Float_Text_IO.Put
        (Item => Frequency (C => Item.Letter),
         Fore => 0,
         Aft  => 3,
         Exp  => 0);
      New_Line;
   end Image;

   procedure Show_Contents (Threshold : in Float := 0.0001) is
      use Character_Counter;

      C : Cursor := Frequencies.First;
   begin
      while Has_Element (C) loop
         if Frequency (Key (C)) > Threshold then

            Image (Element (C));
         end if;
         C := Next (C);
      end loop;
   end Show_Contents;
end Letters;
