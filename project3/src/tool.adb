with Ada.Text_IO; use Ada.Text_IO;
with Ada.Calendar; use Ada.Calendar;
with Ada.Command_Line; use Ada.Command_Line;

--  Local packages.
with Decrypter;
with Decrypter.Trace;
with File_IO_Utilities;
with Key_Utilities;
with Unsigned_Types;
with Key_Candidates;
with Terminal_Shortcuts;
with Time_Handling;

procedure Tool is
   use Time_Handling;
   use Decrypter;
   use Key_Utilities;
   use File_IO_Utilities;
   use Unsigned_Types;

   procedure Usage;

   procedure Usage is
   begin
      Put_Line ("Usage " & Command_Name & " ciphertext_file " &
                  "  [expected keyspace] [--debug]");
   end Usage;

   Start_Seed : constant Unsigned_64 :=
     To_UNIX_Timestamp
       (Add_GMT_Offset
            (Item => Ada.Calendar.Time_Of (Year    => 2009,
                                           Month   =>    6,
                                           Day     =>   22)));
   Stop_Seed  : constant Unsigned_64 :=
     To_UNIX_Timestamp
       (Add_GMT_Offset
            (Item => Ada.Calendar.Time_Of (Year    => 2009,
                                           Month   =>    6,
                                           Day     =>   29)));

   Keyspace   : Integer      := -1;
begin

   if Argument_Count < 1 then
      Usage;
      return;
   elsif Argument_Count > 1 then
      Keyspace := Integer'Value (Argument (2));
   elsif Argument_Count > 2 and then Argument (3) = "--debug" then

      Decrypter.Trace.Unmute (Trace => Decrypter.Trace.Debug);

   end if;

   declare
      Buffer    : constant String := Load_File (Filename => Argument (1));
      Count     : Natural := 0;
      Candidate : Keys;
   begin
      --  Locate all key candidates
      for Seed in Start_Seed .. Stop_Seed loop
         exit when Key_Candidates.Unique_Keys = Keyspace;
         Count     := Count + 1;
         Candidate := Generate_Key (Seed);

         --  Push them to a list to be able detect duplicates.
         Key_Candidates.Add (Candidate);

         if Count mod 50_000 = 0 then  --  Once in a while, show progress.
            Count := 0;
            Terminal_Shortcuts.Progress
              (Text     => "Generating key candidates...",
               Progress =>   Float (Seed - Start_Seed)
                           / Float (Stop_Seed - Start_Seed));
         end if;
      end loop;
      Terminal_Shortcuts.Progress
        (Text     => "Generating key candidates...",
         Progress => 1.0);
      New_Line;

      --  Peek at the first part of the buffer to check it.
      for Key of Key_Candidates.List loop
         declare
            Skip : Boolean := False;
         begin
            for C of Decrypt (Ciphertext => Buffer (Buffer'First .. 16),
                              Key        => Key) loop
               exit when Skip;

               --  Skip all buffers that contain non-printable characters.
               if not Is_Printable (C) then
                  Skip := True;
               end if;
            end loop;

            if not Skip then
               Put_Line ("Found candidate: " & Image (Key));
               Candidate := Key;
            end if;
         end;
      end loop;

      New_Line;
      Put_Line ("============PLAINTEXT=================");
      Put_Line (Decrypt (Buffer, Candidate));
      Put_Line ("============END PLAINTEXT=============");
      New_Line;
      Put_Line ("Key: " & Image (Candidate));
      --  Put_Line ("Seed:" & Seed'Img);

   end;

end Tool;
