package Decrypter is

   subtype Basic_Characters is Character range 'A' .. '_';

   type Encoding_Table is array (Basic_Characters) of Basic_Characters;

   function Decrypt (Cipher_Text : in String;
                      Encodings   : in Encoding_Table) return String;

end Decrypter;
