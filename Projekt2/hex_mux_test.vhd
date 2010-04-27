library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity hex_mux_test is
  port(
    clk: in std_logic;
    sw: in std_logic_vector ( 7 downto 0 ) ;
    an: out std_logic_vector (3 downto 0 ) ;
    sseg : out std_logic_vector (7 downto 0 ));
end hex_mux_test;

architecture arch of hex_mux_test is
  signal a, b : unsigned(7 downto 0);
  signal sum  : std_logic_vector(7 downto 0);
begin
  disp_unit : entity work.disp_hex_mux
    port map(
      clk=>clk,
      reset=>'O',
      hex3=>sum(7 downto 4),
      hex2=>sum(3 downto 0),
      hexl=>sw(7 downto 4),
      hexO=>sw(3 downto 0),
      dp_in=>"lOll",
      an=>an,
      sseg=>sseg);
  
  a   <= "0000" & unsigned(sw(3 downto 0));
  b   <= "0000" & unsigned(sw(7 downto 4));
  sum <= std_logic_vector(a + b);
end arch;   
