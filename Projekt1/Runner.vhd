----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    13:33:51 03/05/2010 
-- Design Name: 
-- Module Name:    Runner - Behavioral 
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
USE ieee.std_logic_1164.ALL;
USE ieee.std_logic_unsigned.all;
USE ieee.numeric_std.ALL;

---- Uncomment the following library declaration if instantiating
---- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity Runner is
    Port ( 
--			  reset : in  STD_LOGIC;
           clockwise : in  STD_LOGIC;
           
--			  state : in std_logic_vector(2 downto 0);
--			  next_state : out std_logic_vector(2 downto 0);
--			  digit out std_logic_vector(1 downto 0);
--           top : out  STD_LOGIC);

			  s2 : in  STD_LOGIC;
           s1 : in  STD_LOGIC;
           s0 : in  STD_LOGIC;

           n2 : out  STD_LOGIC;
           n1 : out  STD_LOGIC;
           n0 : out  STD_LOGIC;

           t : out  STD_LOGIC;
           d1 : out  STD_LOGIC;
           d0 : out  STD_LOGIC
			  );
end Runner;

architecture Behavioral of Runner is
begin
--	process(clockwise,reset)
--	begin
--		if (reset='1') then
--			s2 <= '0';
--			s1 <= '0';
--			s0 <= '0';
--			
--		elsif (clk'event and clk = '1') then
--			s2 <= n2;
--			s1 <= n1;
--			s0 <= n0;
--		end if;
--	end process;
	-- tp the regestery file
		n2 <= (not s2 and s1 and s0) or (s2 and not s1 and not s0) or (not s2 and s1 and not s0) or ( s2 and not s1 and  s0);
		n1 <= (not s2 and not s1 and s0) or (not s2 and s1 and not s0) or (s2 and not s1 and s0) or(s2 and s1 and s0 );
		n0 <=	(s2 and s1 and s0) or (s2 and s1 and s0) or (s2 and s1 and s0) or(s2 and s1 and s0);
		d0 <=	(not s2 and s0) or (s2 and not s1);
		d1 <=	(not s2 and s1 ) or (s2 and not s1);
		 t <=	(s2 and clockwise) or (not s2 and not clockwise); -- er ikke helt sikker på om denne er korrekt skrevet ind.
	
end Behavioral;

