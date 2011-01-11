----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    11:12:04 01/11/2011 
-- Design Name: 
-- Module Name:    memory_map - Behavioral 
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

entity memory_map is
end memory_map;

architecture Behavioral of memory_map is

begin

   -- instantiate uart
   uart_unit: entity work.uart(str_arch)
      port map(clk=>clk, 
					reset=>reset, 
					rd_uart=> cs_stdin_data,
               wr_uart=> cs_stdout_data,
					rx=>rx, 
					w_data=>uart_w_data,
               tx_full=>tx_full, 
					rx_empty=>rx_empty,
               r_data=>uart_r_data, 
					tx=>tx);

  --reserved space in software memory, rest is for I/O
	cs_mem <= '1' when addr >= X"0000" and addr <= X"DFFF" else '0';
  -- cs_mem is In/Out. This is the main memory
  data <= ram(to_integer(unsigned(addr_reg))) when re = '1' and cs_mem = '1' 
    else (others => 'Z');
	 
  -- xFE00 Stdin Status Register
  cs_stdin_status <= '1' when addr = X"FE00" and re = '1' else '0';
  data <= not rx_empty & "000" & X"000" when cs_stdin_status = '1' 
    else (others => 'Z');

  -- xFE02 Stdin Data Register
  cs_stdin_data <= '1' when addr = X"FE02" and re = '1' else '0';
	-- cs_stdin_data  is out
  data <= uart_r_data when cs_stdin_data = '1' 
    else (others => 'Z');

  -- xFE04 Stdout Status Register
  cs_stdout_status <= '1' when re = '1' and addr = X"FE04" else '0';
	-- cs_stdout_status  is out
  data <= not tx_full & "000" & X"000" when cs_stdout_status = '1' 
    else (others => 'Z');		

  -- xFE06 Stdout Data Register
  cs_stdout_data <= '1' when addr = X"FE06" and we = '1' else '0';
 	-- cs_stout_data is in 
  uart_w_data <= data(7 downto 0);

  -- xFE0A Switches Data Register
  cs_switch_data <= '1' when addr = X"FE0A" and re = '1' else '0';
  --cs_switch_data  is out
  data <= sw when re = '1' and cs_switch_data = '1' 
    else (others => 'Z');		

  -- xFE0E Buttons Data Register
  cs_btn_data <= '1' when addr = X"FE0E" and re = '1' else '0';
  -- cs_btn_data  is out
  data <= X"00" & "000" & btn when cs_btn_data = '1' 
    else (others => 'Z');

  -- xFE12 7SegDisplay Data Register
  cs_sseg <= '1' when addr = X"FE12" and we='1' else '0';
	-- cs_sseg  is in 
  process (clk)
  begin
    if (clk'event and clk = '1') then
      if cs_sseg = '1' then
        sseg_reg <= data;
      end if;
    end if;
  end process;		

  -- xFE16 Leds Data Register
  cs_led <= '1' when addr = X"FE16" and we='1' else '0';
 	-- cs_leds  is in 
  process (clk)
  begin
    if (clk'event and clk = '1') then
      if cs_led = '1' then
        leds_reg <= data (7 downto 0);
      end if;
    end if;
  end process;	 

end Behavioral;

