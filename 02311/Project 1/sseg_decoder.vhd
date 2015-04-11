----------------------------------------------------------------------------------
-- Engineer: 		Morten & Kim 
-- Create Date:    	12:13:25 03/15/2010
-- Module Name:    	sseg_decoder - Behavioral
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

-- Set in and outputs
entity sseg_decoder is
  port(
    top   : in  std_logic;
    digit : in  std_logic_vector(1 downto 0);
    AN    : out std_logic_vector(3 downto 0);
    SEG   : out std_logic_vector(7 downto 0));
end sseg_decoder;


architecture Behavioral of sseg_decoder is

begin
  -- Alters AN according to digit value ( alters digit position 0,1,2,3)
  AN <= "0111" when digit = "00" else 
        "1011" when digit = "01" else
        "1101" when digit = "10" else
        "1110" when digit = "11" else
        "0000";
  -- Alters SEG according to top value ( light up fields in the digit a, b, c and so forth. 0 means turned on )
  SEG <= "10011100" when top = '1' else
         "11100010" when top = '0' else
         "00000000";
end Behavioral;

