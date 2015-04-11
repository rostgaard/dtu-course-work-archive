-- Listing 11.1
-- Single-port RAM with asynchronous read
-- Modified from XST 8.1i rams_04
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
entity xilinx_one_port_ram_async is
   generic(
      ADDR_WIDTH: integer:=8;
      DATA_WIDTH: integer:=1
   );
   port(
      clk: in std_logic;
      we: in std_logic;
      addr: in std_logic_vector(ADDR_WIDTH-1 downto 0);
      din: in std_logic_vector(DATA_WIDTH-1 downto 0);
      dout: out std_logic_vector(DATA_WIDTH-1 downto 0)
    );
end xilinx_one_port_ram_async;

architecture beh_arch of xilinx_one_port_ram_async is
   type ram_type is array (2**ADDR_WIDTH-1 downto 0)
        of std_logic_vector (DATA_WIDTH-1 downto 0);
   signal ram: ram_type;
begin
   process (clk)
   begin
      if (clk'event and clk = '1') then
         if (we='1') then
            ram(to_integer(unsigned(addr))) <= din;
            end if;
      end if;
   end process;
   dout <= ram(to_integer(unsigned(addr)));
end beh_arch;