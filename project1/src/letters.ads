
with Ada.Containers.Ordered_Maps;

package Letters is

   Package_Name : constant String := "Letters";

   procedure Show_Contents (Threshold : in Float := 0.0001);

   type Letter is
      record
         Letter : Character;
         Count  : Natural := 0;
      end record;

   procedure Add (C : in Character);

   function Frequency (C : in Character) return Float;

private

   function Hash_Character (Item : in Character) return Positive;

   package Character_Counter is new Ada.Containers.Ordered_Maps
     (Key_Type     => Character,
      Element_Type => Letter);

   Frequencies : Character_Counter.Map;
end Letters;
