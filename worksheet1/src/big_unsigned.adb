with Ada.Strings.Unbounded;

package body Big_Unsigned is

   function "+" (Left, Right : in Unsigned_128) return Unsigned_128 is
      Carry  : Boolean := False;
      Result : Unsigned_128;
   begin

      for I in reverse Left'Range loop
         if Left (I) and Right (I) then
            if Carry then
               Result (I) := True;
            else
               Result (I) := False;
            end if;

            Carry := True;

         elsif not Left (I) and not Right (I) then
            if Carry then
               Result (I) := True;
            else
               Result (I) := False;
            end if;

            Carry := False;

         else
            if Carry then
               Result (I) := False;
            else
               Result (I) := True;
            end if;
         end if;
      end loop;
      return Result;
   end "+";

   function Image (Item : Unsigned_128) return String is
      use Ada.Strings.Unbounded;

      Buffer : Unbounded_String;
   begin
      for I in Item'Range loop
         if Item (I) then
            Append (Buffer, "1");
         else
            Append (Buffer, "0");
         end if;
      end loop;

      return To_String (Buffer);
   end Image;

end Big_Unsigned;
