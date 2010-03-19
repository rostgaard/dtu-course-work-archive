--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   12:13:25 03/15/2010
-- Design Name:   
-- Module Name:   C:/Documents and Settings/Administrator.PCV/Desktop/Projekt_1/Runner_logic_tb.vhd
-- Project Name:  Projekt_1
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: Runner_logic
-- 
-- Dependencies:
-- 
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
 
ENTITY Runner_logic_tb IS
END Runner_logic_tb;
 
ARCHITECTURE behavior OF Runner_logic_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT Runner_logic
    PORT(
			clockwise : IN   std_logic;
         s2        : IN   std_logic;
         s1        : IN   std_logic;
         s0        : IN   std_logic;
         n2        : OUT  std_logic;
         n1        : OUT  std_logic;
         n0        : OUT  std_logic;
         top       : OUT  std_logic;
         d1        : OUT  std_logic;
         d0        : OUT  std_logic
			);
    END COMPONENT;
    

   --Inputs
   signal test_s2 : std_logic := '0';
   signal test_s1 : std_logic := '0';
   signal test_s0 : std_logic := '0';
	signal test_clockwise : std_logic := '0';
	
 	--Outputs
	signal test_n2 : std_logic;
   signal test_n1 : std_logic;
   signal test_n0 : std_logic;
   signal test_top : std_logic;
   signal test_d1 : std_logic;
   signal test_d0 : std_logic;
   signal test_clk : std_logic;

   -- Clock period definitions
   constant clk_period : time := 20 ns; 
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: Runner_logic PORT MAP (
			clockwise => test_clockwise,
          s2 => test_s2,
          s1 => test_s1,
          s0 => test_s0,
          n2 => test_n2,
          n1 => test_n1,
          n0 => test_n0,
          top  => test_top,
          d1 => test_d1,
          d0 => test_d0
        );
		  

   -- Clock process definitions
   clk_process :process
   begin
		test_clk <= '0';
		wait for clk_period/2;
		test_clk	<= '1';
		wait for clk_period/2;
   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
		test_s2 <= '0';
		test_s1 <= '0';
		test_s0 <= '0';
		wait until falling_edge(test_clk);

		test_s2 <= '0';
		test_s1 <= '0';
		test_s0 <= '1';
		wait until falling_edge(test_clk);
		
		test_s2 <= '0';
		test_s1 <= '1';
		test_s0 <= '0';
		wait until falling_edge(test_clk);
		
		test_s2 <= '0';
		test_s1 <= '1';
		test_s0 <= '1';		
		wait until falling_edge(test_clk);
		
		test_s2 <= '1';
		test_s1 <= '0';
		test_s0 <= '0';		
		wait until falling_edge(test_clk);
		
		test_s2 <= '1';
		test_s1 <= '0';
		test_s0 <= '1';		
		wait until falling_edge(test_clk);
		
		test_s2 <= '1';
		test_s1 <= '1';
		test_s0 <= '0';		
		wait until falling_edge(test_clk);

		test_s2 <= '1';
		test_s1 <= '1';
		test_s0 <= '1';
		wait until falling_edge(test_clk);
		
		test_clockwise <= '1';
		test_s2 <= '0';
		test_s1 <= '0';
		test_s0 <= '0';
		wait until falling_edge(test_clk);

		test_s2 <= '0';
		test_s1 <= '0';
		test_s0 <= '1';
		wait until falling_edge(test_clk);
		
		test_s2 <= '0';
		test_s1 <= '1';
		test_s0 <= '0';
		wait until falling_edge(test_clk);
		
		test_s2 <= '0';
		test_s1 <= '1';
		test_s0 <= '1';		
		wait until falling_edge(test_clk);
		
		test_s2 <= '1';
		test_s1 <= '0';
		test_s0 <= '0';		
		wait until falling_edge(test_clk);
		
		test_s2 <= '1';
		test_s1 <= '0';
		test_s0 <= '1';		
		wait until falling_edge(test_clk);
		
		test_s2 <= '1';
		test_s1 <= '1';
		test_s0 <= '0';		
		wait until falling_edge(test_clk);

		test_s2 <= '1';
		test_s1 <= '1';
		test_s0 <= '1';
		wait until falling_edge(test_clk);

		assert false
			report "End of Simulation"
			severity failure;
   end process;

END;
