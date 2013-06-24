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

procedure Tool is
   procedure Usage;

   subtype Cipher_Text is String;

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

   function Encrypt (Plaintext : in String;
                     Key       : in Keys) return Cipher_Text is
      C : Cipher_Text (Plaintext'Range);

      P : String renames Plaintext;
   begin
      for I in Plaintext'Range loop
         C (I) :=
           Character'Val
             (Character'Pos (P (I)) xor Key (I mod 16));
      end loop;

      return C;
   end Encrypt;

   function Decrypt (Plaintext : in String;
                     Key       : in Keys) return Cipher_Text renames Encrypt;

   Start_Seed : Unsigned_64;
   Stop_Seed : Unsigned_64;

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
   begin
      for Seed in Start_Seed .. Stop_Seed loop
         Count := Count + 1;
         Current := Generate_Key (Seed);
         if Ada.Strings.Fixed.Index (Source  => Decrypt (Buffer, Current),
                                     Pattern => "NSA") /= 0 then
            Candidate := Current;
         end if;
      end loop;

      Put_Line (Decrypt (Buffer, Candidate));
   end;

   --  Decode buffer.

end Tool;
