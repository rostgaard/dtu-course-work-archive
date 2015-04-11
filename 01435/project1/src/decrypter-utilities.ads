with N_Grams;

package Decrypter.Utilities is

   package Letters is new N_Grams (N => 1);
   package Digrams is new N_Grams (N => 2);
   package Trigrams is new N_Grams (N => 3);

   type File_Statistics is
      record
         Letter  : Letters.Frequency_Count.Vector;
         Digram  : Digrams.Frequency_Count.Vector;
         Trigram : Trigrams.Frequency_Count.Vector;
      end record;

   function Read_From_File (Filename : in String) return File_Statistics;

   function Buffer_From_File (Filename : in String) return String;

   function To_Upper (Item : in Character) return Character;

   function To_Lower (Item : in String) return String;

end Decrypter.Utilities;
