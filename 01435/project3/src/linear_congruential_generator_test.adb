with Ada.Text_IO;
with Decrypter.Trace;

with Key_Utilities; use Key_Utilities;
procedure Linear_Congruential_Generator_Test is
   use Ada.Text_IO;

begin
   -- 1245628864
   Decrypter.Trace.Unmute (Decrypter.Trace.Debug);
   Put_Line (Image (Generate_Key (0)));
   Put_Line (Value (Generate_Key (0))'Img);

end Linear_Congruential_Generator_Test;
