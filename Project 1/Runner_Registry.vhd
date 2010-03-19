----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    15:22:49 03/14/2010 
-- Design Name: 
-- Module Name:    Runner_Registry - Behavioral 
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

entity Runner_Registry is
	port(
         reset : IN  std_logic;
         clk   : IN  std_logic;
         n2    : IN  std_logic;
         n1    : IN  std_logic;
         n0    : IN  std_logic;
         s2    : OUT  std_logic;
         s1    : OUT  std_logic;
         s0    : OUT  std_logic
	);
end Runner_Registry;

architecture Behavioral of Runner_Registry is
begin
	process(clk,reset)
	begin
		if (reset = '1') then 
			s2 <= '0';
			s1 <= '0';
			s0 <= '0';
		elsif (clk = '1' and clk'event) then
			s1 <= n1;
			s2 <= n2;
			s0 <=	n0;
		end if;
	end process;
end Behavioral;

