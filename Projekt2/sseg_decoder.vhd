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
    clk   : in  std_logic;
    char  : in  character;
    digit : in  std_logic_vector(1 downto 0));
end sseg_decoder;


architecture Behavioral of sseg_decoder is
  signal digit1, digit2, digit3, digit4 std_logic_vector(7 downto 0);
  signal current_digit std_logic_vector(2 downto 0);
begin
  process(clk,char,digit)
  begin
    if (clk = '1' and clk'event) then
      current_digit <= current_digit+1;
    end if;

    if (char = 1) then
      digit1 <= "10011111";
    end if;

    if (char = 2) then
      digit1 <= "00100101";
    end if;

  -- and so on
  end process;

  -- Alters AN according to digit value ( alters digit position 0,1,2,3)
  AN <= "1110" when digit = "00" else 
        "1101" when digit = "01" else
        "1011" when digit = "10" else
        "0111" when digit = "11" else
        "0000";

  -- Alters SEG according to top value ( light up fields in the digit a, b, c and so forth. 0 means turned on )
  -- Chars available: 1,2,3,4,5,6,7,8,9,a,c,e,f,g,h,j,l,p,u,-,_
  --    a
  --  -----
  -- f| g | b
  --  -----
  -- e|   | c
  --  -----
  --    d

  SEG <= digit1 when digit = "00" else 
         digit2 when digit = "01" else
         digit3 when digit = "10" else
         digit4 when digit = "11" else
         "0000";
end Behavioral;

