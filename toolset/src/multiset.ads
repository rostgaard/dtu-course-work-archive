--  Crude implementation of a multiset.

generic
   type Element is private;
   Null_Element : Element;
   Max_Elements : Positive;

   type Count is range <>;

package Multiset is

   procedure Insert (Item : in Element);
   function Occurences (Item : in Element) return Count;
   procedure Remove (Item : in Element);
private

   type Set_Element is
      record
         Value     : Element := Null_Element;
         Occurence : Count := Count'First;
      end record;

   Null_Set_Element : Set_Element := (Null_Element, Count'First);

   function Find (Item : in Element) return Natural;

   function Next_Free return Natural;

   Storage : array (1 .. Max_Elements) of Set_Element;
end Multiset;
