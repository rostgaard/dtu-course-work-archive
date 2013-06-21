with Ada.Calendar;
with Unsigned_Types;

package Utilities is
   use Ada.Calendar;
   use Unsigned_Types;

   function Unix_Timestamp
     (Date : in Time)
      return String;

   function MD5_Redux (Item : in String) return Unsigned_20;
   function MD5_Redux (Item : in Unsigned_20) return Unsigned_20;

end Utilities;
