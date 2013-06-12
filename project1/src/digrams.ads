with BC.Containers;
with BC.Containers.Bags;
with BC.Containers.Bags.Bounded;

package Digrams is

   Package_Name : constant String := "Digrams";

   type Digram is array (1 .. 2) of Character;

   function Value (C1, C2 : in Character) return Digram;

   procedure Show_Contents;

   procedure Add (Letter : in Digram);

   function Frequency (Letter : in Digram) return Float;

private

   package Containers is new BC.Containers
     (Item => Digram);

   package Bags is new Containers.Bags;

   function Hash_Digram (Item : in Digram) return Positive;

   package Digram_Counter is new
     Bags.Bounded (Hash         => Hash_Digram,
                   Buckets      => 1,
                   Maximum_Size => (Character'Pos (Character'Last))**2);

   Frequencies : Digram_Counter.Bag;
end Digrams;
