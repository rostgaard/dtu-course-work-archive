with Ada.Text_IO; use Ada.Text_IO;
with GNAT.MD5;
with Ada.Unchecked_Conversion;
with Ada.Numerics.Discrete_Random;
with Ada.Integer_Text_IO;
--  with Ada.Streams;

with Unsigned_Types;

procedure MD5_Tests is
   use Unsigned_Types;

   package Random_Generator is new Ada.Numerics.Discrete_Random (Unsigned_20);
   use Random_Generator;

   function Reduction (Cipher : in Unsigned_20;
                       I      : in Unsigned_20;
                       Size   : in Unsigned_20) return Unsigned_20;

   function Reduction (Cipher : in Unsigned_20;
                       I      : in Unsigned_20;
                       Size   : in Unsigned_20) return Unsigned_20 is
   begin
      return (Cipher + I) mod Size;
   end Reduction;

   function MD5_Redux (Item : in Unsigned_20) return Unsigned_20;

   function MD5_Redux (Item : in Unsigned_20) return Unsigned_20 is
      subtype Bytes is String (1 .. 3) ;

      type Unsigned_24 is mod 2**24;

      function Convert is new Ada.Unchecked_Conversion
        (Source => Unsigned_24,
         Target => Bytes);

      function Convert_Back is new Ada.Unchecked_Conversion
        (Source => Bytes,
         Target => Unsigned_20);

      Str    : constant Bytes := Convert (Unsigned_24 (Item));
      Digest : constant String :=
        GNAT.MD5.Digest (Str);
      Tmp    : Unsigned_24;
   begin
      Put_Line ("Item (convert): " & Str);
      Put_Line ("Item (convert twice): " & Convert_Back (Str)'img);
      Put ("Item: " & Item'Img & " gives digest:");
      Put_Line ("Digest: " & Digest (Digest'Last-4 .. Digest'Last) & " (" & Digest & ")");

      Tmp := Unsigned_24'Value ("16#" & Digest (Digest'Last-5 .. Digest'Last)
                                & "#");
      Put_Line (Unsigned_20 (Tmp and 16#fffff#)'img);

      return Unsigned_20 (Tmp and 16#fffff#);
   end MD5_Redux;

   function MD5_Redux (Item : in String) return Unsigned_20 is
      Digest : constant String := GNAT.MD5.Digest (Item);
   begin
      Put_Line (Digest);
      Put_Line (Digest (Digest'Last-4 .. Digest'Last));
      return Unsigned_20'Value
        ("16#" & Digest (Digest'Last-4 .. Digest'Last) & "#");
   end MD5_Redux;

begin

   Put_Line (Unsigned_20 (932055)'img);
   Put_Line (Standard'Storage_Unit'Img);

   Put_Line ("a:" & MD5_Redux ("a")'img);

   Ada.Integer_Text_IO.Put (Item  => 1000,
                            Width => 0,
                            Base  => 16);

   Put_Line (MD5_Redux (Item => 6513249)'img);

end MD5_Tests;
