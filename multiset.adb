package body Multiset is
   procedure Insert (Item : in Element) is
      Index : Natural := Find (Item);
   begin
      if Index = 0 then
         Storage (Next_Free) := (Item, 1);
      else
         Storage (Index).Occurence := Storage (Index).Occurence + 1;
      end if;
         
   end Insert;
   
   function Occurences (Item : in Element) return Count is
      Index : Natural := Find (Item);
   begin
      if Index /= 0 then
         return Storage (Find (Item)).Occurence;
      end if;
      
      return 0;
   end Occurences;
      
   function Find (Item : in Element) return Natural is
   begin
      for I in 1 .. Max_Elements loop
         if Storage (I).Value = Item then
            return I;
         end if;
      end loop;
      
      return 0;
   end Find;
   
   procedure Remove (Item : in Element) is
      Index : Natural := Find (Item);
   begin
      Storage (Index).Occurence := Storage (Index).Occurence + 1;
   end Remove;
   
   function Next_Free return Natural is
   begin
      for I in 1 .. Max_Elements loop
         if Storage (I) = Null_Set_Element then
            return I;
         end if;
      end loop;
         return 0;
   end Next_Free;
   
end Multiset;
