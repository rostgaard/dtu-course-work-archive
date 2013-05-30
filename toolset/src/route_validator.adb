with Ada.Text_IO;
with Ada.Command_Line;
with Ada.Exceptions;

with Railval.Parser;
with Railval.Trace;

procedure Route_Validator is
   use Ada.Text_IO;
   use Ada.Command_Line;
   use Ada.Exceptions;

   use Railval;
   use Parser;

   File_Handle : File_Type;
   Railnet     : Parser.Railway_Networks;
   pragma Unreferenced (Railnet);

   procedure Usage;

   procedure Usage is
   begin
      Put_Line (Command_Name & " input_file --debug");
      Put_Line (ASCII.HT &
                  "--debug provides additional information about validation");
      Set_Exit_Status (Code => Failure);
   end Usage;

begin

   if Argument_Count = 0 then
      Usage;
      return;
   elsif Argument_Count = 2 and then Argument (2) =  "--debug" then
      Railval.Trace.Mute (Railval.Trace.Debug) := False;
   end if;

   Open (File => File_Handle,
         Mode => In_File,
         Name => Argument (1));

   Railnet := Load_From_File (File => File_Handle);

   Put_Line ("Network is valid.");
exception
   when E : Name_Error =>
      Put_Line (Exception_Message (E));
   when others =>
      Put_Line ("Validation failed");
      New_Line;
      Put_Line ("Reason:");
      raise;
end Route_Validator;
