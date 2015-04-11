----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    15:04:25 09/01/2010 
-- Design Name: 
-- Module Name:    Assignment1 - Behavioral 
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

entity Assignment1 is
    Port ( clk, we, reset : in  STD_LOGIC;
				LEDG : out STD_LOGIC;
           input : in  STD_LOGIC_VECTOR (7 downto 0);
           output : out  STD_LOGIC_VECTOR (7 downto 0)
			 );
	--signal value : STD_LOGIC_VECTOR (7 downto 0);
end Assignment1;

architecture Behavioral of Assignment1 is
begin
process (clk, reset)
	begin
		if (reset='1') then
			output  <= (others => '0');
		elsif(clk='1' and clk'event and we='1') then
			output <= input;
		end if;
end process;
LEDG <= '1';
-- output <= value;
end Behavioral;

