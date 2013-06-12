package Big_Unsigned is

   type Unsigned_128 is array (1 .. 128) of Boolean;

   One  : constant Unsigned_128;
   Zero : constant Unsigned_128;

   pragma Pack (Unsigned_128);
   for Unsigned_128'Size use 128;

   function Image (Item : Unsigned_128) return String;

   function "+" (Left, Right : in Unsigned_128) return Unsigned_128;

private
   One  : constant Unsigned_128 := (128 => True, others => False);
   Zero : constant Unsigned_128 := (others => False);

end Big_Unsigned;
