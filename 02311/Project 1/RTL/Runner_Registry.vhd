----------------------------------------------------------------------------------
-- Engineer: 		Morten & Kim 
-- Create Date:    	12:13:25 03/15/2010
-- Module Name:    	Runner_Registry
-- Project Name:   	Project 1
-- Description: 
-- Dependencies: 
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments:  
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- in and out-puts
entity Runner_Registry is
  port(
    reset : IN  std_logic;
    clk   : IN  std_logic;
    next_state : in std_logic_vector;
    current_state : out std_logic_vector);
end Runner_Registry;

architecture Behavioral of Runner_Registry is
begin
  -- When clk or reset i altered process this
  process(clk,reset)
  begin
    -- If reset is set high (button5 pressed) set current state to start position "000" 
    if (reset = '1') then
      current_state <= "000";
                          
    -- sets next state when clk is high ( 1 ), and its the start of the high ( Edge ).
    elsif (clk = '1' and clk'event) then
      current_state <= next_state;
    end if;
  end process;
end Behavioral;

