--  N-Gram package exploiting multiset properties to count frequencies.

with Ada.Containers.Hashed_Maps;
with Ada.Containers.Vectors;

generic
   N : Positive;  --  How many characters should the N-Gram contain.

package N_Grams is

   type Element_String is new String (1 .. N);

   procedure Add (Item : in Element_String);
   --  Inserts a N-Gram into the multiset.

   procedure Clear;
   --  Resets the internal state.

   type Frequency is
      record
         Key       : Element_String;
         Frequency : Float := 0.0;
      end record;

   function Image (Item : in Frequency) return String;
   --  String represenatation of a frequency.

   package Frequency_Count is new Ada.Containers.Vectors
     (Index_Type   => Natural,
      Element_Type => Frequency);
   --  Storage package for frequencies.

   function To_Ordered_Table (Reverse_Order : in Boolean := False)
                              return Frequency_Count.Vector;
   --  Order the N-Grams by frequency into a list.

private
   use Ada.Containers;

   type Element is
      record
         Key   : Element_String;
         Count : Natural := 0;
      end record;

   function Calculate_Frequency (Item : in Element_String) return Float;

   function Hash (Item : in Element_String) return Hash_Type;

   function Equivalent_Keys (Left, Right : in Element_String) return Boolean;

   package Count_Storage is new Ada.Containers.Hashed_Maps
     (Key_Type        => Element_String,
      Element_Type    => Element,
      Hash            => Hash,
      Equivalent_Keys => Equivalent_Keys);

   Frequencies : Count_Storage.Map;
end N_Grams;
