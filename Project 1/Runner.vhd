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
	 signal scaled_clock : std_logic;

	begin

scaler: entity work.clockscaler(Behavioral) port map
		(clk => clk,
		 out_clk => scaled_clock);

r_logic: entity work.Runner_logic(Behavioral) PORT MAP (
			clockwise => clockwise,
			current_state => current_state,
			next_state => next_state,
			top => top,
			digit => digit
			);
			
sseg: entity work.sseg_decoder(Behavioral) port map (
			top => top,
			digit => digit,
			AN => AN,
			SEG => SEG);

r_register: entity work.Runner_Registry(Behavioral) PORT MAP (
          reset => reset,
          clk => scaled_clock,
			 current_state => current_state,
			 next_state => next_state
         );

LEDG <= '1';
end Behavioral;

