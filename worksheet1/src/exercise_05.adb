with Ada.Calendar;      use Ada.Calendar;
with Ada.Text_IO;       use Ada.Text_IO;
with Ada.Float_Text_IO; use Ada.Float_Text_IO;
with Ada.Command_Line;  use Ada.Command_Line;
with Ada.Exceptions;    use Ada.Exceptions;
procedure Exercise_05 is

   type Unsigned_128 is mod 2**Long_Long_Integer'Size;

   Max_Tasks : constant Unsigned_128 := 256;

   function Encrypt (Key   : in Unsigned_128;
                     Value : in Unsigned_128) return Unsigned_128 is
   begin

      return Key xor Value;
   end Encrypt;

   procedure Clear is
   begin
      Put (ASCII.ESC & "]" & ASCII.LF & ASCII.ESC & "[F" & ASCII.ESC & "[J");
   end Clear;

   function Decrypt (Key   : in Unsigned_128;
                     Value : in Unsigned_128) return Unsigned_128
                     renames Encrypt;

   Count     : array (Unsigned_128 range 1 .. Max_Tasks)
     of Unsigned_128 := (others => 0);
   Plain     : constant Unsigned_128 := 23455673624214;
   Key       : constant Unsigned_128 := 80_000_000;
   Cipher    : constant Unsigned_128 := Encrypt (Key   => Key,
                                                 Value => Plain);
   Start     : Ada.Calendar.Time;
   Found     : Boolean := False;
   Found_Key : Unsigned_128 := 0;
   Processed : Unsigned_128 := 0;

   pragma Atomic (Found);

   task type Processor (ID    : Natural;
                        First : Unsigned_128;
                        Last  :  Unsigned_128);

   task body Processor is
      Test : Unsigned_128;
   begin
      for I in First .. Last loop
         exit when Found;

         Test := Decrypt (Key   => I,
                          Value => Cipher);

         Count (Unsigned_128 (ID)) := Count (Unsigned_128 (ID)) + 1;

         if Test = Plain then
            Found     := True;
            Found_Key := I;
            New_Line;
            Put_Line ("Task" & ID'Img &" Found key!");
         end if;
      end loop;
   exception
      when E : others =>
         Put_Line ("Task" & ID'Img & " died with " &
                     Exception_Information (E));

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

   Put_Line ("Using" & Num_Tasks'Img & " tasks.");
   Start := Clock;

   for I in 1 .. Num_Tasks loop
      declare
         Total : constant Unsigned_128 := Unsigned_128'Last / Num_Tasks;
      begin
         Tasks (I) := new Processor
           (ID    => Natural (I),
            First => (I - 1) * Total,
            Last  => (if I = Num_Tasks then Unsigned_128'Last
                      else (I * Total)-1));
      end;
   end loop;

   loop
      exit when Found or (Processed = Unsigned_128'Last);
      declare
         Runtime   : constant Duration := Ada.Calendar.Clock - Start;
         Tmp       : Duration := 0.0;
      begin
         Processed := 0;
         for Item of Count loop
            Processed := Processed + Item;
         end loop;

         Tmp := Duration (Float (Processed)) / Runtime;
         Clear;
         Put ("Processed" & Natural(Processed/1_000_000)'Img &
                "M decryptions in ");
         Put (Item => Float (Runtime),
              Aft  => 1,
              Exp  => 0);
         Put (" seconds (");
         Put (Item => Float (Tmp),
              Aft  => 2);
         Put (" decryptions/s).");
      end;
      delay 0.1;
   end loop;

   Put_Line ("Key is:" & Found_Key'Img & " (expected" & Key'Img & ")");

end Exercise_05;
