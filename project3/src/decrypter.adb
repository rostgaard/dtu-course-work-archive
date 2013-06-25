package body Decrypter is

   function Encode (Item : in Character;
                    Key  : in Keys;
                    I    : in Natural) return Character is
   begin
      return Character'Val
        (Character'Pos (Item) xor Key (I mod 16));

   end Encode;

   function Encrypt (Plaintext : in String;
                     Key       : in Keys) return Cipher_Text is
      C : Cipher_Text (Plaintext'Range);

      P : String renames Plaintext;
   begin
      for I in Plaintext'Range loop
         C (I) := Encode (Item => P (I),
                          Key  => Key,
                          I    => I);
      end loop;

      return C;
   end Encrypt;

   function Update (S : Unsigned_64) return Unsigned_64 is
   begin
      return (69_069 * S + 5) mod 2**32;
   end Update;

end Decrypter;
