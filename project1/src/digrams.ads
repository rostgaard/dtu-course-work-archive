with Ada.Containers.Hashed_Maps;
with Ada.Containers.Vectors;

package Digrams is

   Package_Name : constant String := "Digrams";

   type Digram is new String (1 .. 2);

   type Digram_Frequency is
      record
         Key       : Digram;
         Frequency : Float := 0.0;
      end record;

   function Image (Item : in Digram_Frequency) return String;

   procedure Add (D : in Digram);

   procedure Clear;

   function Frequency (D : in Digram) return Float;

   package Frequency_Count is new Ada.Containers.Vectors
     (Index_Type   => Natural,
      Element_Type => Digram_Frequency);

   function To_Ordered_Table (Reverse_Order : in Boolean := False)
                              return Frequency_Count.Vector;

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
