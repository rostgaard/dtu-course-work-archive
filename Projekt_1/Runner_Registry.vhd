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
			reset, clk, n2,n1,n0 : in std_logic;
			--reset, clk, n2,n1,n0 : in std_logic;
			s2,s1,s0 : out std_logic
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

