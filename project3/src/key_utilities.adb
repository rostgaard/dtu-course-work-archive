with Ada.Integer_Text_IO;
with Ada.Strings.Unbounded;

with Decrypter;

package body Key_Utilities is

   function Generate_Key (Seed : Unsigned_64) return Keys is
      Key : Keys := (others => 0);
      S   : Unsigned_64 := Seed;
   begin
      for I in Key'Range loop
         --  The eight least significant bits of update.
         S := Decrypter.Update (S) and 16#ff#;
         Key (I) := Bytes (S);
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

         if Item (I) < 16  then
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

      return C in Character'('A') .. Character'('Z') or
        C in Character'('a') .. Character'('a') or
        C in Character'('0') .. Character'('9');
   end Is_Printable;

   function Value (Item : in Keys) return Unsigned_128 is
      Sum    : Unsigned_128 := 0;
   begin
      for I in reverse 0 .. 15 loop
         Sum := Sum + ((2**I) * Unsigned_128 (Item (I)));
      end loop;

      return Sum;
   end Value;

end Key_Utilities;
