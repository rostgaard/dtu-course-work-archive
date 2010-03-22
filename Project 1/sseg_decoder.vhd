----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    17:18:32 03/22/2010 
-- Design Name: 
-- Module Name:    sseg_decoder - Behavioral 
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

entity sseg_decoder is
	port(
			top   : in  std_logic;
			digit : in  std_logic_vector(1 downto 0);
			AN    : out std_logic_vector(3 downto 0);
		   SEG   : out std_logic_vector(7 downto 0));
end sseg_decoder;

architecture Behavioral of sseg_decoder is

begin

AN <= "0111" when digit = "00" else 
      "1011" when digit = "01" else
		"1101" when digit = "10" else
		"1110" when digit = "11" else
		"0000";
SEG <= "10011100" when top = '1' else
		 "11100010" when top = '0' else
		 "00000000";

--process(top,digit)
--begin
--case digit is
--	when "00" =>
--		AN <= "0111";
--		
--	when "01" =>
--		AN <= "1011";
--
--	when "10" =>
--		AN <= "1101";
--		
--	when "11" =>
--		AN <= "1110";
--
--	when others =>
--		AN <= "0000";
--end case;
--
--case top is
--	when '1' =>
--		SEG <= "10011100";
--	when '0' =>
--		SEG <= "11100010";
--	when others =>
--		SEG <= "00000000";
--end case;
--end process;

end Behavioral;

