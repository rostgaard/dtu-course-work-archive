--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   09:53:54 03/14/2010
-- Design Name:   
-- Module Name:   C:/Documents and Settings/Administrator.PCV/Desktop/Digitalteknik/Projekt1/Runner_tb.vhd
-- Project Name:  Projekt1
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: Runner
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
 
ENTITY Runner_tb IS
END Runner_tb;
 
ARCHITECTURE behavior OF Runner_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT Runner
    PORT(
	      clk : IN  std_logic;
         clockwise : IN  std_logic;
         s2 : IN  std_logic;
         s1 : IN  std_logic;
         s0 : IN  std_logic;
         n2 : OUT  std_logic;
         n1 : OUT  std_logic;
         n0 : OUT  std_logic;
         t : OUT  std_logic;
         d1 : OUT  std_logic;
         d0 : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal clockwise : std_logic := '0';
   signal s2 : std_logic := '0';
   signal s1 : std_logic := '0';
   signal s0 : std_logic := '0';

 	--Outputs
   signal n2 : std_logic;
   signal n1 : std_logic;
   signal n0 : std_logic;
   signal t : std_logic;
   signal d1 : std_logic;
   signal d0 : std_logic;

   -- Clock period definitions
   constant clockwise_period : time := 1us;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: Runner PORT MAP (
		    clk => clk,
          clockwise => clockwise,
          s2 => s2,
          s1 => s1,
          s0 => s0,
          n2 => n2,
          n1 => n1,
          n0 => n0,
          t => t,
          d1 => d1,
          d0 => d0
        );

   -- Clock process definitions
   clockwise_process :process
   begin
		clk <= '0';
		wait for clockwise_period/2;
		clk <= '1';
		wait for clockwise_period/2;
   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100ms.
      wait for 100ms;	

      wait for clockwise_period*10;

      -- insert stimulus here 

      wait;
   end process;

END;
