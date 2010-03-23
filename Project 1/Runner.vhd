----------------------------------------------------------------------------------
-- Engineer: 		Morten & Kim 
-- Create Date:    	12:13:25 03/15/2010
-- Module Name:    	Runner - Behavioral
-- Project Name:   	Project 1
-- Description: 
-- Dependencies: 
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: Thi is "the big box that connects all the small boxes and sets their attributes"
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Input and outputs
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
	 signal scaled_clock : std_logic;

	begin
-- Sets entity for clockscaler
scaler: entity work.clockscaler(Behavioral) port map
		(clk => clk,
		 out_clk => scaled_clock);

-- Sets entity for Runner_logic
r_logic: entity work.Runner_logic(Behavioral) PORT MAP (
			clockwise => clockwise,
			current_state => current_state,
			next_state => next_state,
			top => top,
			digit => digit
			);

-- Sets entity for sseg_decoder			
sseg: entity work.sseg_decoder(Behavioral) port map (
			top => top,
			digit => digit,
			AN => AN,
			SEG => SEG);

-- Sets entity for Runner_Registry
r_register: entity work.Runner_Registry(Behavioral) PORT MAP (
          reset => reset,
          clk => scaled_clock,
			 current_state => current_state,
			 next_state => next_state
         );

-- This just has to be set for th leds to work on our board.
LEDG <= '1';
end Behavioral;

