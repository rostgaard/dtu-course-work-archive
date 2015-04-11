with Ada.Text_IO; use Ada.Text_IO;
--  with Ada.Real_Time;
with Ada.Numerics.Discrete_Random;
with Ada.Float_Text_IO;

with Utilities;
with Unsigned_Types;
with Definition_Map;

procedure Exercise_15 is
   use Utilities;
   use Unsigned_Types;
   --  use Ada.Real_Time;

   package Random_Generator is new Ada.Numerics.Discrete_Random (Unsigned_32);
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

   M : constant Natural := 16;
   T : constant Natural := 8;

   G : Generator;
   --  S : Ada.Real_Time.Time := Ada.Real_Time.Clock;
begin

   for I in 0 .. 2**M loop
      declare
         Val  : Unsigned_32 := Random (G);
         --  Now   : Duration    := To_Duration (Ada.Real_Time.Clock - S);
      begin
         Reset (G);

         for J in Unsigned_20 (0) .. Unsigned_20 (2**T) loop
            Val := MD5_Redux (Val) and 16#FFFFF#;
            Val := Unsigned_32
              (Reduction (Cipher => Unsigned_20 (Val),
                          I      => 0,
                          Size   => Unsigned_20'Last));
            Definition_Map.Add (Unsigned_20 (Val));
         end loop;
      end;

      if I mod 100 = 0 then
         Ada.Float_Text_IO.Put (Item => Definition_Map.Coverage,
                                Fore => 0,
                                Aft  => 6,
                                Exp  => 0);

         Ada.Float_Text_IO.Put (Item => Float (Definition_Map.Collisions)
                                / 100_000_000.0,
                                Fore => 3,
                                Aft  => 6,
                                Exp  => 0);

         New_Line;
      end if;

   end loop;
   --  Generate chains

end Exercise_15;
