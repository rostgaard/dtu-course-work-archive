----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    12:10:57 03/15/2010 
-- Design Name: 
-- Module Name:    Runner_logic - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

---- Uncomment the following library declaration if instantiating
---- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity Runner_logic is
	port (
			clockwise : IN   std_logic;
         s2        : IN   std_logic;
         s1        : IN   std_logic;
         s0        : IN   std_logic;
         n2        : OUT  std_logic;
         n1        : OUT  std_logic;
         n0        : OUT  std_logic;
         top       : OUT  std_logic;
         d1        : OUT  std_logic;
         d0        : OUT  std_logic
			);
end Runner_logic;

architecture Behavioral of Runner_logic is
begin

		n2 <= ( ( (s2 and not s1) or (s1 and ((not s2 and s0) or (s2 and not s0)))) and clockwise) or
		      ((( not s2 and not s1 and not s0) or
				  (     s2 and not s1 and     s0) or
				  (     s2 and     s1 and not s0) or
				  (     s2 and     s1 and     s0) ) and not clockwise);
	

		n1 <= (( (not s1 and s0) or (s1 and not s0) )and clockwise) or
		      ((( not s2 and not s1 and not s0) or
				  ( not s2 and     s1 and     s0) or
				  (     s2 and not s1 and not s0) or
				  (     s2 and     s1 and     s0) ) and not clockwise);
				  
		n0 <=	not s0 or
				((( not s2 and not s1 and not s0) or
				  ( not s2 and     s1 and not s0) or
				  (     s2 and not s1 and not s0) or
				  (     s2 and     s1 and not s0) ) and not clockwise);

		d1 <= (not s2 and s1) or (s2 and not s1);
		d0 <=	(not s2 and s0) or (s2 and not s0);
      top  <=	not s2;



end Behavioral;

