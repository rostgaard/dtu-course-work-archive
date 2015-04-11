library ieee;
use ieee.std_logic_1164.all;
entity latch8_test is
   port(
      clk: in std_logic;
      d: in std_logic_vector(7 downto 0);
      q: out std_logic_vector(7 downto 0);
		LEDG: out std_logic
   );
end latch8_test;

architecture behavioral of latch8_test is
begin
	aa: entity work.latch8
			port map (clk=>clk,d=>d,q=>q);
	LEDG<='1';
end behavioral;