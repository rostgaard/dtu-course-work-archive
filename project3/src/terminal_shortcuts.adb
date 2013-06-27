with Ada.Text_IO;
with Ada.Float_Text_IO;

package body Terminal_Shortcuts is
   use Ada.Text_IO;
   use Ada.Float_Text_IO;

   procedure Progress (Text : in String; Progress : in Float) is
   begin
      Terminal_Shortcuts.To_Beginning_Of_Line;
      Put (Text);
      Put (Item => 100.0 * Progress,
           Exp  => 0,
           Fore => 4,
           Aft  => 3);
      Put ("%");
   end Progress;

   procedure To_Beginning_Of_Line is
   begin
      Put (ASCII.ESC & "]" & ASCII.LF &
             ASCII.ESC & "[F" & ASCII.ESC & "[J");
   end To_Beginning_Of_Line;

end Terminal_Shortcuts;
