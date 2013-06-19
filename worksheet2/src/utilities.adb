with Ada.Strings.Fixed;
with Ada.Calendar.Conversions;
with Interfaces.C;

package body Utilities is

   function Unix_Timestamp
     (Date : in Time)
      return String
   is
      use Ada.Strings;
      use Interfaces.C;
   begin
      return Fixed.Trim
        (Source => long'Image
           (Ada.Calendar.Conversions.To_Unix_Time (Date)),
         Side   => Left);
   end Unix_Timestamp;

end Utilities;
