-- Listing 11.1
-- Single-port RAM with synchronous read
-- Modified from XST 8.1i rams_07
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
entity vga_test is
  generic(
    ADDR_WIDTH: integer:=12;
    DATA_WIDTH: integer:=8
    );
  port(
  
    led: out std_logic_vector(7 downto 0);
    clk,btn: in std_logic;
    LEDG: out std_logic;
	 reset: in std_logic;
    --we: in std_logic; -- Write and read enable
	 sw: in std_logic_vector(7 downto 0);
    hsync, vsync: out std_logic;
    rgb: out std_logic_vector(2 downto 0)
  );
end vga_test;

architecture beh_arch of vga_test is
  
  -- Display specific signals
   signal pixel_x, pixel_y: std_logic_vector(9 downto 0);
   signal video_on, pixel_tick: std_logic;
   signal rgb_reg, rgb_next: std_logic_vector(2 downto 0);
   signal scaled_clk: std_logic;
   signal vga_bus_data: std_logic_vector(15 downto 0);
   signal vga_addr: std_logic_vector(15 downto 0);

	signal we_display: std_logic;
	signal we: std_logic;
begin
  -- Display specific
  -- When using a 100Mhz clock
  clk_scaler: entity work.mod_m_counter(arch)
     generic map(M=>2, N=>2)
     port map(clk=>clk, reset=>reset,
              q=>open, max_tick=>scaled_clk);

   -- instantiate VGA sync circuit
   vga_sync_unit: entity work.vga_sync
      port map(clk=>scaled_clk, reset=>reset,
               hsync=>hsync, vsync=>vsync,
               video_on=>video_on, p_tick=>pixel_tick,
               pixel_x=>pixel_x, pixel_y=>pixel_y);
					
   -- instantiate full-screen text generator
   text_gen_unit: entity work.text_screen_gen
      port map(clk=>clk, reset=>reset, --btn=>btn, 
		addr=>vga_addr, we=>we_display,
		bus_data => vga_bus_data ,
               video_on=>video_on, pixel_x=>pixel_x,
               pixel_y=>pixel_y, text_rgb=>rgb_next);

   -- rgb buffer
   process (clk)
   begin
      if (clk'event and clk='1') then
         if (pixel_tick='1') then
            rgb_reg <= rgb_next;
         end if;
      end if;
   end process;
   rgb <= rgb_reg;
	
	we <= sw(7);
	
	
	we_display <= btn;

  -- Display chip select
  vga_bus_data <= "000000000" & sw(6 downto 0) when we = '1';
  vga_addr <= X"0008";
  
  led(7) <= btn;
  led(6 downto 0) <= vga_bus_data(6 downto 0) when we = '0' else "000" & X"0";

  LEDG <= '1';
end beh_arch;
