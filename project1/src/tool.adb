with Ada.Text_IO; use Ada.Text_IO;
with Ada.Command_Line; use Ada.Command_Line;
with Letters;
with Digrams;
with Trigrams;

with Decrypter;
with Decrypter.Utilities;
with Decrypter.Trace;

procedure Tool is
   use Letters;
   use Digrams;
   use Trigrams;

   procedure Usage;

   Dictionary  : Decrypter.Utilities.File_Statistics;
   Cipher_Text : Decrypter.Utilities.File_Statistics;

   procedure Show_Substitution_Table;

   procedure Show_Substitution_Table is
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
   end Show_Substitution_Table;

   procedure Usage is
   begin
      Put_Line ("Usage " & Command_Name & " plaintext_file (dictionary)" &
                  " ciphertext_file [--debug]");
   end Usage;

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

      Quit        : constant Character := 'q';
      Replace     : constant Character := 'r';
      Clear       : constant Character := 'c';
      Exchange    : constant Character := 'x';
      Command     : Character;
      Status      : String (1 .. 80) := (others => ' ');
      Cipher      : constant String :=
        Decrypter.Utilities.Buffer_From_File (Filename => Argument (2));
      Subst_Table : Decrypter.Encoding_Table := ('B' => 'E',
                                                 'S' => 'T',
                                                 'P' => 'H',
                                                 others => '_');
      procedure Clear_Screen;
      procedure Menu;
      procedure Replace_Character;
      procedure Exchange_Character;
      procedure Clear_Character;
      procedure Show_Status;

      procedure Clear_Character is
         Source : Character;
      begin
         Put ("clear: ");

         Get_Immediate (Source);
         Source := Decrypter.Utilities.To_Upper (Source);
         Put (Source);

         Subst_Table (Source) := '_';
      exception
         when Constraint_Error =>
            Status (1 .. 20) := "Invalid character: " & Source;
      end Clear_Character;

      procedure Clear_Screen is
      begin
         Put (ASCII.ESC & "[2J");
      end Clear_Screen;

      procedure Exchange_Character is
         Source, Target, Tmp : Character;
      begin
         Put ("replace: ");

         Get_Immediate (Source);
         Source := Decrypter.Utilities.To_Upper (Source);
         Put (Source);
         Put (" with: ");
         Get_Immediate (Target);
         Target := Decrypter.Utilities.To_Upper (Target);
         Put (Target);

         Tmp := Subst_Table (Source);
         Subst_Table (Source) := Subst_Table (Target);
         Subst_Table (Target) := Tmp;
      exception
         when Constraint_Error =>
            Status (1 .. 26) := "Invalid character: " &
              Source & " or " & Target & ".";
      end Exchange_Character;

      procedure Menu is
      begin
         Put_Line ("q: Quit,  r: Replace character in table. ");
         Put ("c: Clear charcter,  x: Exchange character in table. ");
      end Menu;

      procedure Replace_Character is
         Source, Target : Character;
      begin
         Put ("replace: ");

         Get_Immediate (Source);
         Source := Decrypter.Utilities.To_Upper (Source);
         Put (Source);
         Put (" with: ");
         Get_Immediate (Target);
         Target := Decrypter.Utilities.To_Upper (Target);
         Put (Target);
         Subst_Table (Source) := Target;
      exception
         when Constraint_Error =>
            Status (1 .. 26) := "Invalid character: " &
              Source & " or " & Target & ".";
      end Replace_Character;

      procedure Show_Status is
         Position    : Natural := Cipher'First;
      begin
         while Position < Cipher'Last loop
            if Position + 87 < Cipher'Last then
               Put_Line (Cipher (Position .. Position + 87));

               Put_Line
                 (Decrypter.Utilities.To_Lower
                    (Decrypter.Decrypt
                       (Cipher_Text => Cipher (Position .. Position + 87),
                        Encodings   => Subst_Table)));

               Position := Position + 87;
            else
               Put_Line (Cipher (Position .. Cipher'Last));

               Put_Line
                 (Decrypter.Utilities.To_Lower
                    (Decrypter.Decrypt
                       (Cipher_Text => Cipher (Position .. Cipher'Last),
                        Encodings   => Subst_Table)));
               Position := Cipher'Last;
            end if;
            New_Line;
         end loop;
      end Show_Status;

   begin
      loop
         Clear_Screen;

         Put_Line (Status);
         --  Clear buffer.
         Status := (others => ' ');

         Show_Substitution_Table;
         Show_Status;

         Menu;
         Get_Immediate (Command);

         case Command is
            when Quit =>
               Set_Exit_Status (Success);
               return;

            when Replace =>
               Replace_Character;

            when Clear =>
               Clear_Character;

            when Exchange =>
               Exchange_Character;

            when others =>
               null;
         end case;
      end loop;

   end;
end Tool;
