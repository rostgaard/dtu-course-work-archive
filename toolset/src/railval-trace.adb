with Ada.Text_IO;

package body Railval.Trace is
   use Ada.Text_IO;

   Error_String : constant String := "ERROR";
   Debug_String : constant String := "DEBUG";
   Seperator    : constant String := ": ";

   procedure Error (Context : in String;
                    Message : in String) is
   begin
      Put_Line (Error_String & Seperator &
           Context & Seperator &
           Message);
   end Error;

   procedure Debug (Context : in String;
                    Message : in String) is
   begin
      Put_Line (Debug_String & Seperator &
           Context & Seperator &
           Message);
   end Debug;

end Railval.Trace;
