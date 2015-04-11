with Ada.Text_IO; use Ada.Text_IO;

with Multiset;

procedure Crude_Containers.Multiset.Test is
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

   begin
      Bag.Insert ("AA");
      raise Assert_Failure with "Expected exception";
   exception
      when others =>
         Put_Line ("Exception raised successfully.");
   end;

end Crude_Containers.Multiset.Test;
