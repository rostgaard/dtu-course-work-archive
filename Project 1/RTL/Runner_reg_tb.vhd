--------------------------------------------------------------------------------
-- Engineer: 		Morten & Kim 
-- Create Date:    	12:13:25 03/15/2010
-- Module Name:    	Runner_reg_tb
-- Project Name:   	Project 1
-- Description: 
-- Dependencies: 
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
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

ENTITY Runner_reg_tb IS
END Runner_reg_tb;

ARCHITECTURE behavior OF Runner_reg_tb IS 
  
  -- Component Declaration for the Unit Under Test (UUT)
  
  COMPONENT Runner_Registry
    PORT(
      reset : IN  std_logic;
      clk   : IN  std_logic;
      n2    : IN  std_logic;
      n1    : IN  std_logic;
      n0    : IN  std_logic;
      s2    : OUT  std_logic;
      s1    : OUT  std_logic;
      s0    : OUT  std_logic
      );
  END COMPONENT;
  

  --Inputs
  signal reset : std_logic := '0';
  signal clk   : std_logic := '0';
  signal n2    : std_logic := '0';
  signal n1    : std_logic := '0';
  signal n0    : std_logic := '0';

  --Outputs
  signal s2 : std_logic;
  signal s1 : std_logic;
  signal s0 : std_logic;

  -- Clock period definitions
  constant clk_period : time := 20 ns; 
BEGIN
  
  -- Instantiate the Unit Under Test (UUT)
  uut: Runner_Registry PORT MAP (
    reset => reset,
    clk   => clk,
    n(2)  => n2,
    n(1)  => n1,
    n(0)  => n0,
    s(2)  => s2,
    s(1)  => s1,
    s(0)  => s0
    );

  -- Clock process definitions
  clk_process :process
  begin
    clk <= '0';
    wait for clk_period/2;
    clk <= '1';
    wait for clk_period/2;
  end process;
  
  stim_proc: process
  begin		

    n2 <= '0';
    n1 <= '0';
    n0 <= '0';
    wait until falling_edge(clk);
    
    n2 <= '0';
    n1 <= '0';
    n0 <= '1';
    wait until falling_edge(clk);
    
    n2 <= '0';
    n1 <= '1';
    n0 <= '0';
    wait until falling_edge(clk);

    assert false
      report "End of Simulation"
      severity failure;
  end process;

END;
