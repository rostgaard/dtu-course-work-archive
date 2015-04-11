library ieee;
use ieee.std_logic_1164.all;
entity reg_reset_test is
   port(
      clk: in std_logic;
		reset: in std_logic;
      d: in std_logic_vector(7 downto 0);
      q: out std_logic_vector(7 downto 0);
		LEDG: out std_logic
   );
end reg_reset_test;

architecture behavioral of reg_reset_test is
begin
	aa: entity work.reg_reset
			port map (clk=>clk,reset=>reset,d=>d,q=>q);
	LEDG<='1';
end behavioral;

