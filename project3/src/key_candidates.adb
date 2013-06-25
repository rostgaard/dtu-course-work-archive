with Ada.Strings.Hash_Case_Insensitive;
with Ada.Strings.Equal_Case_Insensitive;

package body Key_Candidates is

   procedure Add (Key : in Keys) is
   begin
      if not Key_List.Contains (Key) then
         Key_List.Insert (Key);
         Key_Count := Key_Count + 1;
      end if;
   end Add;
   function Equivalent_Elements (Left, Right : in Keys) return Boolean is
   begin

      return Ada.Strings.Equal_Case_Insensitive (Left  => Image (Left),
                                                 Right => Image (Right));
   end Equivalent_Elements;

   function Hash (Key : in Keys) return Hash_Type is
   begin
      return Ada.Strings.Hash_Case_Insensitive (Image (Key));
   end Hash;

   function List return Storage.Set is
   begin
      return Key_List;
   end List;

   function Unique_Keys return Natural is
   begin
      return Key_Count;
   end Unique_Keys;

end Key_Candidates;
