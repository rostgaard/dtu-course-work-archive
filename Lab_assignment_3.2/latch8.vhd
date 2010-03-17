----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    13:49:34 02/26/2010 
-- Design Name: 
-- Module Name:    latch8 - Behavioral 
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
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE ieee.std_logic_unsigned.all;
USE ieee.numeric_std.ALL;


entity latch8 is
	port(
		clk: in std_logic;
		d: in std_logic_vector(7 downto 0);
		q: out std_logic_vector(7 downto 0)
	);
end latch8;

architecture arch of latch8 is
begin 
	process(clk)
	begin
		if (clk = '1') then
			q <= d;

		end if;
	end process;
end arch;