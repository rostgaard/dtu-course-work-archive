-- This is just a simple test bench that you can use as a starting point for tesing
--   your design for project 2. For making a proper test of your state machine you 
--   should add more stimuli to the test bench in the process called stim_proc


-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE ieee.std_logic_unsigned.all;
USE ieee.numeric_std.ALL;

ENTITY vm_processor_tb IS
END vm_processor_tb;

ARCHITECTURE behavior OF vm_processor_tb IS 
  
  -- Component Declaration for the Unit Under Test (UUT)
  
  COMPONENT vm_processor
    PORT(
      clk : IN  std_logic;
      reset : IN  std_logic;
      kr1 : IN  std_logic;
      kr2 : IN  std_logic;
      return_coins : IN  std_logic;
      purchase_finished : IN  std_logic;
      power_on : OUT  std_logic;
      change_available : OUT  std_logic;
      item_released : OUT  std_logic;
      returned_all_coins : OUT  std_logic;
      returned_change : OUT  std_logic;
      slot_closed : OUT  std_logic;
      debug : IN  std_logic_vector(1 downto 0);
      display : OUT  std_logic_vector(15 downto 0)
      );
  END COMPONENT;
  

  --Inputs
  --All the inputs are initialized to zero here:
  signal clk : std_logic := '0';
  signal reset : std_logic := '0';
  signal kr1 : std_logic := '0';
  signal kr2 : std_logic := '0';
  signal return_coins : std_logic := '0';
  signal purchase_finished : std_logic := '0';
  signal debug : std_logic_vector(1 downto 0) := (others => '0') := "00";

  --Outputs
  signal power_on : std_logic;
  signal change_available : std_logic;
  signal item_released : std_logic;
  signal returned_all_coins : std_logic;
  signal returned_change : std_logic;
  signal slot_closed : std_logic;
  signal display : std_logic_vector(15 downto 0);

  -- Clock period definitions
  constant clk_period : time := 10 ns;
  
BEGIN
  
  -- Instantiate the Unit Under Test (UUT)
  uut: vm_processor PORT MAP (
    clk => clk,
    reset => reset,
    kr1 => kr1,
    kr2 => kr2,
    return_coins => return_coins,
    purchase_finished => purchase_finished,
    power_on => power_on,
    change_available => change_available,
    item_released => item_released,
    returned_all_coins => returned_all_coins,
    returned_change => returned_change,
    slot_closed => slot_closed,
    debug => debug,
    display => display
    );

  -- Clock process definitions
  clk_process :process
  begin
    clk <= '0';
    wait for clk_period/2;
    clk <= '1';
    wait for clk_period/2;
  end process;
  
  -- For your state machine it might be necassary to jump through several states
  --  until you get to a state where you busy wait for an input. To accomodate this
  --  we should wait 5 clock cycles before we activate new inputs. Depending on how
  --  you have designed the state machine you might have to wait even longer.

  -- Stimulus process
  stim_proc: process
  begin		
    -- hold reset state for 5 clock periods.
    reset <= '1';
    wait for clk_period*5;	
    
    reset <= '0';
    wait for clk_period*5;

	--Test1 insert 7 kr with change avaliable - dispense

	    --Insert a 2 kr coin
    kr2 <='1' ;
    wait for clk_period;
    kr2 <= '0';         
    wait for clk_period*5;
	     --Insert a 2 kr coin
		  
    kr2 <='1' ;
    wait for clk_period;
    kr2 <= '0';         
    wait for clk_period*5;
	     --Insert a 2 kr coin
		  
    kr2 <='1' ;
    wait for clk_period;
    kr2 <= '0';         
    wait for clk_period*5;
	     --Insert a 1 kr coin
		  
    kr1 <='1' ;
    wait for clk_period;
    kr1 <= '0';
    wait for clk_period*5;
	 
	--Test2 insert 8 kr in 2kr coins with change avaliable - Dispense, 1kr change
	--Test3 insert 8 kr in 2kr coins with no change avaliable - Return all coins
	--Test4 insert 6 kr and use return all coins - Return all coins
	--Test5 insert 8 kr in order 1kr+1kr+2kr+2kr+2kr with no change avaliable - dispence, 1kr change

    assert false
      report "Simulation completed"
      severity failure;
  end process;

END;
