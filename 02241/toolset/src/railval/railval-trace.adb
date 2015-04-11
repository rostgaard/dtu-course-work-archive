with Ada.Text_IO;

package body Railval.Trace is
   use Ada.Text_IO;

   Error_String : constant String := "ERROR";
   Debug_String : constant String := "DEBUG";
   Seperator    : constant String := ": ";

   procedure Error (Context : in String;
                    Message : in String) is
   begin
      if not Mute (Error) then
         Put_Line (Error_String & Seperator &
                     Context & Seperator &
                     Message);
      end if;
   end Error;

   procedure Debug (Context : in String;
                    Message : in String) is
   begin
      if not Mute (Debug) then
         Put_Line (Debug_String & Seperator &
                     Context & Seperator &
                     Message);
      end if;
   end Debug;

end Railval.Trace;
