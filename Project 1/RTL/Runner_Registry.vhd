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

entity Runner_Registry is
	port(
         reset : IN  std_logic;
         clk   : IN  std_logic;
			next_state : in std_logic_vector;
			current_state : out std_logic_vector
	);
end Runner_Registry;

architecture Behavioral of Runner_Registry is
begin
	process(clk,reset)
	begin
		if (reset = '1') then 
		   current_state <= "000";
		elsif (clk = '1' and clk'event) then
		   current_state <= next_state;
		end if;
	end process;
end Behavioral;

