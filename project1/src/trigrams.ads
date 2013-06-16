with Ada.Containers.Ordered_Maps;
with Ada.Containers.Vectors;

package Trigrams is

   Package_Name : constant String := "Trigrams";

   procedure Show_Contents (Threshold : in Float := 0.0001);

   type Trigram_String is new String (1 .. 3);

   procedure Clear;

   type Trigram is
      record
         Key   : Trigram_String;
         Count : Natural := 0;
      end record;

   type Trigram_Frequency is
      record
         Key       : Trigram_String;
         Frequency : Float := 0.0;
      end record;

--     function "<" (Left, Right : in Trigram) return Boolean;
--     function "=" (Left, Right : in Trigram) return Boolean;

   procedure Image (Item : Trigram);

   function Image (Item : in Trigram_Frequency) return String;

   package Frequency_Count is new Ada.Containers.Vectors
     (Index_Type   => Natural,
      Element_Type => Trigram_Frequency);

   function To_Ordered_Table (Reverse_Order : in Boolean := False)
                              return Frequency_Count.Vector;

   procedure Add (T : in Trigram_String);

   function Frequency (T : in Trigram_String) return Float;

private
   use Ada.Containers;

   package Count_Storage is new Ada.Containers.Ordered_Maps
     (Key_Type     => Trigram_String,
      Element_Type => Trigram,
      "<"          => Trigrams."<",
      "="          => Trigrams."=");

   Frequencies : Count_Storage.Map;
end Trigrams;
