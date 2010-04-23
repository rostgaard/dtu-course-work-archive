--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   15:09:36 04/23/2010
-- Design Name:   
-- Module Name:   C:/Documents and Settings/Administrator.PCV/Desktop/Digitalteknik/Projekt_1/project2/test_tb.vhd
-- Project Name:  project2
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: test
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
 
ENTITY test_tb IS
END test_tb;
 
ARCHITECTURE behavior OF test_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT test
    PORT(
         kr1 : IN  std_logic;
         kr2 : IN  std_logic;
         clk : IN  std_logic;
         amount : OUT  std_logic_vector(3 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal kr1 : std_logic := '0';
   signal kr2 : std_logic := '0';
   signal clk : std_logic := '0';

 	--Outputs
   signal amount : std_logic_vector(3 downto 0);

   -- Clock period definitions
   constant clk_period : time := 1us;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: test PORT MAP (
          kr1 => kr1,
          kr2 => kr2,
          clk => clk,
          amount => amount
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
      -- hold reset state for 100ms.

      wait for clk_period*2;
		kr1 <= '1';
		wait for clk_period*2;
		kr1 <= '0';
		wait for clk_period*2;

		kr2 <= '1';
		wait for clk_period*2;
		kr2 <= '0';
		wait for clk_period*2;



      -- insert stimulus here 
    assert false
      report "End of Simulation"
      severity failure;
   end process;

END;
