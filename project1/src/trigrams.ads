with Ada.Containers.Ordered_Maps;

package Trigrams is

   Package_Name : constant String := "Trigrams";

   procedure Show_Contents (Threshold : in Float := 0.0001);

   type Trigram_String is new String (1 .. 3);

   type Trigram is
      record
         Key   : Trigram_String;
         Count : Natural := 0;
      end record;

--     function "<" (Left, Right : in Trigram) return Boolean;
--     function "=" (Left, Right : in Trigram) return Boolean;

   procedure Image (Item : Trigram);

   type Ordered_Trigram_List is array (Natural range <>) of aliased Trigram;

--   function Value (C1, C2, C3 : in Character) return Trigram;

--     procedure Add (Item : in String) with
--       Precondition => Item'Length = 3;

   procedure Add (T : in Trigram_String);

   function Frequency (T : in Trigram_String) return Float;

private
   use Ada.Containers;

--     function Hash_Trigram (Item : in Trigram) return Hash_Type;
--     function Equivalent_Keys (Left, Right : Trigram) return Boolean;

   package Count_Storage is new Ada.Containers.Ordered_Maps
     (Key_Type     => Trigram_String,
      Element_Type => Trigram,
      "<"          => Trigrams."<",
      "="          => Trigrams."=");

   Frequencies : Count_Storage.Map;
end Trigrams;
