library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity disp_hex_mux is
  port (
    clk, reset             : in  std_logic;
    hex3, hex2, hex1, hex0 : in  std_logic_vector(3 downto 0);
    dp_in                  : in  std_logic_vector(3 downto 0);
    AN                     : out std_logic_vector(3 downto 0);
    SEG                   : out std_logic_vector(7 downto 0)
    );
end disp_hex_mux;

architecture arch of disp_hex_mux is
  constant N : integer := 18;
  signal q_reg, q_next : unsigned(N-1 downto 0);
  signal sel : std_logic_vector(1 downto 0);
  signal hex : std_logic_vector(3 downto 0);
  signal dp : std_logic;

begin
  -- purpose: register
  -- type   : sequential
  -- inputs : clk, reset
  -- outputs: 
  process (clk, reset)
  begin  -- process
    if reset = '1' then                 -- asynchronous reset (active high)
      q_reg <= (others => '0');
    elsif clk'event and clk = '1' then  -- rising clock edge
      q_reg <= q_next;
    end if;
  end process;
  -- next-state logic for the counter

  q_next <= q_reg + 1 ;

  -- 2 MSBs of counter to control 4-to-1 multiplexing
  sel <= std_logic_vector(q_reg(N-1 downto N-2));
  process (sel,hex0,hex1,hex2,hex3,dp_in)
  begin  -- process
    case sel is
      when "00" =>
        AN <= "1110";
        hex <= hex0;
        dp <= dp_in(0);
      when "01" =>
        AN <= "1101";
        hex <= hex1;
        dp <= dp_in(1);
      when "10" =>
        AN <= "1011";
        hex <= hex2;
        dp <= dp_in(2);
      when "11" =>
        AN <= "0111";
        hex <= hex3;
        dp <= dp_in(3);

      when others => null;
    end case;
  end process;

  with hex select
    SEG(6 downto 0) <=
    "0000001" when "0000",
    "1001111" when "0001",
    "0010010" when "0010",
    "0000110" when "0011",
    "1001100" when "0100",
    "0100100" when "0101",
    "0100000" when "0110",
    "0001111" when "0111",
    "0000000" when "1000", 
    "0000100" when "1001",
    "0001000" when "1010", --a
    "1100000" when "1011", --b
    "0110001" when "1100", --c
    "1000010" when "1101", --d
    "0110000" when "1110", --e    
    "0111000" when others; --f
  -- decimal point
  SEG(7) <= dp;
end arch;
