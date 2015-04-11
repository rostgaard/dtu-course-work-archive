with Ada.Text_IO; use Ada.Text_IO;
with Ada.Containers.Hashed_Sets;

package body Definition_Map is
   use Unsigned_Types;
   use Ada.Containers;

   function Hash (Item : in Unsigned_20) return Hash_Type is
   begin
      return Hash_Type (Item);
   end Hash;

   function Equivalent_Elements (Left, Right : in Unsigned_20)
                                 return Boolean is
   begin
      return Left = Right;
   end Equivalent_Elements;

   package Count_Storage is new
     Ada.Containers.Hashed_Sets (Element_Type => Unsigned_20,
                                 Hash          => Hash,
                                 Equivalent_Elements => Equivalent_Elements);

   Count : Count_Storage.Set;
   Num_Collisions : Natural := 0;
   Total : Natural := 0;

   procedure Add (Item : in Unsigned_Types.Unsigned_20) is
   begin
      if not Count.Contains (Item) then
         Count.Insert (New_Item => Item);
         Total := Total + 1;
      else
         Num_Collisions := Num_Collisions + 1;
      end if;
   end Add;

   function Collisions return Natural is
   begin
      return Num_Collisions;
   end Collisions;

   function Coverage return Float is
   begin
      return Float (Total) / Float (Unsigned_20'Last);
   end Coverage;

end Definition_Map;
