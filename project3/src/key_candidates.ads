with Ada.Containers.Hashed_Sets;

with Key_Utilities;

package Key_Candidates is
   use Key_Utilities;
   use Ada.Containers;

   function Hash (Key : in Keys) return Hash_Type;

   function Equivalent_Elements (Left, Right : in Keys) return Boolean;

   package Storage is new Ada.Containers.Hashed_Sets
     (Element_Type        => Keys,
      Hash                => Hash,
      Equivalent_Elements => Equivalent_Elements);

   function Unique_Keys return Natural;

   procedure Add (Key : in Keys);

   function List return Storage.Set;

private

   Key_List  : Storage.Set;
   Key_Count : Natural := 0;
end Key_Candidates;
