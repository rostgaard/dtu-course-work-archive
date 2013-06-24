with Unsigned_Types;

package Key_Utilities is
   use Unsigned_Types;

   type Bytes is mod 2**8;
   type Keys is array (0 .. 15) of Bytes;

   function Is_Printable (C : in Character) return Boolean;
   --  Denotes if the character is printable.

   function Image (Item : in Keys) return String;

   function Value (Item : in Keys) return Unsigned_128;

   function Generate_Key (Seed : Unsigned_64) return Keys;
   --  Builds a key from an intial seed value by continously calling "update".

end Key_Utilities;
