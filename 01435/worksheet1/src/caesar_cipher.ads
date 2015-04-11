package Caesar_Cipher is

   function Encode (Item  : in String;
                    Key   : in Positive) return String;

   function Decode (Item : in String;
                    Key   : in Positive) return String;

end Caesar_Cipher;
