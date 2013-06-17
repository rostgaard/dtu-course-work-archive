package Decrypter is

   subtype Basic_Characters is Character range 'A' .. '_';

   type Codebooks is array (Basic_Characters) of Basic_Characters;

   function Decrypt (Cipher_Text : in String;
                     Codebook    : in Codebooks) return String;

end Decrypter;
