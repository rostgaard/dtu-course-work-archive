----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    10:19:36 04/23/2010 
-- Design Name: 
-- Module Name:    Register - Behavioral 
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

entity stateregister is
    Port ( reset : in  STD_LOGIC;
           kr1 : in  STD_LOGIC;
           kr2 : in  STD_LOGIC;
			  clk : in STD_LOGIC;
           amount : out  STD_LOGIC_VECTOR(3 downto 0));
end stateregister;

architecture Behavioral of stateregister is
	type statetype is (init, purchase, add1, add2);
	signal currentstate, nextstate : statetype := init;	
   signal addamount: STD_LOGIC_VECTOR ( 3 downto 0 );
	
	signal reset_total: STD_LOGIC;
	
	signal display_total : STD_LOGIC_VECTOR(3 downto 0);
begin
	amountreg: entity work.amountregister(Behavioral) port map (
		addamount => addamount,
		clk => clk,
		total => display_total,
		clr => reset_total
	);


	statereg : process(reset, clk)
		begin
			if(reset='1') then currentstate <= init;
			elsif(clk ='1' and clk'event) then currentstate <= nextstate;
			end if;
	end process;
	
	-- next state logic
	
	nextstatelogic : process (kr1, kr2, currentstate) 
		begin
			case currentstate is
				when init => 
					nextstate <= purchase;
					reset_total <= '1';
				when add1 =>
					nextstate <= purchase; 
				when add2 => 
					nextstate <= purchase;
				when purchase =>
					if (kr1 = '1') then nextstate <= add1;
					elsif (kr2 = '1') then nextstate <= add2;
					end if;
			end case;
		end process;
		
		-- outputlogic
	outputlogic : process (currentstate)
	   begin
		case currentstate is
				when init =>
				   -- clear counters
				   reset_total <= '1';
					
				when add1 =>
					addamount <= "0001";
					-- increment amount register by one
				when add2 => 
					-- increment amount register by two
					addamount <= "0010";
				when purchase =>
				   -- check amount
			end case;
      end process;
	amount <= "0000";
end Behavioral;

