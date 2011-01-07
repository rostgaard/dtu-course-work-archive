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
	 reset: in std_logic;
    we, re: in std_logic; -- Write and read enable
    addr: in std_logic_vector(ADDR_WIDTH-1 downto 0);
    data: inout std_logic_vector(DATA_WIDTH-1 downto 0);
	 sw: in std_logic_vector(7 downto 0) 
    );
end lc3_mem;

architecture beh_arch of lc3_mem is
  type ram_type is array (2**ADDR_WIDTH-1 downto 0)
    of std_logic_vector (DATA_WIDTH-1 downto 0);
  signal ram: ram_type;
  signal addr_reg: std_logic_vector(ADDR_WIDTH-1 downto 0);
  
  signal data_in: std_logic_vector(ADDR_WIDTH-1 downto 0);
  signal data_out: std_logic_vector(ADDR_WIDTH-1 downto 0);

  signal ram_signal: std_logic_vector(ADDR_WIDTH-1 downto 0);
  signal memory_map: std_logic_vector(ADDR_WIDTH-1 downto 0); 
  
  signal cs_mem: std_logic;
  
  signal cs_stdin_status: std_logic;
  signal cs_stdin_data: std_logic;
  
  signal cs_stdout_status: std_logic;
  signal cs_stdout_data: std_logic;
  
  signal cs_switch_data: std_logic;
  signal cs_btn_data: std_logic;
  signal cs_sseg_data: std_logic;
  signal cs_led_data: std_logic;
  
begin

   -- instantiate uart
   uart_unit: entity work.uart(str_arch)
      port map(clk=>clk, 
					reset=>reset, 
					rd_uart=> cs_stdin_status,
               wr_uart=> cs_stdout_status,
					rx=>rx, 
					w_data=>uart_w_data,
               tx_full=>tx_full, 
					rx_empty=>rx_empty,
               r_data=>uart_r_data, 
					tx=>tx);



  process (addr_reg)
  begin
    cs_mem <= '0';
	 cs_stdin_status <= '0';
	 cs_stdin_data <= '0';
	 cs_stdout_status <= '0';
	 cs_stdout_data <= '0';
	 cs_switch_data <= '0';
	 cs_btn_data <= '0';
	 cs_sseg_data <= '0';
	 cs_led_data <= '0';
   
	 if addr_reg >= X"0000" AND addr_reg <= X"DFFF" then --reserved space in software memory, rest is for I/O
	   cs_mem <= '1';
		-- xFE00 Stdin Status Register
    elsif addr_reg = X"FE00" then
	   cs_stdin_status <= '1';
		-- xFE02 Stdin Data Register
    elsif addr_reg = X"FE02" then
		cs_stdin_data <= '1';
		-- xFE04 Stdout Status Register
	 elsif addr_reg = X"FE04" then
		cs_stdout_status <= '1';
		 -- xFE06 Stdout Data Register
	 elsif addr_reg = X"FE06" then
		cs_stdout_data <= '1';
		-- xFE0A Switches Data Register
	 elsif addr_reg = X"FE0A" then
		cs_switch_data <= '1';
		-- xFE0E Buttons Data Register
	 elsif addr_reg = X"FE0E" then
		cs_btn_data <= '1';
		-- xFE12 7SegDisplay Data Register
	 elsif addr_reg = X"FE12" then
		cs_7seg_data <= '1';
		 -- xFE16 Leds Data Register
	 elsif addr_reg = X"FE16" then
		cs_led_data <= '1';
    end if;
  end process;
 
  -- RAM template
  --data <= ram(to_integer(unsigned(addr_reg))) when re = '1' and cs_mem = '1' 
    --else (others => 'Z');
  
  --process (clk)
  --begin
    --if (clk'event and clk = '1') then
     --if (we='1') and cs_mem = '1' then
       --ram(to_integer(unsigned(addr))) <= data;
     --end if;
    -- addr_reg <= addr;
   --end if;
 -- end process;
  
  	-- cs_mem is In/Out
  data <= ram(to_integer(unsigned(addr_reg))) when re = '1' and cs_stdin_status = '1' 
    else (others => 'Z');
	 
  process (clk)
  begin
    if (clk'event and clk = '1') then
      if (we='1') and cs_stdin_status = '1' then
        ram(to_integer(unsigned(addr))) <= data;
      end if;
      addr_reg <= addr;
    end if;
  end process;
  
	-- cs_stdin_status is out
  data <= rx_empty & "000" & X"000" when re = '1' and cs_stdin_status = '1' 
    else (others => 'Z');
  
	-- cs_stdin_data  is out
  data <= uart_r_data when re = '1' and cs_stdin_data = '1' 
    else (others => 'Z');
  
	-- cs_stdout_status  is out
  data <= not tx_full & "000" & X"000" when re = '1' and cs_stdout_status = '1' 
    else (others => 'Z');
	 
	-- cs_stout_data is in 
  process (clk)
  begin
    if (clk'event and clk = '1') then
      if (we='1') and cs_stdout_data = '1' then
        uart_w_data <= data;
      end if;
    end if;
  end process;
  
  --cs_switch_data  is out
  data <= sw when re = '1' and cs_switch_data = '1' 
    else (others => 'Z');
	 
  --cs_btn_data  is out
  data <= btn_data when re = '1' and cs_btn_data = '1' 
    else (others => 'Z');
	 
	-- cs_sseg  is in 
  process (clk)
  begin
    if (clk'event and clk = '1') then
      if (we='1') and cs_sseg = '1' then
        sseg <= data;
      end if;
    end if;
  end process;
  
 	-- cs_leds  is in 
  process (clk)
  begin
    if (clk'event and clk = '1') then
      if (we='1') and cs_leds = '1' then
        leds_reg <= leds_next;
      end if;
    end if;
  end process;
  leds_next <= data;
  leds <= leds_reg;
        
end beh_arch;
