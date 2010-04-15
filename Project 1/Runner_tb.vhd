--------------------------------------------------------------------------------
-- Engineer: 		Morten & Kim 
-- Create Date:    	12:13:25 03/15/2010
-- Module Name:    	Runner_tb
-- Project Name:   	Project 1
-- Description: 
-- Dependencies: 
-- Revision: 
-- Revision 0.01 - File Created
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

ENTITY Runner_tb IS
END Runner_tb;

ARCHITECTURE behavior OF Runner_tb IS 
  
  -- Component Declaration for the Unit Under Test (UUT)
  COMPONENT Runner
    PORT(
      clockwise: IN std_logic;
      clk : IN  std_logic;
      reset : IN  std_logic;
      d1 : OUT  std_logic;
      d0 : OUT  std_logic;
      top : OUT  std_logic
      );
  END COMPONENT;
  

  --Inputs
  signal clk : std_logic := '0';
  signal reset : std_logic := '1';
  signal clockwise : std_logic := '1';

  --Outputs
  signal d1 : std_logic;
  signal d0 : std_logic;
  signal top : std_logic;

  -- Clock period definitions
  constant clk_period : time := 10ns;
  
BEGIN
  -- Instantiate the Unit Under Test (UUT)
  uut: Runner PORT MAP (
    clockwise => clockwise,
    clk => clk,
    reset => reset,
    d1 => d1,
    d0 => d0,
    top => top
    );

  -- Clock process definitions
  clk_process :process
  begin
    clk <= '0';
    wait for clk_period/2;
    clk <= '1';
    wait for clk_period/2;
  end process;

  -- Stimulus process
  stim_proc: process
  begin
    -- tests reset and CW CCW functionality
    wait until falling_edge(clk);
    reset <= '0';
    wait for 12*clk_period;
    clockwise <= '0';
                 wait for 12*clk_period;

    
    assert false
      report "End of Simulation"
      severity failure;
  end process;


END;
