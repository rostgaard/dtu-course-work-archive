with Ada.Text_IO; use Ada.Text_IO;

with Multiset;

procedure Multiset_Test is
   type Foo is range 0 .. 2;

   type Two_Letter_String is new String (1 .. 2);
   package Bag is new Multiset (Element      => Two_Letter_String,
                                Null_Element => "00",
                                Max_Elements => 8,
                                Count        => Foo);
begin
   Put_Line ("The string AA is contained" &
               Bag.Occurences ("AA")'Img & " times in the bag");
   Bag.Insert ("AA");

   Put_Line ("The string AA is contained" &
               Bag.Occurences ("AA")'Img & " times in the bag");
   Bag.Insert ("AA");

   Put_Line ("The string AA is contained" &
               Bag.Occurences ("AA")'Img & " times in the bag");
end Multiset_Test;
