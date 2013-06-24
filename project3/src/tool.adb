with Ada.Text_IO; use Ada.Text_IO;
with Ada.Float_Text_IO; use Ada.Float_Text_IO;
with Ada.Streams.Stream_IO;
with Ada.Streams; use Ada.Streams;
--  with Ada.Characters.Handling; use Ada.Characters.Handling;
with Ada.Directories;  use Ada.Directories;
with Ada.Calendar; use Ada.Calendar;
with Ada.Calendar.Time_Zones; use Ada.Calendar.Time_Zones;
with Ada.Command_Line; use Ada.Command_Line;
with Ada.Calendar.Conversions;
--  with Decrypter;
--  with Decrypter.Utilities;
with Decrypter.Trace;

procedure Tool is
   procedure Usage;

   type Long_Unsigned is mod 2 ** 64;
   type Keys is mod 2 ** Long_Long_Integer'Size;

   function Load_File (Filename : in String) return String;

   function Is_Printable (C : in Character) return Boolean;

   function Update (S : Long_Unsigned) return Long_Unsigned;

   function Generate_Key (Seed : Long_Unsigned) return Keys;

   Start_Time : constant Ada.Calendar.Time :=
     Ada.Calendar.Time_Of (Year    => 2009,
                           Month   =>    6,
                           Day     =>   22);

   function Is_Printable (C : in Character) return Boolean is
   begin

      return C in Character'('A') .. Character'('Z') or
        C in Character'('a') .. Character'('a') or
        C in Character'('0') .. Character'('9');
   end Is_Printable;

   function Load_File (Filename : in String) return String is
      Buffer : String (1 .. Natural (Size (Filename)));

      File         : Ada.Streams.Stream_IO.File_Type;
      Stream  : Ada.Streams.Stream_IO.Stream_Access;
      Bytes      : File_Size :=  0;

      Total      : File_Size := 0;
      Start : constant Ada.Calendar.Time := Ada.Calendar.Clock;

      procedure Show_Progress;

      procedure Show_Progress is
         Runtime : constant Duration := Ada.Calendar.Clock - Start;
         Tmp     : constant Natural :=
           Natural ((Float (Bytes) / Float (Runtime))/1024.0);
      begin
         Put (ASCII.ESC & "]" & ASCII.LF &
                ASCII.ESC & "[F" & ASCII.ESC & "[J");
         Put ("Processed" & Bytes'Img & " bytes of file " &
                Filename & " in ");

         Put (Item => Float (Runtime),
              Fore => 0,
              Aft  => 2,
              Exp  => 0);
         Put (" seconds (" & Tmp'Img & " kbytes/s).");
      end Show_Progress;

   begin

      Ada.Streams.Stream_IO.Open
        (File => File,
         Name => Filename,
         Mode => Ada.Streams.Stream_IO.In_File);

      Total := Size (Filename);

      Stream := Ada.Streams.Stream_IO.Stream (File => File);

      while Bytes < Total loop
         Bytes := Bytes + 1;
         Buffer (Natural (Bytes)) := Character'Input (Stream);
      end loop;
      Show_Progress;

      Ada.Streams.Stream_IO.Close (File => File);

      return Buffer;
   end Load_File;

   function Add_GMT_Offset (Item : in Time) return Time is
   begin
      return Item + Duration (UTC_Time_Offset (Date => Start_Time) * 60);
   end Add_GMT_Offset;

   function Generate_Key (Seed : Long_Unsigned) return Keys is
      Key : Keys := 0;
      S   : Long_Unsigned := Seed;
   begin
      Put_Line ("Start_Seed:" & Seed'Img);
      for I in 0 .. 15 loop
         --  The eight least significant bits of update.
         S := Update (S) and 16#ff#;
         Put_Line ("S:" & S'Img & " Key: " & Key'Img);
         Key := Key + Keys ((2**I) * (S));
      end loop;

      return Key;
   end Generate_Key;

   function Update (S : Long_Unsigned) return Long_Unsigned is
   begin
      return (69_069 * S + 5) mod 2**32;
   end Update;

   procedure Usage is
   begin
      Put_Line ("Usage " & Command_Name & " plaintext_file (dictionary)" &
                  " ciphertext_file [--debug]");
   end Usage;

   function To_UNIX_Timestamp (Item : in Time) return Long_Unsigned is
   begin
      return Long_Unsigned (Ada.Calendar.Conversions.To_Unix_Time (Item));
   end To_UNIX_Timestamp;

   Start_Seed : Long_Unsigned;

begin

   if Argument_Count < 1 then
      Usage;
      return;
   elsif Argument_Count > 1 and then Argument (2) = "--debug" then

      Decrypter.Trace.Unmute (Trace => Decrypter.Trace.Debug);

   end if;

   Start_Seed := To_UNIX_Timestamp (Add_GMT_Offset (Item => Start_Time));

   Put_Line ("Start_Seed:" & Start_Seed'Img);
   Put_Line (Generate_Key (Seed => Start_Seed)'Img);
   Put_Line (Generate_Key (Seed => Start_Seed + 1)'Img);
   Put_Line (Generate_Key (Seed => Start_Seed + 2)'Img);
   Put_Line ("Loading file");

   declare
      Buffer : constant String := Load_File (Filename => Argument (1));
   begin
      for C of Buffer loop
         exit when not Is_Printable (C);
         null;
      end loop;
   end;

   --  Decode buffer.

end Tool;
