with Unsigned_Types;
with Key_Utilities;

package Decrypter is
   use Unsigned_Types;
   use Key_Utilities;

   subtype Cipher_Text is String;

   function Encode (Item : in Character;
                    Key  : in Keys;
                    I    : in Natural) return Character;

   function Encrypt (Plaintext : in String;
                     Key       : in Keys) return Cipher_Text;

      function Update (S : Unsigned_64) return Unsigned_64;
   --  Progress the internal state S of

   function Decrypt (Ciphertext : in String;
                     Key        : in Keys) return String renames Encrypt;

end Decrypter;
