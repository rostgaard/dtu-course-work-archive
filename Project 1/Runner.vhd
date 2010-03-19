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
		AN : OUT std_logic_vector(3 downto 0);
		SEG : OUT std_logic_vector(7 downto 0);
		LEDG : OUT std_logic;
		
		reset: IN std_logic
		);
end Runner;

architecture Behavioral of Runner is

	 signal next_state : std_logic_vector(2 downto 0);
	 signal current_state : std_logic_vector(2 downto 0);
	 signal digit : std_logic_vector (1 downto 0);
	 signal top : std_logic;

	begin

r_logic: entity work.Runner_logic(Behavioral) PORT MAP (
			clockwise => clockwise,
			s2 => current_state(2),
			s1 => current_state(1),
			s0 => current_state(0),
			n2 => next_state(2),
			n1 => next_state(1),
			n0 => next_state(0),
			top => top,
			d1 => digit(1),
			d0 => digit(0)
			);
			

r_register: entity work.Runner_Registry(Behavioral) PORT MAP (
          reset => reset,
          clk => clk,
          n2 => next_state(2),
          n1 => next_state(1),
          n0 => next_state(0),
          s2 => current_state(2),
          s1 => current_state(1),
          s0 => current_state(0)
         );

process(top,digit)
begin
case digit is
	when "00" =>
		AN <= "0111";
		
	when "01" =>
		AN <= "1011";

	when "10" =>
		AN <= "1101";
		
	when "11" =>
		AN <= "1110";

	when others =>
		AN <= "0000";

end case;

case top is
	when '1' =>
		SEG <= "10011100";
	when '0' =>
		SEG <= "11100010";
	when others =>
		SEG <= "00000000";
end case;
	
end process;


LEDG <= '1';
end Behavioral;

