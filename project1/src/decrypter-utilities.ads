with Letters;
with Digrams;
with Trigrams;

package Decrypter.Utilities is

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
