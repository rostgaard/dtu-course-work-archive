with Ada.Text_IO; use Ada.Text_IO;

procedure Railval.Parser.Test is
   use Railval;
   use Parser;

   File_Handle : File_Type;
   Railnet     : Parser.Railway_Networks;

begin
   Open (File => File_Handle,
         Mode => In_File,
         Name => "../networks/net1.railnet");

   Railnet := Load_From_File (File => File_Handle);

   Railnet.Dump_Network;

end Railval.Parser.Test;
