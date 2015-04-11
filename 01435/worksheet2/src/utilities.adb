--  with Ada.Streams;
with Ada.Strings.Fixed;
with Ada.Calendar.Conversions;
with Interfaces.C;
with GNAT.MD5;
with Ada.Unchecked_Conversion;

with MD5;

package body Utilities is

   Ctx : MD5.Context;

   subtype Bytes is String (1 .. 4);

   function Convert is new Ada.Unchecked_Conversion
     (Source => Unsigned_32,
      Target => MD5.Fingerprint);

   function MD5_Redux (Item : in Unsigned_32) return Unsigned_32 is
      use MD5;

      Digest : constant Digest_String :=
        MD5.Digest_To_Text (A =>  Convert (Item));
   begin
      return Unsigned_32'Value
        ("16#" & Digest (Digest'Last - 7 .. Digest'Last) & "#");
   end MD5_Redux;

   function MD5_Redux_GNAT (Item : in Unsigned_32) return Unsigned_32 is
      function Convert is new Ada.Unchecked_Conversion
        (Source => Unsigned_32,
         Target => Bytes);

      Digest : constant String := GNAT.MD5.Digest (Convert (Item));
   begin
      return Unsigned_32'Value
        ("16#" & Digest (Digest'Last - 7 .. Digest'Last) & "#");
   end MD5_Redux_GNAT;

   function MD5_Redux (Item : in Unsigned_20) return Unsigned_20 is
      function Convert is new Ada.Unchecked_Conversion
        (Source => Unsigned_32,
         Target => Bytes);

      Digest : constant String :=
        GNAT.MD5.Digest (Convert (Unsigned_32 (Item)));
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

   function Reduction_Function
     (Cipher : in Unsigned_20;
      I      : in Unsigned_20;
      Size   : in Unsigned_20) return Unsigned_20
   is
   begin
      return (Cipher + I) mod Size;
   end Reduction_Function;

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

begin
   MD5.Init (Ctx => Ctx);

end Utilities;
