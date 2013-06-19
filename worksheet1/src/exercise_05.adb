with Ada.Calendar;     use Ada.Calendar;
with Ada.Text_IO;      use Ada.Text_IO;
with Ada.Command_Line; use Ada.Command_Line;
procedure Exercise_05 is

   type Unsigned_128 is mod 2**Long_Long_Integer'Size;

   Max_Tasks : constant Unsigned_128 := 256;

   function Encrypt (Key   : in Unsigned_128;
                     Value : in Unsigned_128) return Unsigned_128 is
   begin

      return Key xor Value;
   end Encrypt;

   Count     : array (1 .. 4) of Unsigned_128 := (others => 0);
   Plain     : constant Unsigned_128 := 23455673624214;
   Key       : Unsigned_128 := 9223372036884775826;
   Test      : constant Unsigned_128 := Plain xor Key;
   Res       : Unsigned_128 := 1;
   Found     : Boolean := False;
   Found_Key : Unsigned_128 := 0;

   pragma Atomic (Found);

   Start : Ada.Calendar.Time;

   task type Processor (ID    : Natural;
                        First : Unsigned_128;
                        Last  :  Unsigned_128);

   task body Processor is
   begin
      Put_Line ("Starting task" & ID'Img & " - decrypting from" &
                  First'Img & " to" & Last'Img);

      for I in First .. Last loop
         exit when Found;

         Res := Encrypt (Key   => I,
                         Value => Test);

         Count (ID) := Count (ID) + 1;

         if Res = Plain then
            Found     := True;
            Found_Key := I;
            Put_Line ("Task" & ID'Img &" Found key!");
         end if;
      end loop;
   end Processor;

   Num_Tasks : Unsigned_128 := 4;
   Tasks     : array (Unsigned_128 range 1 .. Max_Tasks) of access Processor;
begin

   if Argument_Count > 0 then
      Num_Tasks := Unsigned_128'Value (Argument (1));
      if Num_Tasks > Max_Tasks then
         Put_Line ("Too many tasks!");
         return;
      end if;
   end if;

   Put_Line ("Starting:" & Num_Tasks'Img & " tasks;");
   Start := Clock;

   for I in 1 .. Num_Tasks loop
      declare
         Total : constant Unsigned_128 := Unsigned_128'Last / Num_Tasks;
      begin
         Put_Line ("Starting task" & I'Img);
         Tasks (I) := new Processor
           (ID    => Natural (I),
            First => (I - 1) * Total,
            Last  => (if I = Num_Tasks then I * Total else (I * Total)-1));
      end;
   end loop;
   loop
      exit when Found;
      declare
         Processed : Unsigned_128 := 0;
         Runtime   : constant Duration := Ada.Calendar.Clock - Start;
         Tmp       : Duration := 0.0;
      begin
         for Item of Count loop
            Processed := Processed + Item;
         end loop;

         Tmp := Duration (Float (Processed)) / Runtime;

         Put_Line ("Processed" & Processed'Img & " decryptions in" &
                     Runtime'Img & " seconds (" & Tmp'Img &
                     " decryptions/s).");
      end;
      delay 2.0;
   end loop;

   Put_Line ("Key is:" & Found_Key'Img & " (expected" & Key'Img & ")");

end Exercise_05;
