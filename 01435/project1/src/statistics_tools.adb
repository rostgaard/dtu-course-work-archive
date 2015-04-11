package Statistics_Tools is

   function Chi_Squared (Test_Set : in Test_Sets) return Float is
      Sum : Float := 0.0;
   begin
      for I in Test_Set'Range loop

         Sum := Sum + ((Test_Set (I).Value - Test_Set (I).Expected)**2
                       / Test_Set (I).Expected);
      end loop;

      return Sum;
   end Chi_Squared;

end package Statistics_Tools;

