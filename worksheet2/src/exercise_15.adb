with Ada.Text_IO; use Ada.Text_IO;
with GNAT.MD5;
with Ada.Unchecked_Conversion;
with Ada.Real_Time;
with Ada.Numerics.Discrete_Random;
with Ada.Float_Text_IO;
with Ada.Integer_Text_IO;

with Utilities;
with Unsigned_Types;
with Definition_Map;

procedure Exercise_15 is
   use Unsigned_Types;
   use Ada.Real_Time;

   package Random_Generator is new Ada.Numerics.Discrete_Random (Unsigned_20);
   use Random_Generator;

   function MD5_Redux (Item : in String) return Unsigned_20;
   function MD5_Redux (Item : in Unsigned_20) return Unsigned_20;

   function MD5_Redux (Item : in Unsigned_20) return Unsigned_20 is
      type Unsigned_24 is mod 2 ** 24;
      subtype String_5 is String (1 .. 3);

      function Convert is new Ada.Unchecked_Conversion (Source => Unsigned_24,
                                                        Target => String_5);
   begin
      return MD5_Redux (Item => Convert (S => Unsigned_24 (Item)));
   end MD5_Redux;

   function MD5_Redux (Item : in String) return Unsigned_20 is
      Str : constant String := GNAT.MD5.Digest (Item);
   begin
      return Unsigned_20'Value ("16#" & Str (1 .. 5) & "#");
   end MD5_Redux;

   function Reduction (Cipher : in Unsigned_20;
                       I      : in Unsigned_20;
                       Size   : in Unsigned_20) return Unsigned_20 is
   begin
      return (Cipher + I) mod Size;
   end Reduction;

   M : constant Natural := 16;
   T : constant Natural := 8;

   G : Generator;
   S : Ada.Real_Time.Time := Ada.Real_Time.Clock;
begin

   for I in 0 .. 2**M loop
      declare
         Val   : Unsigned_20 := Random (G);
         Now   : Duration    := To_Duration (Ada.Real_Time.Clock - S);
      begin

         Put (Now'Img & " ");

         Ada.Float_Text_IO.Put (Item => Definition_Map.Coverage,
                                Fore => 0,
                                Aft  => 6,
                                Exp  => 0);

         Ada.Float_Text_IO.Put (Item => Float (Definition_Map.Collisions) / 10_000_000.0,
                                Fore => 3,
                                Aft  => 6,
                                Exp  => 0);

         New_Line;
         Reset (G);

         for J in Unsigned_20 (0) .. Unsigned_20 (2**T) loop

            Val := Reduction (Cipher => MD5_Redux (Val),
                              I      => 1,
                              Size   => Unsigned_20'Last);
            Definition_Map.Add (Val);
         end loop;
      end;

   end loop;
   --  Generate chains

end Exercise_15;
