
with Decrypter.Trace;

package body Letters is

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

end Letters;
