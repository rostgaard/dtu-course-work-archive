with Ada.Containers.Hashed_Maps;

package Digrams is

   Package_Name : constant String := "Digrams";

   type Digram is array (1 .. 2) of Character;

   procedure Show_Contents (Threshold : in Float := 0.0001);

   procedure Add (D : in Digram);

   function Frequency (D : in Digram) return Float;

private
   use Ada.Containers;

   function Hash_Trigram (Item : in Digram) return Hash_Type;
   function Equivalent_Keys (Left, Right : Digram) return Boolean;

   package Count_Storage is new Ada.Containers.Hashed_Maps
     (Key_Type        => Digram,
      Element_Type    => Natural,
      Hash            => Hash_Trigram,
      Equivalent_Keys => Equivalent_Keys);

   Frequencies : Count_Storage.Map;
end Digrams;
