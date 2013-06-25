with Ada.Text_IO;

package body Terminal_Shortcuts is
   use Ada.Text_IO;

   procedure To_Beginning_Of_Line is
   begin
      Put (ASCII.ESC & "]" & ASCII.LF &
             ASCII.ESC & "[F" & ASCII.ESC & "[J");
   end To_Beginning_Of_Line;

end Terminal_Shortcuts;
