-- Listing 2.1
library ieee;
use ieee.std_logic_1164.all;
--
--entity eq1 is
--   port(
--      i0, i1: in std_logic;
--      eq: out std_logic
--   );
--end eq1;
--
--architecture sop_arch of eq1 is
--   signal p0, p1: std_logic;
--begin
--   -- sum of two product terms
--   eq <= p0 or p1;
--   -- product terms
--   p0 <= (not i0) and (not i1);
--   p1 <= i0 and i1;
--end sop_arch;

entity agtb4bit is
   port(
      i0, i1, i2, i3: in std_logic;
      eq: out std_logic
   );
end agtb4bit;

architecture agtb4bit_arch of agtb4bit is
   signal p0, p1, p3: std_logic;
begin
   -- sum of two product terms
   eq <= p0 or p1 or p3;
   -- product terms
   p0 <= (i0 or i1) and i2 and i3;
   p1 <= i1 and i3 and not i2 ;
	p3 <= i0 and i1 and i3 and not i2;
end agtb4bit_arch;
