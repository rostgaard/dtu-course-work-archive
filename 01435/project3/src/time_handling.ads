with Ada.Calendar;
with Unsigned_Types;

package Time_Handling is
   use Ada.Calendar;
   use Unsigned_Types;

   function To_UNIX_Timestamp (Item : in Time) return Unsigned_64;

   function Add_GMT_Offset (Item : in Time) return Time;

end Time_Handling;
