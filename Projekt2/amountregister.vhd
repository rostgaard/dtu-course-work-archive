----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    11:21:59 04/23/2010 
-- Design Name: 
-- Module Name:    amountregister - Behavioral 
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

entity amountregister is
    Port ( input : in  STD_LOGIC_VECTOR( 3 downto 0) := (others => '0');
           total : out  STD_LOGIC_VECTOR( 3 downto 0);
			  clk : in  STD_LOGIC;
			  clr : in STD_LOGIC);
end amountregister;

architecture Behavioral of amountregister is
	signal nextamount, currentamount : STD_LOGIC_VECTOR(3 downto 0 ); 

begin
   process(clr, clk)
		begin
			if(clr='1') then 
				currentamount <= (others => '0');
			elsif(clk ='1' and clk'event) then 
				currentamount <= nextamount;
			end if;
	end process;
	
	nextamount <= input;
	total <= currentamount;
end Behavioral;

