----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    09:11:35 03/19/2010 
-- Design Name: 
-- Module Name:    Runner - Behavioral 
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

entity Runner is
	PORT(
	   clockwise : IN   std_logic;
		clk : IN std_logic;
		reset : IN std_logic;
		d1 : OUT std_logic;
		d0 : OUT std_logic;
		top : OUT std_logic
		);
end Runner;

architecture Behavioral of Runner is
--
-- COMPONENT Runner_Registry
--    PORT(
--         reset : IN  std_logic;
--         clk   : IN  std_logic;
--         n2    : IN  std_logic;
--         n1    : IN  std_logic;
--         n0    : IN  std_logic;
--         s2    : OUT  std_logic;
--         s1    : OUT  std_logic;
--         s0    : OUT  std_logic
--        );
--    END COMPONENT;
--	 
--   COMPONENT Runner_logic
--    port (
--			clockwise : IN   std_logic;
--         s2        : IN   std_logic;
--         s1        : IN   std_logic;
--         s0        : IN   std_logic;
--         n2        : OUT  std_logic;
--         n1        : OUT  std_logic;
--         n0        : OUT  std_logic;
--         top       : OUT  std_logic;
--         d1        : OUT  std_logic;
--         d0        : OUT  std_logic
--			);
--    END COMPONENT;

	 signal n2 : std_logic;
	 signal n1 : std_logic;
	 signal n0 : std_logic;
	 signal s2 : std_logic;
	 signal s1 : std_logic;
	 signal s0 : std_logic;

begin


r_register: entity work.Runner_Registry(Behavioral) PORT MAP (
          reset => reset,
          clk => clk,
          n2 => n2,
          n1 => n1,
          n0 => n0,
          s2 => s2,
          s1 => s1,
          s0 => s0
        );
		  
r_logic: entity work.Runner_logic(Behavioral) PORT MAP (
			clockwise => clockwise,
			s2 => s2,
			s1 => s1,
			s0 => s0,
			n2 => n2,
			n1 => n1,
			n0 => n0,
			top => top,
			d1 => d1,
			d0 => d0
			);
			

end Behavioral;

