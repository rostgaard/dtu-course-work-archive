----------------------------------------------------------------------------------
-- Engineer: 		Morten & Kim 
-- Create Date:    	17:08:44 03/22/2010 
-- Module Name:    	clockscaler - Behavioral 
-- Project Name:   	Project 1
-- Description: 
-- Dependencies: 
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Clockscaler entity's ( in and out-puts )
entity clockscaler is
	port(
		clk : IN std_logic;
		out_clk : out std_logic);
end clockscaler;

-- clockscaler architecture ( what do we do with the data )
architecture Behavioral of clockscaler is
	signal scaled_clock: std_logic;
    	signal next_count,count: std_logic_vector(31 downto 0);

begin
	-- when the clk alters we process this
	process(clk)
	begin
			-- when count is 10000000 it sets scaled_clock to 1
			if (count = 10000000) then
			count <= (others => '0');
			scaled_clock <= '1';

			-- resets scaled_clock after use
			elsif(clk'event and clk='1') then
			count <= next_count;
			scaled_clock <= '0';
			end if;
	end process;

-- enumerates count
next_count <= count+1;
out_clk <= scaled_clock;
end Behavioral;

