with Unsigned_Types;

package Key_Utilities is
   use Unsigned_Types;

   type Bytes is mod 2**8;
   type Keys is array (0 .. 15) of Bytes;
   --  Given that we never need to do arithmetic operations on keys,
   --  A byte array suits the purpose nicely.

   function Is_Printable (C : in Character) return Boolean;
   --  Denotes if the character is printable.

   function Image (Item : in Keys) return String;
   --  Hexadecimal string represenatation of a key.

   function Value (Item : in Keys) return Unsigned_64;
   --  Gives out the value of the 64 least-significant bits.
   pragma Obsolescent (Value, "Will be replaced with 128-bit version.");

   function Generate_Key (Seed : Unsigned_64) return Keys;
   --  Builds a key from an intial seed value by continously calling "update".

end Key_Utilities;
