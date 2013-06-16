with Ada.Containers.Hashed_Maps;
with Ada.Containers.Vectors;

package Letters is

   Package_Name : constant String := "Letters";

   type Letter is new String (1 .. 1);

   type Letter_Frequency is
      record
         Key       : Letter;
         Frequency : Float := 0.0;
      end record;

   procedure Add (C : in Character);

   procedure Add (C : in Letter);

   procedure Clear;

--   procedure Image (Item : in Letter);

   function Image (Item : in Letter_Frequency) return String;

   function Frequency (C : in Letter) return Float;

   package Frequency_Count is new Ada.Containers.Vectors
     (Index_Type   => Natural,
      Element_Type => Letter_Frequency);

   function To_Ordered_Table (Reverse_Order : in Boolean := False)
                              return Frequency_Count.Vector;

private
   use Ada.Containers;

   function Hash_Letter (Item : in Letter) return Hash_Type;

   function Equivalent_Keys (Left, Right : in Letter) return Boolean;

   package Character_Counter is new Ada.Containers.Hashed_Maps
     (Key_Type        => Letter,
      Hash            => Hash_Letter,
      Equivalent_Keys => Equivalent_Keys,
      Element_Type    => Natural);

   Frequencies : Character_Counter.Map;
end Letters;
