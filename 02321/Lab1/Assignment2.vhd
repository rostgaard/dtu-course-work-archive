----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    15:41:59 09/01/2010 
-- Design Name: 
-- Module Name:    Assignment2 - Behavioral 
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
use IEEE.NUMERIC_STD.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

---- Uncomment the following library declaration if instantiating
---- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity Assignment2 is
	generic (
		B: integer:=4;
		W: integer:=16
	);
    Port ( 
			  clk,reset : in  STD_LOGIC;
           wr_en : in  STD_LOGIC;
           w_addr : in  STD_LOGIC_VECTOR (W-1 downto 0);
           w_data : in  STD_LOGIC_VECTOR (W-1 downto 0);
           r_data : out  STD_LOGIC_VECTOR (B-1 downto 0)
			 );
end Assignment2;

architecture Behavioral of Assignment2 is
	type assignment2_type is array (2**W-1 downto 0) of
		STD_LOGIC_VECTOR (B-1 downto 0);
	signal array_reg : assignment2_type;
begin
process(clk, reset)
	begin
		if (reset='1') then
			array_reg <= (others => (others => '0'));
		elsif (clk'event and clk='1') then
			if wr_en='1' then
				array_reg(to_integer(unsigned(w_addr))) <= w_data;
			end if;
		end if;
end process;
	r_data <= array_reg(to_integer(unsigned(w_addr)));
end Behavioral;

