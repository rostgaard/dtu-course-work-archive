with BC.Containers;
with BC.Containers.Bags;
with BC.Containers.Bags.Bounded;

package Letters is

   Package_Name : constant String := "Letters";

   procedure Add (Letter : in Character);

   function Frequency (Letter : in Character) return Float;

private

   package Containers is new BC.Containers
     (Item => Character);

   package Bags is new Containers.Bags;

   function Hash_Character (Item : in Character) return Positive;

   package Character_Counter is new
     Bags.Bounded (Hash         => Hash_Character,
                   Buckets      => 1,
                   Maximum_Size => Character'Pos (Character'Last));

   Frequencies : Character_Counter.Bag;
end Letters;
