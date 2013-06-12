with Ada.Text_IO; use Ada.Text_IO;
with Ada.Streams.Stream_IO;
with Ada.Float_Text_IO; use Ada.Float_Text_IO;
with Ada.Command_Line; use Ada.Command_Line;
with Ada.Streams; use Ada.Streams;

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

   procedure Usage is
   begin
      Put_Line ("Usage " & Command_Name & " plaintext_file");
   end Usage;

   Buffer     : String (1 .. 3) := (others => ' ');
   Char_Count : Natural := 0;
   Char       : Character := ' ';
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

   Stream := Ada.Streams.Stream_IO.Stream (File => File);
   --  TODO: Read in file and do frequency analysis.

   --  Pre-fill the buffer.
   while not Ada.Streams.Stream_IO.End_Of_File (File) loop
      exit when Char_Count = 2;
      Character'Read (Stream, Char);
      if Char /= ' ' then
         Buffer ((Char_Count mod 3) + 1) := Char;
         Letters.Add (Letter => Char);
      end if;
      Char_Count := Char_Count + 1;
   end loop;

   while not Ada.Streams.Stream_IO.End_Of_File (File) loop
      Character'Read (Stream, Char);
      if Char /= ' ' and Char /= ASCII.LF and Char /= ASCII.CR then
         Buffer ((Char_Count mod 3) + 1) := Char;

         Letters.Add (Letter => Char);

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

   end loop;

   Letters.Add (Letter => 'a');
   Letters.Add (Letter => 'a');
   Letters.Add (Letter => 'c');
   Letters.Add (Letter => 'b');
   Letters.Add (Letter => 'd');
   Letters.Add (Letter => 'b');

   Put ("th: ");
   Ada.Float_Text_IO.Put
     (Item => Digrams.Frequency (Value (C1 => 't', C2 => 'h')),
      Fore => 0,
      Aft  => 3,
      Exp  => 0);
   New_Line;

   Put ("c: ");
   Ada.Float_Text_IO.Put
     (Item => Letters.Frequency (Letter => 'c'),
      Fore => 0,
      Aft  => 3,
      Exp  => 0);
   New_Line;

   Put ("a: ");
   Ada.Float_Text_IO.Put
     (Item => Letters.Frequency (Letter => 'a'),
      Fore => 0,
      Aft  => 3,
      Exp  => 0);
   New_Line;

   Letters.Show_Contents;

   Digrams.Show_Contents;

   Trigrams.Show_Contents;

end Tool;
