-- Listing 7.5
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
entity uart_test is
   port(
      clk, reset: in std_logic;
		LEDG: out std_logic;
      btn: std_logic_vector (2 downto 0);
      rx: in std_logic;
      tx: out std_logic;
      led: out std_logic_vector(7 downto 0);
      sseg: out std_logic_vector(7 downto 0);
      an: out std_logic_vector(3 downto 0)
   );
end uart_test;

architecture arch of uart_test is
   signal tx_full, rx_empty: std_logic;
   signal rec_data,rec_data1: std_logic_vector(7 downto 0);
   signal btn_tick: std_logic;
	signal dp_in: std_logic_vector(3 downto 0);
	signal tx_buffer_reg: std_logic_vector(7 downto 0):= X"41";
	signal tx_buffer_reg_next: std_logic_vector(7 downto 0);
	
	signal wr: std_logic;
begin
	LEDG <= '1';
   -- instantiate uart
   uart_unit: entity work.uart(str_arch)
      port map(clk=>clk, reset=>reset, rd_uart=>btn_tick,
               wr_uart=>hat, rx=>rx, w_data=>rec_data1,
               tx_full=>tx_full, rx_empty=>rx_empty,
               r_data=>rec_data, tx=>tx);
   -- instantiate debounce circuit
   btn_db_unit: entity work.debounce(fsmd_arch)
      port map(clk=>clk, reset=>reset, sw=>btn(0),
               db_level=>open, db_tick=>btn_tick);
   -- incremented data loop back

   process(clk,reset)
   begin
      if reset='1' then
         tx_buffer_reg <= X"41";
      elsif (clk'event and clk='1') and (tx_buffer_reg <= X"7b" ) then
			tx_buffer_reg <= tx_buffer_reg_next;
			wr <= '1';
      end if;
	end process;
  	tx_buffer_reg_next <= std_logic_vector(unsigned(tx_buffer_reg)+1);
	rec_data1 <= tx_buffer_reg when tx_full = '0';
	
	
   --  led display
   -- led <= rec_data;
	led <= "01010101";
   --an <= "1110";
   --sseg <= '1' & (not tx_full) & "11" & (not rx_empty) & "111";
	
	dp_in <= tx_full & rx_empty & "11";
	
	display : entity work.disp_hex_mux(arch)
		port map (clk => clk,
			reset => reset,
			hex3 => "0000",
			hex2 => "0000", 
			hex1 => "0000", 
			hex0 => rec_data(3 downto 0),
			dp_in => dp_in,
			an => an,
			sseg => sseg);
end arch;
