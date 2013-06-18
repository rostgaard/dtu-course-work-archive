with Ada.Text_IO; use Ada.Text_IO;
with GNAT.OS_Lib;
with GNAT.Expect;

package body Caesar_Cipher_Evaluation is
   use GNAT.Expect;
   use GNAT.OS_Lib;

   Pd : Process_Descriptor;
   Result  : Expect_Match;

   Command : constant String := "aspell -a";

   Args : constant Argument_List_Access := Argument_String_To_List (Command);

   function Try_String (Item : in String; Num_Arguments : in Positive)
                        return Natural
   is
      Count   : Natural := 0;
      Result  : Expect_Match;
   begin
      --  The debugger is now running. Let's send a command.
      --  Then let's read the output of the debugger.
      --  Here, we are expecting any possible non-empty output (hence the
      --  ".+" regexp). We might in fact be expecting a file name that
      --  gdb uses to confirm a breakpoint. The regexp would be something like:
      --  "file (.*), line (\d+)"

      Send (Pd, """" & Item & """");

      for I in 1 .. Num_Arguments loop
         Expect (Descriptor =>  Pd,
                 Result     => Result,
                 Regexp     => "(.+)",
                 Timeout    => 1_000);

         case Result is
         when Expect_Timeout =>
            Put_Line ("Timeout!");
         when 1 =>
            declare
               Line : constant String :=
                 Expect_Out_Match (Descriptor => Pd);
            begin
               if Line (Line'First) = '*' then
                  --  Put_Line ("Sentence "& Item & " ok!");
                  Count := Count + 1;
               else
                  null;
                  --  Put_Line ("Sentence "& Item & " not ok!");
               end if;
            end;
         when others =>
            null;
         end case;
      end loop;

      return Count;

   end Try_String;

begin
   Non_Blocking_Spawn
     (Descriptor  =>  Pd,
      Command     => Args (Args'First).all,
      Args        => Args (Args'First + 1 .. Args'Last),
      Buffer_Size => 0);

   Expect (Descriptor =>  Pd,
              Result     => Result,
              Regexp     => ".*",
              Timeout    => 1_000);

   Put_Line (Expect_Out (Descriptor => Pd));

end Caesar_Cipher_Evaluation;






