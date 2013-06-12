with Ada.Calendar;  use Ada.Calendar;
with Ada.Text_IO;   use Ada.Text_IO;
with Big_Unsigned;  use Big_Unsigned;

procedure Exercise_05 is

   function Encrypt (Key   : in Unsigned_128;
                     Value : in Unsigned_128) return Unsigned_128;

   function Encrypt (Key   : in Unsigned_128;
                     Value : in Unsigned_128) return Unsigned_128 is
      Result : Unsigned_128;
   begin
      for I in Value'Range loop
         Result (I) := Key (I) xor Value (I);
      end loop;

      return Result;
   end Encrypt;

   Test : constant Unsigned_128 := (True, True, False, others => True);
   Key  : Unsigned_128 := (False, True, False, others => True);
   Res  : Unsigned_128 := (False, True, False, others => True);

   Count : Natural := 0;
   Start : Ada.Calendar.Time;

begin
   Key := Zero;
   Start := Clock;

   loop
      exit when Res = Key;
      Res := Encrypt (Key   => Key,
                      Value => Test);
      Key := Key + One;
      Count := Count + 1;

      if Count mod 1_000_000 = 0 then
         declare
            Runtime : constant Duration := Ada.Calendar.Clock - Start;
            Tmp     : constant Duration := Duration (Float (Count)) / Runtime;
         begin
            Put_Line ("Processed" & Count'Img & " decryptions in" &
                        Runtime'Img & " seconds (" & Tmp'Img &
                        " decryptions/s).");
            Count := 0;
            Start := Clock;
         end;
      end if;

   end loop;

   Put_Line ("Key is:" & Image (Res));

end Exercise_05;
