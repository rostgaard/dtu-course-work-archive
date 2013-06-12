with Ada.Strings.Hash;
with Ada.Text_IO; use Ada.Text_IO;

with Decrypter.Trace;

package body Trigrams is

   procedure Show (Item : Trigram;
                   Ok   : out Boolean);

   procedure Frequency_Visitor is new Containers.Visit (Show);

   procedure Add (T : in Trigram) is
      Context : constant String := Package_Name & ".Add";
   begin
      Decrypter.Trace.Debug (Message => "Adding """ & String (T) & """",
                             Context => Context);
      Frequencies.Add (T);
   end Add;

   function Frequency (T : in Trigram) return Float
   is
   begin
      return Float (Frequencies.Count (T)) /
        Float (Frequencies.Total_Size);
   end Frequency;

   function Hash_Trigram (Item : in Trigram) return Positive is
   begin
      return Positive
        (Ada.Strings.Hash (Key => Item (1) & Item (2) & Item (3)));
   end Hash_Trigram;

   procedure Show (Item : Trigram;
                   Ok   : out Boolean) is
   begin
      Put_Line (String (Item) & " => " & Frequencies.Count (Item)'Img);
      Ok := True;
   end Show;

   procedure Show_Contents is
      Iterator : Containers.Iterator'Class
        := Frequencies.New_Iterator;
   begin
      Frequency_Visitor (Iterator);
   end Show_Contents;

   function Value (C1, C2, C3 : in Character) return Trigram is
   begin
      return (1 => C1, 2 => C2, 3 => C3);
   end Value;

end Trigrams;
