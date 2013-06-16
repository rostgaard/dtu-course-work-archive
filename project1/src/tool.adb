with Ada.Text_IO; use Ada.Text_IO;
with Ada.Command_Line; use Ada.Command_Line;
with Letters;
with Digrams;
with Trigrams;

with Decrypter.Utilities;
with Decrypter.Trace;

procedure Tool is
   use Letters;
   use Digrams;
   use Trigrams;

   procedure Usage;

   procedure Usage is
   begin
      Put_Line ("Usage " & Command_Name & " plaintext_file (dictionary)" &
                  " ciphertext_file [--debug]");
   end Usage;

   Dictionary  : Decrypter.Utilities.File_Statistics;
   Cipher_Text : Decrypter.Utilities.File_Statistics;

begin

   if Argument_Count < 2 then
      Usage;
      return;
   elsif Argument_Count > 2 and then Argument (3) = "--debug" then

      Decrypter.Trace.Unmute (Trace => Decrypter.Trace.Debug);

   end if;

   Dictionary := Decrypter.Utilities.Read_From_File (Filename => Argument (1));
   New_Line;
   Cipher_Text := Decrypter.Utilities.Read_From_File
     (Filename => Argument (2));
   New_Line;

   declare
      use Letters.Frequency_Count;
      use Digrams.Frequency_Count;
      use Trigrams.Frequency_Count;

      procedure Header;

      procedure Header is
         HR : constant String (1 .. 87) := (others => '-');
      begin
         Put_Line ("          Trigrams            |" &
                     "           Bigrams           |" &
                     "         Letters");
         Put_Line (HR);
         Put_Line ("Ciphertext         Dictionary |" &
                     "Ciphertext        Dictionary |" &
                     "Ciphertext      Dictionary");
         Put_Line (HR);
      end Header;

      DC1 : Letters.Frequency_Count.Cursor := Dictionary.Letter.First;
      CC1 : Letters.Frequency_Count.Cursor := Cipher_Text.Letter.First;
      DC2 : Digrams.Frequency_Count.Cursor := Dictionary.Digram.First;
      CC2 : Digrams.Frequency_Count.Cursor := Cipher_Text.Digram.First;
      DC3 : Trigrams.Frequency_Count.Cursor := Dictionary.Trigram.First;
      CC3 : Trigrams.Frequency_Count.Cursor := Cipher_Text.Trigram.First;
   begin

      New_Line;
      Header;

      for I in 1 .. Cipher_Text.Letter.Length loop
         if DC3 /= Trigrams.Frequency_Count.No_Element then
            Put (Image (Element (DC3)));
         else
            Put ("            -");
         end if;

         Put ("   "); -- Separator;

         if CC3 /= Trigrams.Frequency_Count.No_Element then
            Put (Image (Element (CC3)));
         else
            Put ("            -");
         end if;

         Put (" | "); -- Separator;

         if DC2 /= Digrams.Frequency_Count.No_Element then
            Put (Image (Element (DC2)));
         else
            Put ("            -");
         end if;

         Put ("   "); -- Separator;

         if CC2 /= Digrams.Frequency_Count.No_Element then
            Put (Image (Element (CC2)));
         else
            Put ("           -");
         end if;

         Put (" | "); -- Separator;

         if DC1 /= Letters.Frequency_Count.No_Element then
            Put (Image (Element (DC1)));
         else
            Put ("          -");
         end if;

         Put ("   "); -- Separator;

         if CC1 /= Letters.Frequency_Count.No_Element then
            Put (Image (Element (CC1)) & " ");
         end if;
         New_Line;

         Next (DC1);
         Next (CC1);
         Next (DC2);
         Next (CC2);
         Next (DC3);
         Next (CC3);

      end loop;

      New_Line;

      declare

         Cipher      : constant String :=
           Decrypter.Utilities.Buffer_From_File (Filename => Argument (2));
         Position    : Natural := Cipher'First;
         Subst_Table : String (Cipher'Range) := (others => '.');
         Subst_Pos   : Natural := Subst_Table'First;
      begin
         while Position < Cipher'Last loop
            if Position + 87 < Cipher'Last then
               Put_Line (Cipher (Position .. Position + 87));
               Put_Line (Subst_Table (Position .. Position + 87));
               Position := Position + 87;
            else
               Put_Line (Cipher (Position .. Cipher'Last));
               Put_Line (Subst_Table (Position .. Cipher'Last));
               Position := Cipher'Last;
            end if;
            New_Line;

--              if Position mod 87 = 0 then
--                 New_Line;
--                 while Subst_Pos /= Subst_Table'Last loop
--                    exit when Subst_Pos mod 87 = 0;
--                    Put (Subst_Table (Subst_Pos));
--                    Subst_Pos := Subst_Pos +1;
--                 end loop;
--                 Subst_Pos := Subst_Pos +1;
--                 New_Line;
--                 New_Line;
--
--              end if;

         end loop;
      end;
   end;
end Tool;
