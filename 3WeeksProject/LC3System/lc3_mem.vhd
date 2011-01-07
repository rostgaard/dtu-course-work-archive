-- Listing 11.1
-- Single-port RAM with synchronous read
-- Modified from XST 8.1i rams_07
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
entity lc3_mem is
  generic(
    ADDR_WIDTH: integer:=12;
    DATA_WIDTH: integer:=8
    );
  port(
    clk: in std_logic;
    we, re: in std_logic;
    addr: in std_logic_vector(ADDR_WIDTH-1 downto 0);
    data: inout std_logic_vector(DATA_WIDTH-1 downto 0)
    );
end lc3_mem;

architecture beh_arch of lc3_mem is
  type ram_type is array (2**ADDR_WIDTH-1 downto 0)
    of std_logic_vector (DATA_WIDTH-1 downto 0);
  signal ram: ram_type;
  signal addr_reg: std_logic_vector(ADDR_WIDTH-1 downto 0);
  signal data_out: std_logic_vector(ADDR_WIDTH-1 downto 0);
  signal memory_signal: std_logic_vector(ADDR_WIDTH-1 downto 0);
  signal memory_map: std_logic_vector(ADDR_WIDTH-1 downto 0); 
  signal cs: std_logic;
  
begin
  
  process (clk)
  begin
    if (clk'event and clk = '1') then
      if (we='1') then
        ram(to_integer(unsigned(addr))) <= data;
      end if;
      addr_reg <= addr;
    end if;
  end process;
  
  data_out <= ram(to_integer(unsigned(addr_reg))) when cs = '1' else memory_map;
  
  -- Tri state buffer asserts read enable
  memory_signal <=  ram(to_integer(unsigned(addr_reg))) when re = '1' else (others => 'Z');
  ram(to_integer(unsigned(addr_reg))) <= memory_signal;

  -- Chip selector
  cs <= '0' when addr(15 downto 8) = X"FE" else '1';
  
  data <= memory_signal when cs = '1' else memory_map;
  
  

  -- TODO routing for memory map with tri-state buffer in front
  memory_map <= mmap_route when re = '1' else (others => 'Z');

  mmap_route <= memory_map;

  --   Stdin Status Register
  mmap_route <= uart_rx_status_reg when addr(7 downto 0) = X"00" else
  -- xFE02 Stdin Data Register
                uart_rx_data_reg when addr(7 downto 0) = X"02" else
  -- xFE04 Stdout Status Register
                uart_tx_status_reg when addr(7 downto 0) = X"04" else
  -- xFE06 Stdout Data Register
                uart_tx_data_reg when addr(7 downto 0) = X"06" else
  -- xFE0A Switches Data Register
                sw_data_reg when addr(7 downto 0) = X"0A" else
  -- xFE0E Buttons Data Register
                button_data_reg when addr(7 downto 0) = X"0E" else
  -- xFE12 7SegDisplay Data Register
                sseg_data_register when addr(7 downto 0) = X"12" else
  -- xFE16 Leds Data Register
                leds_data_reg when addr(7 downto 0) = X"06" else
                
end beh_arch;
