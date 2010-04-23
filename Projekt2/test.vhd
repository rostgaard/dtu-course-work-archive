----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    14:50:00 04/23/2010 
-- Design Name: 
-- Module Name:    test - Behavioral 
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

entity test is port (
	kr1 : in  STD_LOGIC;
	kr2 : in  STD_LOGIC;
	clk : in  STD_LOGIC;
	amount : out STD_LOGIC_VECTOR(3 downto 0));
end test;

architecture Behavioral of test is
type StateType is (init, purchase, add1, add2);
signal state_reg, state_next : StateType := init;
begin

NextStateDecoding : process (state_reg)
begin
	--Init values
	state_next <= state_reg;
	--Assignments
		case state_reg is
			when init => 
				state_next <= purchase;
			when purchase =>
				if(kr1 = '1') then 
					state_next <= add1;
				elsif (kr2 = '1') then 
					state_next <= add2;
				end if;
			when add1 =>
				state_next <= purchase;
			when add2 =>
				state_next <= purchase;
			when others => 
				state_next <= init;
		end case;
	end process NextStateDecoding;

StateRegister: process (clk)
begin
	if clk'event and clk = '1' then
		state_reg <= state_next;
	end if;
end process;

	OutputDecoding : process (state_reg)
	begin
		case state_reg is
			when init => 
				amount <= "0001";

			when purchase =>
				amount <= "0010";
			when add1 =>
				amount <= "0011";
			when add2 =>
				amount <= "0100";
			when others => 
				amount <= "0000";
		end case;
	end process OutputDecoding;
--Initial

end Behavioral;
