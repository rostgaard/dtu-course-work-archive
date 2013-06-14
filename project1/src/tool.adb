with Ada.Text_IO; use Ada.Text_IO;
with Ada.Float_Text_IO; use Ada.Float_Text_IO;
with Ada.Streams.Stream_IO;
with Ada.Command_Line; use Ada.Command_Line;
with Ada.Streams; use Ada.Streams;
with Ada.Characters.Handling; use Ada.Characters.Handling;
with Ada.Directories;  use Ada.Directories;
with Ada.Calendar;
with Letters;
with Digrams;
with Trigrams;

with Decrypter.Trace;

procedure Tool is
   use Letters;
   use Digrams;
   use Trigrams;

   File         : Ada.Streams.Stream_IO.File_Type;
   Stream  : Ada.Streams.Stream_IO.Stream_Access;

   procedure Usage;

   function Is_Character (C : in Character) return Boolean;

   function Is_Character (C : in Character) return Boolean is
   begin
      return C in Character'('A') .. Character'('Z');
   end Is_Character;

   procedure Usage is
   begin
      Put_Line ("Usage " & Command_Name & " plaintext_file");
   end Usage;

   Buffer     : String (1 .. 3) := (others => ' ');
   Char_Count : Natural := 0;
   Char       : Character := ' ';
   Bytes      : File_Size :=  0;
   Total      : File_Size := 0;

   Start : Ada.Calendar.Time;

begin

   if Argument_Count < 1 then
      Usage;
      return;
   elsif Argument_Count > 1 and then Argument (2) = "--debug" then

      Decrypter.Trace.Unmute (Trace => Decrypter.Trace.Debug);

   end if;

   Ada.Streams.Stream_IO.Open
     (File => File,
      Name => Argument (1),
      Mode => Ada.Streams.Stream_IO.In_File);

   Total := Size (Argument (1));

   Stream := Ada.Streams.Stream_IO.Stream (File => File);
   --  TODO: Read in file and do frequency analysis.


   Start := Ada.Calendar.Clock;

   --  Pre-fill the buffer.
   while not Ada.Streams.Stream_IO.End_Of_File (File) loop
      exit when Char_Count = 2;
      Char := To_Upper (Character'Input (Stream));
      if Is_Character (Char) then
         Buffer ((Char_Count mod 3) + 1) := Char;
         Letters.Add (C => Char);

         Char_Count := Char_Count + 1;

      end if;

      Bytes := Bytes + 1;
   end loop;

   while not Ada.Streams.Stream_IO.End_Of_File (File) loop
      Char := To_Upper (Character'Input (Stream));

      if Is_Character (Char) then
         Buffer ((Char_Count mod 3) + 1) := Char;

         Letters.Add (C => Char);

         case (Char_Count mod 3) + 1 is
         when 1 =>
            Digrams.Add (Buffer (2) & Buffer (3));
            Trigrams.Add (Buffer (2) & Buffer (3) & Buffer (1));
         when 2 =>
            Digrams.Add (Buffer (3) & Buffer (1));
            Trigrams.Add (Buffer (3) & Buffer (1) & Buffer (2));
         when 3 =>
            Digrams.Add (Buffer (1) & Buffer (2));
            Trigrams.Add (Buffer (1) & Buffer (2) & Buffer (3));
         when others =>
            null;
         end case;

         Char_Count := Char_Count + 1;

      end if;
      Bytes := Bytes + 1;

      if Bytes mod 2**16 = 0 then
         Put (Item => Float(Bytes)/Float (Total)* 100.0,
              Fore => 3,
              Exp  => 0,
              Aft  => 2);
         Put ("% .. ");

         declare
            use Ada.Calendar;
            Runtime : constant Duration := Ada.Calendar.Clock - Start;
            Tmp     : constant Natural :=
              Natural ((Float (Bytes) / Float (Runtime))/1024.0);
         begin
            Put (" Processed" & Bytes'Img & " bytes in ");

            Put (Item => Float (Runtime),
                 Fore => 0,
                 Aft  => 2,
                 Exp  => 0);
            Put_Line (" seconds (" & Tmp'Img & " kbytes/s).");
         end;

      end if;

   end loop;

         Put (Item => Float(Bytes)/Float (Total)* 100.0,
              Fore => 3,
              Exp  => 0,
              Aft  => 2);
         Put ("% .. ");

         declare
            use Ada.Calendar;
            Runtime : constant Duration := Ada.Calendar.Clock - Start;
            Tmp     : constant Natural :=
              Natural ((Float (Bytes) / Float (Runtime))/1024.0);
         begin
            Put (" Processed" & Bytes'Img & " bytes in ");

            Put (Item => Float (Runtime),
                 Fore => 0,
                 Aft  => 2,
                 Exp  => 0);
            Put_Line (" seconds (" & Tmp'Img & " kbytes/s).");
         end;

   Letters.Show_Contents;

   -- Digrams.Show_Contents;

   -- Trigrams.Show_Contents;

end Tool;
