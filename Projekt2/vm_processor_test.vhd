-- This is the top level for the implementation you need to create for project 2.
-- It corresponds to the drawing that you've got as part of the project description.
-- You don't really need to make changes in this file (unless you want to use a slow clock)

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

---- Uncomment the following library declaration if instantiating
---- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity vm_processor_test is
  Port ( 
    clk100 : in STD_LOGIC; -- This is the system clock from the XUP board (100 MHz)
    BTN : in  STD_LOGIC_VECTOR (5 downto 1);   
    LED : out  STD_LOGIC_VECTOR (6 downto 1);	
    LEDG : out  STD_LOGIC;
    sseg : out  STD_LOGIC_VECTOR (7 downto 0);
    an : out  STD_LOGIC_VECTOR (3 downto 0);
    debug : in  STD_LOGIC_VECTOR (1 downto 0);
    reset : in  STD_LOGIC);
end vm_processor_test;

architecture Behavioral of vm_processor_test is

  signal clk : std_logic; -- This is the clock used by the vm_processor
  signal display : std_logic_vector(15 downto 0);

begin
  
  clk <= BTN(5); -- At first we use manual clock for the vm_processor
  LEDG <= '1'; -- Needs to be set in order to activate the leds
  
  --Instance of the vm_processor
  processor: entity work.vm_processor
    port map(
      clk => clk, 
      kr1 => BTN(1),
      kr2 => BTN(2),
      return_coins => BTN(3),
      purchase_finished => BTN(4),
      
      power_on => LED(1),
      change_available => LED(2),
      item_released => LED(3),
      returned_all_coins => LED(4),
      returned_change => LED(5),
      slot_closed => LED(6),
      
      reset => reset,
      debug => debug,
      display => display);
  

  --Instance of the seven segment decoder
  sseg_decoder: entity work.disp_hex_mux
    port map(
      clk => clk100,
      reset => '0', --We don't really need to reset this component
      hex3 => display(15 downto 12), 
      hex2 => display(11 downto 8), 
      hex1 => display(7 downto 4), 
      hex0 => display(3 downto 0),
      dp_in => "1111",
      an => an,
      sseg => sseg);
  
end Behavioral;

