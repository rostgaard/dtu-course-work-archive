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

-- in and output's
entity Runner_logic is
	port (
			clockwise  : IN   std_logic;
			next_state : out std_logic_vector(2 downto 0);
			current_state : in std_logic_vector(2 downto 0);
         		top     : OUT  std_logic;
			digit	: out std_logic_vector(1 downto 0)
			);
end Runner_logic;

architecture Behavioral of Runner_logic is
begin
	-- next state logic
	next_state <= current_state + 1 when clockwise = '1' else current_state - 1;

	-- Output decoding
	digit <= not current_state(1 downto 0) when current_state(2) = '1' else current_state(1 downto 0);
	top <= not current_state(2);

end Behavioral;

