----------------------------------------------------------------------------------
-- Engineer: 		Morten & Kim 
-- Create Date:    	12:13:25 03/15/2010
-- Module Name:    	Runner_logic - Behavioral
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

-- In and out-puts
entity Runner_logic is
	port (
			clockwise  : IN   std_logic;
			next_state : out std_logic_vector(2 downto 0);
			current_state : in std_logic_vector(2 downto 0);
         		top       : OUT  std_logic;
			digit		 : out std_logic_vector(1 downto 0)
			);
end Runner_logic;

-- Next state logic found in our truthTable
architecture Behavioral of Runner_logic is
begin
		next_state(2) <= 
		  (((current_state(2) and not current_state(1)) or 
		    (current_state(1) and ((not current_state(2) and current_state(0)) or 
			 (current_state(2) and not current_state(0))))) and clockwise) or (
         ((not current_state(1) and (( current_state(2) and current_state(0)) or 
			 (not current_state(2) and not current_state(0)))) or 
          (current_state(2) and current_state(1))) and not clockwise);
			 
		next_state(1) <= 
		  (((not current_state(1) and current_state(0)) or (current_state(1) and not current_state(0)) )and clockwise) or
        (((not current_state(1) and not current_state(0)) or (current_state(1) and     current_state(0)) )and not clockwise);

		next_state(0) <=	not current_state(0) ;

		digit(1) <= (not current_state(2) and current_state(1)) or (current_state(2) and not current_state(1));
		digit(0) <=	(not current_state(2) and current_state(0)) or (current_state(2) and not current_state(0));
      top  <=	not current_state(2);

end Behavioral;

