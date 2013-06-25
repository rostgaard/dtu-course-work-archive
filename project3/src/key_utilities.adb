with Ada.Integer_Text_IO;
with Ada.Strings.Unbounded;

with Decrypter;

package body Key_Utilities is

   function Shift_Left (Value  : in Unsigned_64;
                        Amount : in Natural) return Unsigned_64;

   function Generate_Key (Seed : Unsigned_64) return Keys is
      Key : Keys := (others => 0);
      S   : Unsigned_64 := Seed;
   begin
      for I in Key'Range loop
         --  The eight least significant bits of update.
         S := Decrypter.Update (S);
         Key (I) := Bytes (S and 16#ff#);
      end loop;

      return Key;
   end Generate_Key;

   function Image (Item : in Keys) return String is
      use Ada.Strings.Unbounded;
      Buffer : Unbounded_String;
      S_Buf  : String (1 .. 6);
   begin
      for I in reverse Item'Range loop
         Ada.Integer_Text_IO.Put (To    => S_Buf,
                                  Item  => Integer (Item (I)),
                                  Base  => 16);

         if Item (I) > 16 then
            Append
              (Source   => Buffer,
               New_Item => S_Buf (4 .. 5));
         else
            Append
              (Source   => Buffer,
               New_Item =>  "0" & S_Buf (5 .. 5));
         end if;
      end loop;

      return To_String (Buffer);
   end Image;

   function Is_Printable (C : in Character) return Boolean is
   begin

      return
        Character'Pos (C) in 32 .. 126 or
        Character'Pos (C) in 9 .. 13;
   end Is_Printable;

   function Shift_Left (Value  : in Unsigned_64;
                        Amount : in Natural) return Unsigned_64 is
   begin
      return Value * (2**Amount);
   end Shift_Left;

   function Value (Item : in Keys) return Unsigned_64 is
      Sum    : Unsigned_64 := 0;
   begin
      for I in 0 .. 8 loop
         Sum := Sum + Shift_Left
           (Value  => Unsigned_64 (Item (I)),
            Amount => I * 8);
      end loop;

      return Sum;
   end Value;

end Key_Utilities;
