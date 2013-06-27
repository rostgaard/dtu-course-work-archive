with Ada.Containers.Hashed_Maps;
with Ada.Containers.Hashed_Sets;

with Unsigned_Types;

package Rainbow_Table is
   use Unsigned_Types;
   use Ada.Containers;

   type Instance is private;

   function Create (Length : Unsigned_20 := 2**10;
                    Rows   : Unsigned_20 := 2**18) return Instance;

private

   function Hash (Item : in Unsigned_32) return Hash_Type;

   function Hash (Item : in Unsigned_20) return Hash_Type;

   function Equivalent_Elements (Left, Right : in Unsigned_20)
                                 return Boolean;

   function Equivalent_Elements (Left, Right : in Unsigned_32)
                                 return Boolean;

   function Equivalent_Keys (Left, Right : in Unsigned_32)
                             return Boolean;

   function Equivalent_Keys (Left, Right : in Unsigned_20)
                             return Boolean;

   package Value_Storage is new Ada.Containers.Hashed_Sets
     (Element_Type        => Unsigned_32,
      Hash                => Hash,
      Equivalent_Elements => Equivalent_Elements);

   package Table_Storage is new Ada.Containers.Hashed_Maps
     (Key_Type        => Unsigned_32,
      Element_Type    => Value_Storage.Set,
      Hash            => Hash,
      Equivalent_Keys => Equivalent_Keys,
      "="             => Value_Storage."=");

   type Instance is
      record
         Rows        : Unsigned_20;
         ChainLength : Unsigned_20;
         Table       : Table_Storage.Map;
      end record;

end Rainbow_Table;
