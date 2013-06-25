
package Unsigned_Types is

   type Unsigned_64 is mod 2 ** 64;

   type Unsigned_128 is mod 2 ** Long_Long_Integer'Size;
   --for Unsigned_128'Size use 128;
   --  pragma Assert (Unsigned_128'Size = 128);

end Unsigned_Types;
