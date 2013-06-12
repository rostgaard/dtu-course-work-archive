with Ada.Strings.Hash;

with Decrypter.Trace;

package body Digrams is

   procedure Add (Letter : in Digram) is
      Context : constant String := Package_Name & ".Add";
   begin
      Decrypter.Trace.Debug (Message => "Adding """ & String (Letter) & """",
                             Context => Context);
      Frequencies.Add (Letter);
   end Add;

   function Frequency (Letter : in Digram) return Float
   is
   begin
      return Float (Frequencies.Count (Letter)) /
        Float (Frequencies.Total_Size);
   end Frequency;

   function Hash_Digram (Item : in Digram) return Positive is
   begin
      return Positive (Ada.Strings.Hash (Key => Item (1) & Item (2)));
   end Hash_Digram;

   function Value (C1, C2 : in Character) return Digram is
   begin
      return (1 => C1, 2 => C2);
   end Value;

end Digrams;
