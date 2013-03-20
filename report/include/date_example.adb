procedure Date_Example is
   type Days_In_Month is range 1 .. 31;
   
   Day : Days_In_Month := 31;
   
begin
   Day := Day+1;
end Date_Example;
