package body Decrypter is

   function Decrypt (Cipher_Text : in String;
                     Codebook    : in Codebooks) return String is
      Buffer : String (Cipher_Text'Range);
   begin
      for Index in Cipher_Text'Range loop
         Buffer (Index) := Codebook (Cipher_Text (Index));
      end loop;

      return Buffer;
   end Decrypt;

end Decrypter;
