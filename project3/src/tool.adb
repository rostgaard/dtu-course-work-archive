with Ada.Text_IO; use Ada.Text_IO;
with Ada.Calendar; use Ada.Calendar;
with Ada.Calendar.Time_Zones; use Ada.Calendar.Time_Zones;
with Ada.Command_Line; use Ada.Command_Line;
with Ada.Calendar.Conversions;
with Ada.Strings.Fixed; use Ada.Strings.Fixed;

with File_IO_Utilities; use File_IO_Utilities;
with Decrypter; use Decrypter;
with Key_Utilities; use Key_Utilities;
with Unsigned_Types; use Unsigned_Types;
with Decrypter.Trace;
with Key_Candidates;
with Terminal_Shortcuts;

procedure Tool is
   procedure Usage;

   function Add_GMT_Offset (Item : in Time) return Time;
   function To_UNIX_Timestamp (Item : in Time) return Unsigned_64;

   Start_Time : constant Ada.Calendar.Time :=
     Ada.Calendar.Time_Of (Year    => 2009,
                           Month   =>    6,
                           Day     =>   22);

   Stop_Time : constant Ada.Calendar.Time :=
     Ada.Calendar.Time_Of (Year    => 2009,
                           Month   =>    6,
                           Day     =>   29);

   function Add_GMT_Offset (Item : in Time) return Time is
   begin
      return Item + Duration (UTC_Time_Offset (Date => Start_Time) * 60);
   end Add_GMT_Offset;

   procedure Usage is
   begin
      Put_Line ("Usage " & Command_Name & " ciphertext_file");
   end Usage;

   function To_UNIX_Timestamp (Item : in Time) return Unsigned_64 is
   begin
      return Unsigned_64 (Ada.Calendar.Conversions.To_Unix_Time (Item));
   end To_UNIX_Timestamp;

   Start_Seed : Unsigned_64;
   Stop_Seed : Unsigned_64;

   Prefound  : Keys := (15 => 16#D7#,
                        14 => 16#1A#,
                        13 => 16#69#,
                        12 => 16#F4#,
                        11 => 16#AB#,
                        10 => 16#3E#,
                        9  => 16#1D#,
                        8  => 16#78#,
                        7  => 16#3F#,
                        6  => 16#22#,
                        5  => 16#91#,
                        4  => 16#BC#,
                        3  => 16#93#,
                        2  => 16#C6#,
                        1  => 16#C5#,
                        0  => 16#30#); --  Found C0, is 30

begin

   if Argument_Count < 1 then
      Usage;
      return;
   elsif Argument_Count > 1 and then Argument (2) = "--debug" then

      Decrypter.Trace.Unmute (Trace => Decrypter.Trace.Debug);

   end if;

   Start_Seed := To_UNIX_Timestamp (Add_GMT_Offset (Item => Start_Time));
   Stop_Seed := To_UNIX_Timestamp (Add_GMT_Offset (Item => Stop_Time));

   --     Put_Line ("Encrypt test:" & Encrypt
   --               (Plaintext => "heloooooooo",
   --                Key => Generate_Key (Seed => Start_Seed)));
   --     Put_Line ("Encrypt test:" & Decrypt
   --       (Plaintext =>
   --          Encrypt (Plaintext => "heloooooooo",
   --                   Key => Generate_Key (Seed => Start_Seed)),
   --        Key => Generate_Key (Seed => Start_Seed)));

   declare
      Buffer    : constant String := Load_File (Filename => Argument (1));
      Found     : Boolean := True;
      Count     : Natural := 0;
      Current   : Keys;
      Candidate : Keys;
      Start_S   : Unsigned_64;
   begin
      --  Locate all key candidates
      Put_Line ("Generating key candidates...");
      for Seed in Start_Seed .. Stop_Seed loop
         Count   := Count + 1;
         Key_Candidates.Add (Generate_Key (Seed));
         if Count mod 30_000 = 0 then
            Terminal_Shortcuts.To_Beginning_Of_Line;
            Put (Unsigned_64'Image
                 (100*(Seed - Start_Seed) / (Stop_Seed - Start_Seed)) & "%..");
         end if;
      end loop;
      Terminal_Shortcuts.To_Beginning_Of_Line;
      Put_Line ("100%..");

      --  Peek at the first part of the buffer to check it.
      for Key of Key_Candidates.List loop
         declare
            Skip : Boolean := False;
         begin
            for C of Decrypt (Ciphertext => Buffer (Buffer'First .. 14),
                              Key        => Key) loop
               exit when Skip;

               --  Skip all buffers that contain non-printable characters.
               if not Is_Printable (C) then
                  Skip := True;
               end if;
            end loop;

            --  Seems like we have our key candidate, now we just need to
            --  correct the odd skew in key(0). For this, we increase the
            --  look-ahead buffer.
            if not Skip then
               Put_Line ("Found candidate " & Image (Key) & " narrowing.");
               Candidate := Key;

               for I in Bytes (0) .. Bytes (255) loop
                  Skip          := False; --  Pull down the skip flag.
                  Candidate (0) := I;     --  The test parameter.

                  for C of Decrypt
                    (Ciphertext => Buffer (Buffer'First .. Buffer'Last - 406),
                     Key        => Candidate)
                  loop
                     exit when Skip;
                     if not Is_Printable (C) then
                        Skip := True;
                     end if;
                  end loop;

                  if not Skip then
                     New_Line;
                     Put_Line ("Possible Candidate: " & Image (Candidate));
                     Put_Line ("Listing first 100 bytes, press a to accept" &
                                 " or r to reject.");
                     New_Line;
                     Put_Line ("============PLAINTEXT=================");
                     Put_Line
                       (Decrypt
                          (Ciphertext =>
                             Buffer (Buffer'First .. Buffer'First + 100),
                           Key        => Candidate));
                     Put_Line ("============END PLAINTEXT=============");
                     declare
                        Input : Character := ASCII.NUL;
                     begin

                        loop
                           exit when Input in 'r' | 'a';
                           Get_Immediate (Input);
                        end loop;

                        if Input = 'a' then
                           New_Line;
                           Put_Line ("Key is " & Image (Candidate));
                           New_Line;
                           Put_Line ("Show plaintext? (y/n)");

                           loop
                              exit when Input in 'y' | 'n';
                              Get_Immediate (Input);
                           end loop;

                           if Input = 'y' then
                              Put_Line ("============PLAINTEXT=================");
                              Put_Line (Decrypt (Ciphertext => Buffer,
                                                Key        => Candidate));
                              Put_Line ("============END PLAINTEXT=============");
                           end if;

                           return;
                        end if;
                     end;
                  end if;

               end loop;

            end if;
         end;
      end loop;

      -- Decrypter.Trace.Unmute (Trace => Decrypter.Trace.Debug);
      --  Seed: 1246233511
      --Put_Line ("Key:" & Image (Candidate) & " Seed:" & Start_S'Img);

      --Put (" Decoded:" & Encode (Buffer (16), Prefound, 16));

      -- Put_Line (Decrypt (Buffer, Prefound));

      New_Line;
      Put_Line ("No key found.. :(");

   end;

   --  Decode buffer.

end Tool;
