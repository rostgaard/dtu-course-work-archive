with Ada.Text_IO; use Ada.Text_IO;

procedure Railval.Parser.Test is
   use Railval;
   use Parser;

   File_Handle : File_Type;
   Buffer      : String (1 .. 128);
   Filled      : Natural := 0;

   Railnet     : Parser.Railway_Networks;
begin
   Open (File => File_Handle,
         Mode => In_File,
         Name => "../networks/net1.railnet");

   while not End_Of_File (File_Handle) loop
      Get_Line (File => File_Handle,
                Item => Buffer,
                Last => Filled);
      declare
         Token : constant Tokens :=
           Parse_Line (Buffer (Buffer'First .. Filled));
      begin
         case Token.Kind is
            when CONN =>
               Railnet.Link (Token.Left, Token.Right);
            when STAT =>
               Railnet.Define_Station (Name           => Token.Station_Name,
                                      Identification => Token.Identifier);
            when ENDP =>
               Railnet.Define_Endpoint (Identification => Token.Closing);
            when Undefined =>
               raise Constraint_Error with "File Syntax error detected.";
         end case;
      end;
   end loop;

   Railnet.Dump_Network;
   Railnet.Validate;

end Railval.Parser.Test;
