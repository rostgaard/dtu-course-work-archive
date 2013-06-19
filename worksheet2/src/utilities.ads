with Ada.Calendar;

package Utilities is
   use Ada.Calendar;

   function Unix_Timestamp
     (Date : in Time)
      return String;

end Utilities;
