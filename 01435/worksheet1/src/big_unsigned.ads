package Big_Unsigned is

   type Unsigned_128 is mod 2**Long_Long_Integer'Size;

   One  : constant Unsigned_128;
   Zero : constant Unsigned_128;

   for Unsigned_128'Size use 128;

private
   One  : constant Unsigned_128 := 1;
   Zero : constant Unsigned_128 := 0;

end Big_Unsigned;
