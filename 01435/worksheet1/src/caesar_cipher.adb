with Ada.Characters.Handling;

package body Caesar_Cipher is

   function Decode (Item : in String;
                    Key  : in Positive) return String is
      Result : String (Item'Range) := (others => ' ');
   begin
      for I in Item'Range loop
         --  Skip spaces.
         if Item (I) /= ' ' then
            declare
               Position  : constant Natural
                 := (Character'Pos (Item (I)))-65;
               Enc_Char  : constant Character :=
                 Character'Val (((Position - Key) mod 26)+65);
            begin
               Result (I) := Enc_Char;
            end;
         end if;
      end loop;
      return Result;
   end Decode;

   function Encode (Item  : in String;
                    Key   : in Positive) return String is
      use Ada.Characters.Handling;

      Result : String (Item'Range) := To_Upper (Item); -- Working buffer.
   begin
      for I in Item'Range loop
         --  Skip spaces.
         if Item (I) /= ' ' then
            declare
               Position  : constant Natural
                 := (Character'Pos (Result (I)))-65;
               Enc_Char  : constant Character
                 := Character'Val (((Position + Key) mod 26)+65);
            begin
               Result (I) := Enc_Char;
            end;
         end if;
      end loop;

      return Result;
   end Encode;

end Caesar_Cipher;
