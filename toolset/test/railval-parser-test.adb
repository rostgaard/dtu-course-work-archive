with Ada.Text_IO; use Ada.Text_IO;

procedure Railval.Parser.Test is
   use Railval;

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
      begin
         case Token.Kind is
            when CONN =>
               Parser.Link (Token.Left, Token.Right);
            when STAT =>
               Parser.Define_Station (Name           => Token.Station_Name,
                                      Identification => Token.Identifier);
            when others =>
               null;
         end case;
      end;
   end loop;

end Railval.Parser.Test;
