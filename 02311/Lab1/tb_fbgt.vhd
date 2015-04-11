--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   11:58:05 02/19/2010
-- Design Name:   
-- Module Name:   H:/Public/Lab1/Xilinx/tb_fbgt.vhd
-- Project Name:  1_project
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: agtb4bit
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
 
ENTITY tb_fbgt IS
END tb_fbgt;
 
ARCHITECTURE behavior OF tb_fbgt IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT agtb4bit
    PORT(
         i0 : IN  std_logic;
         i1 : IN  std_logic;
         i2 : IN  std_logic;
         i3 : IN  std_logic;
         eq : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal i0 : std_logic := '0';
   signal i1 : std_logic := '0';
   signal i2 : std_logic := '0';
   signal i3 : std_logic := '0';

 	--Outputs
   signal eq : std_logic;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: agtb4bit PORT MAP (
          i0 => i0,
          i1 => i1,
          i2 => i2,
          i3 => i3,
          eq => eq
        );
 
   -- No clocks detected in port list. Replace <clock> below with 
   -- appropriate port name 
 
   constant <clock>_period := 1ns;
 
   <clock>_process :process
   begin
		<clock> <= '0';
		wait for <clock>_period/2;
		<clock> <= '1';
		wait for <clock>_period/2;
   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100ms.
      wait for 100ms;	

      wait for <clock>_period*10;

      -- insert stimulus here 

      wait;
   end process;

END;
