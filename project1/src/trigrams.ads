with BC.Containers;
with BC.Containers.Bags;
with BC.Containers.Bags.Bounded;

package Trigrams is

   Package_Name : constant String := "Trigrams";

   procedure Show_Contents;

   type Trigram is array (1 .. 3) of Character;

   function Value (C1, C2, C3 : in Character) return Trigram;

--     procedure Add (Item : in String) with
--       Precondition => Item'Length = 3;

   procedure Add (T : in Trigram);

   function Frequency (T : in Trigram) return Float;

private

   package Containers is new BC.Containers
     (Item => Trigram);

   package Bags is new Containers.Bags;

   function Hash_Trigram (Item : in Trigram) return Positive;

   package Trigram_Counter is new
     Bags.Bounded (Hash         => Hash_Trigram,
                   Buckets      => 1,
                   Maximum_Size => (Character'Pos (Character'Last))**3);

   Frequencies : Trigram_Counter.Bag;
end Trigrams;
