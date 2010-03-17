----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    10:30:07 03/14/2010 
-- Design Name: 
-- Module Name:    Runner - Runner_arch 
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

entity Runner is
	port(
		clockwise : IN  std_logic;
         s2 : IN  std_logic;
         s1 : IN  std_logic;
         s0 : IN  std_logic;
			
         n2 : OUT  std_logic;
         n1 : OUT  std_logic;
         n0 : OUT  std_logic;
         top : OUT  std_logic;
         d1 : OUT  std_logic;
         d0 : OUT  std_logic

	);
end Runner;

architecture Runner_arch of Runner is

begin
	
--		n2 <= (not s2 and     s1 and     s0) or 
--		      (    s2 and not s1 and not s0) or 
--				(    s2 and not s1 and     s0) or 
--				(    s2 and     s1 and not s0);
--				

		n1 <= (not s2 and not s1 and     s0) or 
		      (not s2 and     s1 and not s0) or 
				(    s2 and not s1 and     s0) or
				(    s2 and     s1 and not s0 );
				
		n2 <= (s2 and not s1) or (s1 and ((not s2 and s0) or (s2 and not s0)));


				
		n0 <=	(not s2 and not s1 and not s0) or 
		      (not s2 and     s1 and not s0) or 
				(    s2 and not s1 and not s0) or
				(    s2 and     s1 and not s0);

		d1 <=	(not s2 and     s1 and not s0) or 
		   (not s2 and     s1 and     s0) or 
			(    s2 and not s1 and not s0) or
			(    s2 and not s1 and     s0);
	

	d0 <=	(not s2 and not s1 and     s0) or 
		   (not s2 and     s1 and     s0) or 
			(    s2 and not s1 and not s0) or
			(    s2 and     s1 and not s0);
			
			
	top  <=	(not s2 and not s1 and not s0) or 
		   (not s2 and not s1 and     s0) or 
			(not s2 and     s1 and not s0) or
			(not s2 and     s1 and not s0);				
--		d0 <=	(not s2 and s0) or (s2 and not s1);
--		d1 <=	(not s2 and s1 ) or (s2 and not s1);
--		 t <=	(s2 and clockwise) or (not s2 and not clockwise); -- er ikke helt sikker på om denne er korrekt skrevet ind.
--			
--		end if;


end Runner_arch;

