package body Decrypter is

   function Update (S : Unsigned_64) return Unsigned_64 is
   begin
      return (69_069 * S + 5) mod 2**32;
   end Update;

end Decrypter;
