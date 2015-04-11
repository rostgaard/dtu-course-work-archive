-- Listing 4.21
library ieee;
use ieee.std_logic_1164.all;
entity fifo_test is
   port(
	   clk : in std_logic;
		LEDG: out std_logic;
      BTN1: in std_logic;
		BTN2: in std_logic;
		BTN3: in std_logic;
		BTN5: in std_logic;
      SW: in std_logic_vector(7 downto 0);
      --LED: out std_logic_vector(7 downto 0);
		AN: out std_logic_vector(3 downto 0);
      SEG: out std_logic_vector(7 downto 0)
   );
end fifo_test;

architecture arch of fifo_test is
   --signal db_btn: std_logic_vector(1 downto 0);
	--signal w_data: std_logic_vector(7 downto 0);
	signal r_data: std_logic_vector(7 downto 0);
	signal dp_in : std_logic_vector(3 downto 0) := "0000";
begin
   -- debounce circuit for btn(0)
--   btn_db_unit0: entity work.debounce(fsmd_arch)
--      port map(BTN5=>clk, BTN1=>reset, sw=>btn(0),
--               db_level=>open, db_tick=>db_btn(0));
					
   -- debounce circuit for btn(1)
--   btn_db_unit1: entity work.debounce(fsmd_arch)
--      port map(clk=>clk, reset=>reset, sw=>btn(1),
--               db_level=>open, db_tick=>db_btn(1));

   display: entity work.disp_hex_mux(arch)
		port map( clk=>clk,
					 hex3=> "0000",
					 hex2=> "0000",
					 hex1=> r_data(7 downto 4),
					 hex0=> r_data(3 downto 0),
		          reset=>BTN1,
					 dp_in=>dp_in,
					 an => AN,
					 sseg => SEG);
					 


   fifo_unit: entity work.fifo(arch)
      generic map(B=>8, W=>2)
      port map(clk=>BTN5, reset=>BTN1,
               rd=>BTN2, wr=>BTN3,
               w_data=>SW, r_data=>r_data,
               full=>dp_in(3), empty=>dp_in(2));

   -- disable unused leds
   --LED(7 downto 0)<=(others=>'0');
	LEDG <= '1';
 end arch;