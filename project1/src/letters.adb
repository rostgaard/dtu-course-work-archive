with Ada.Text_IO; use Ada.Text_IO;

with Decrypter.Trace;

package body Letters is

   procedure Show (Item : in  Character;
                   Ok   : out Boolean);

   procedure Frequency_Visitor is new Containers.Visit (Show);

   procedure Add (Letter : in Character) is
      Context : constant String := Package_Name & ".Add";
   begin
      Decrypter.Trace.Debug (Message => "Adding """ & Letter & """",
                             Context => Context);
      Frequencies.Add (Letter);
   end Add;

   function Frequency (Letter : in Character) return Float is
   begin
      return Float (Frequencies.Count (Letter)) /
        Float(Frequencies.Total_Size);
   end Frequency;

   function Hash_Character (Item : in Character) return Positive is
   begin
      return Character'Pos (Item);
   end Hash_Character;

   procedure Show (Item : in Character;
                   Ok   : out Boolean) is
   begin
      Put_Line (Item & " => " & Frequencies.Count (Item)'Img);
      Ok := True;
   end Show;

   procedure Show_Contents is
      Iterator : Containers.Iterator'Class
        := Frequencies.New_Iterator;
   begin
      Frequency_Visitor (Iterator);
   end Show_Contents;
end Letters;
