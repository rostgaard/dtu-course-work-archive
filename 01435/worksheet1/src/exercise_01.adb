with Ada.Text_IO; use Ada.Text_IO;

with Caesar_Cipher;

procedure Exercise_01 is
   Test_String : constant String
     := "this is encrypted with the caesar cipher";
   Key         : constant Positive := 3;
   Assignment  : constant String   := "GJBFWJYMJNIJXTKRFWHM";

begin
   --  Just test if functions resturn the value expected.
   Put_Line (Caesar_Cipher.Encode (Test_String, Key));
   Put_Line (Caesar_Cipher.Decode
             (Caesar_Cipher.Encode (Test_String, Key), Key));

   New_Line;

   --  Test all values.
   for I in 1 .. 25 loop
      Put_Line (Caesar_Cipher.Decode (Assignment, I) &
                  " (shifted" & I'Img & " characters.)");
   end loop;

end Exercise_01;
