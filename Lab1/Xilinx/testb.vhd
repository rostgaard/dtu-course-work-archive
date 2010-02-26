--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   12:12:06 02/19/2010
-- Design Name:   
-- Module Name:   H:/Public/Lab1/Xilinx/testb.vhd
-- Project Name:  1_project
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: thgh
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
USE ieee.numeric_std.ALL;
 
ENTITY testb IS
END testb;
 
ARCHITECTURE behavior OF testb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT thgh
    PORT(
         a : IN  std_logic_vector(1 downto 0);
         b : IN  std_logic_vector(1 downto 0);
         f : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal a : std_logic_vector(1 downto 0) := (others => '0');
   signal b : std_logic_vector(1 downto 0) := (others => '0');

 	--Outputs
   signal f : std_logic;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: thgh PORT MAP (
          a => a,
          b => b,
          f => f
        );
 
   -- No clocks detected in port list. Replace <clock> below with 
   -- appropriate port name 
 
--   constant <clock>_period := 1ns;
-- 
--   <clock>_process :process
--   begin
--		<clock> <= '0';
--		wait for <clock>_period/2;
--		<clock> <= '1';
--		wait for <clock>_period/2;
--   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100ms.
      wait for 100ms;	
			a <= "00";
			b <= "00";

      wait for 100ms;	
			a <= "00";
			b <= "01";
			
      wait for 100ms;	
			a <= "00";
			b <= "10";

      wait for 100ms;	
			a <= "00";
			b <= "11";

      wait for 100ms;	
			a <= "10";
			b <= "00";


      wait for 100ms;	
			a <= "01";
			b <= "00";

      wait for 100ms;	
			a <= "01";
			b <= "01";

      wait for 100ms;	
			a <= "01";
			b <= "10";

      wait for 100ms;	
			a <= "01";
			b <= "11";

      wait for 100ms;	
			a <= "10";
			b <= "00";
			

      wait;
   end process;

END;
