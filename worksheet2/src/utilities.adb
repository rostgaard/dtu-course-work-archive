--  with Ada.Streams;
with Ada.Strings.Fixed;
with Ada.Calendar.Conversions;
with Interfaces.C;
with GNAT.MD5;
with Ada.Unchecked_Conversion;

package body Utilities is

   function MD5_Redux (Item : in Unsigned_20) return Unsigned_20 is
      subtype Bytes is String (1 .. 3);

      type Unsigned_24 is mod 2**24;

      function Convert is new Ada.Unchecked_Conversion
        (Source => Unsigned_24,
         Target => Bytes);
      Digest : constant String :=
        GNAT.MD5.Digest (Convert (Unsigned_24 (Item)));
   begin
      return Unsigned_20'Value
        ("16#" & Digest (Digest'Last - 4 .. Digest'Last) & "#");
   end MD5_Redux;

   function MD5_Redux (Item : in String) return Unsigned_20 is
      Digest : constant String := GNAT.MD5.Digest (Item);
   begin
      return Unsigned_20'Value
        ("16#" & Digest (Digest'Last - 4 .. Digest'Last) & "#");
   end MD5_Redux;
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
