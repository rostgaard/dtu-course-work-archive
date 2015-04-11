with Ada.Text_IO; use Ada.Text_IO;
with Ada.Strings.Unbounded;      use Ada.Strings.Unbounded;
with Ada.Command_Line;

with Railval.Validator;
with Railval.Trace;

procedure Schedule_Validator is
   use Railval.Validator;
   use Ada.Command_Line;

   File_Handle : File_Type;
   Routes      : Row_Container.Vector;

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

   while not End_Of_File (File => File_Handle) loop
      Routes.Append (New_Item => Parse_Line
                     (Get_Line (File => File_Handle)));
   end loop;
   Close (File_Handle);

   declare
      Schedule : constant Schedules := Create (Routes);
   begin
      Validate (Schedule => Schedule);
      Put_Line ("Schedule is valid");
   exception
      when others =>
         Put_Line ("Schedule is invalid");
         Put (Image (Schedule));
         Put_Line ("Reason:");
         raise;
   end;

end Schedule_Validator;
