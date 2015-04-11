with Ada.Calendar.Time_Zones; use Ada.Calendar.Time_Zones;
with Ada.Calendar.Conversions;

package body Time_Handling is

   function Add_GMT_Offset (Item : in Time) return Time is
   begin
      return Item + Duration (UTC_Time_Offset (Date => Item) * 60);
   end Add_GMT_Offset;

   function To_UNIX_Timestamp (Item : in Time) return Unsigned_64 is
   begin
      return Unsigned_64 (Ada.Calendar.Conversions.To_Unix_Time (Item));
   end To_UNIX_Timestamp;

end Time_Handling;
