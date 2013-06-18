with Ada.Calendar;  use Ada.Calendar;
with Ada.Text_IO;   use Ada.Text_IO;
with Big_Unsigned;  use Big_Unsigned;

procedure Exercise_05 is

   function Encrypt (Key   : in Unsigned_128;
                     Value : in Unsigned_128) return Unsigned_128;

   function Encrypt (Key   : in Unsigned_128;
                     Value : in Unsigned_128) return Unsigned_128 is
   begin

      return Key xor Value;
   end Encrypt;

   Count1 : Unsigned_128 := 0;
   Count2 : Unsigned_128 := 0;
   Count3 : Unsigned_128 := 0;
   Count4 : Unsigned_128 := 0;
   Key    : Unsigned_128 := 10_00_00_000_000;
   Test   : constant Unsigned_128 := 23455673624214 xor Key;
   Res    : Unsigned_128 := 1;
   Found  : Boolean := False;
   pragma Atomic (Found);

   Start : Ada.Calendar.Time;

   task Processor1;

   task body Processor1 is   begin
      for I in Unsigned_128'First .. Unsigned_128'Last / 4 loop
         exit when Found;

         Res := Encrypt (Key   => I,
                         Value => Test);

         Count1 := Count1 + 1;

         if  Res = 23455673624214 then
            Found := True;
            Put_Line ("Task 1 found key!");
         end if;
      end loop;
   end Processor1;

   task Processor2;

   task body Processor2 is  begin
      for I in Unsigned_128'Last / 4 .. 2*(Unsigned_128'Last / 4) loop
         exit when Found;

         Res := Encrypt (Key   => I,
                         Value => Test);

         Count2 := Count2 + 1;

         if  Res = 23455673624214 then
            Found := True;
            Put_Line ("Task 2 found key!");
         end if;
      end loop;
   end Processor2;

   task Processor3;

   task body Processor3 is  begin
      for I in 2*(Unsigned_128'Last / 4) .. 3*(Unsigned_128'Last / 4) loop
         exit when Found;

         Res := Encrypt (Key   => I,
                         Value => Test);

         Count3 := Count3 + 1;

         if  Res = 23455673624214 then
            Found := True;
            Put_Line ("Task 3 found key!");
         end if;
      end loop;
   end Processor3;

   task Processor4;

   task body Processor4 is   begin
      for I in 3*(Unsigned_128'Last / 4) .. Unsigned_128'Last loop
         exit when Found;

         Res := Encrypt (Key   => I,
                         Value => Test);

         Count4 := Count4 + 1;

         if  Res = 23455673624214 then
            Found := True;
            Put_Line ("Task 4 found key!");
         end if;
      end loop;
   end Processor4;
begin
   Start := Clock;

   Put_Line ("Starting:");

   loop
      exit when Found;
      declare
         Count   : Unsigned_128      := (Count1 + Count2 + Count3 + Count4);
         Runtime : constant Duration := Ada.Calendar.Clock - Start;
         Tmp     : constant Duration := Duration (Float (Count)) / Runtime;
      begin
         Put_Line ("Processed" & Count'Img & " decryptions in" &
                     Runtime'Img & " seconds (" & Tmp'Img &
                     " decryptions/s).");
      end;
      delay 2.0;
   end loop;

   Put_Line ("Key is:" & Key'Img);

end Exercise_05;
