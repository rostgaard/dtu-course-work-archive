with Ada.Text_IO; use Ada.Text_IO;
with Ada.Float_Text_IO; use Ada.Float_Text_IO;
with Ada.Streams.Stream_IO;
with Ada.Streams; use Ada.Streams;
with Ada.Characters.Handling; use Ada.Characters.Handling;
with Ada.Directories;  use Ada.Directories;
with Ada.Calendar;

package body Decrypter.Utilities is

   function Is_Character (C : in Character) return Boolean;

   function Is_Character (C : in Character) return Boolean is
   begin
      return C in Character'('A') .. Character'('Z');
   end Is_Character;

   function Buffer_From_File (Filename : in String) return String is
      Buffer : String (1 .. Natural (Size (Filename)));

      File         : Ada.Streams.Stream_IO.File_Type;
      Stream  : Ada.Streams.Stream_IO.Stream_Access;
      Count   : Positive := 1;
      Tmp     : Character;
   begin
      Ada.Streams.Stream_IO.Open
        (File => File,
         Name => Filename,
         Mode => Ada.Streams.Stream_IO.In_File);

      Stream := Ada.Streams.Stream_IO.Stream (File => File);

      while not Ada.Streams.Stream_IO.End_Of_File (File) loop
         Tmp := To_Upper (Character'Input (Stream));
         if Is_Character (Tmp) then
            Buffer (Count) := Tmp;
            Count := Count + 1;
         end if;
      end loop;

      Ada.Streams.Stream_IO.Close (File => File);

      return Buffer (Buffer'First .. Count - 1);
   end Buffer_From_File;

   function Read_From_File (Filename : in String) return File_Statistics is
      use Letters;
      use Digrams;
      use Trigrams;

      File         : Ada.Streams.Stream_IO.File_Type;
      Stream  : Ada.Streams.Stream_IO.Stream_Access;
      Buffer     : String (1 .. 3) := (others => ' ');
      Char_Count : Natural := 0;
      Char       : Character := ' ';
      Bytes      : File_Size :=  0;
      Total      : File_Size := 0;

      Start : Ada.Calendar.Time;

      procedure Show_Progress is
         use Ada.Calendar;
         Runtime : constant Duration := Ada.Calendar.Clock - Start;
         Tmp     : constant Natural :=
           Natural ((Float (Bytes) / Float (Runtime))/1024.0);
      begin
         Put (ASCII.ESC & "]" & ASCII.LF & ASCII.ESC & "[F" & ASCII.ESC & "[J");
         Put ("Processed" & Bytes'Img & " bytes of file " &
                Filename & " in ");

         Put (Item => Float (Runtime),
              Fore => 0,
              Aft  => 2,
              Exp  => 0);
         Put (" seconds (" & Tmp'Img & " kbytes/s).");
      end;

   begin
      Ada.Streams.Stream_IO.Open
        (File => File,
         Name => Filename,
         Mode => Ada.Streams.Stream_IO.In_File);

      Total := Size (Filename);

      Stream := Ada.Streams.Stream_IO.Stream (File => File);

      Start := Ada.Calendar.Clock;

      --  Clear the states.
      Letters.Clear;
      Digrams.Clear;
      Trigrams.Clear;

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
            Put (Item => Float (Bytes) / Float (Total) * 100.0,
                 Fore => 3,
                 Exp  => 0,
                 Aft  => 2);
            Put ("% .. ");

            Show_Progress;
         end if;
      end loop;

      Show_Progress;
      Ada.Streams.Stream_IO.Close (File => File);

      return (Letter  => Letters.To_Ordered_Table (Reverse_Order => True),
              Digram  => Digrams.To_Ordered_Table (Reverse_Order => True),
              Trigram => Trigrams.To_Ordered_Table (Reverse_Order => True));
   end Read_From_File;

end Decrypter.Utilities;
