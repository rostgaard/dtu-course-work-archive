----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    17:08:44 03/22/2010 
-- Design Name: 
-- Module Name:    clockscaler - Behavioral 
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

entity clockscaler is
	port(
		clk : IN std_logic;
		out_clk : out std_logic);
end clockscaler;

architecture Behavioral of clockscaler is
	 signal scaled_clock: std_logic;
    signal next_count,count: std_logic_vector(31 downto 0);

begin
	process(clk)
	begin
		if (count = 10000000) then
			count <= (others => '0');
			scaled_clock <= '1';
		elsif(clk'event and clk='1') then
			count <= next_count;
			scaled_clock <= '0';
		end if;
	end process;
next_count <= count+1;
out_clk <= scaled_clock;
end Behavioral;

