with Ada.Text_IO; use Ada.Text_IO;

procedure Railval.Tokenizer.Test is

   File_Handle : File_Type;
   Buffer      : String (1 .. 128);
   Filled      : Natural := 0;

begin
   Open (File => File_Handle,
         Mode => In_File,
         Name => "../networks/net1.railnet");

   while not End_Of_File (File_Handle) loop
      Get_Line (File => File_Handle,
                Item => Buffer,
                Last => Filled);
      declare
         Token : Tokens :=
           Parse_Line (Buffer (Buffer'First .. Filled));
         pragma Unreferenced (Token);
      begin
         --  Put_Line (Image (Token));
         null;
      end;
   end loop;

end Railval.Tokenizer.Test;
