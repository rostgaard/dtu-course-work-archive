--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   13:17:45 04/23/2010
-- Design Name:   
-- Module Name:   C:/Documents and Settings/s084283/Desktop/project2/amountreg_tb.vhd
-- Project Name:  project2
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: amountregister
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
 
ENTITY amountreg_tb IS
END amountreg_tb;
 
ARCHITECTURE behavior OF amountreg_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT amountregister
    PORT(
         input  : IN  std_logic_vector(3 downto 0);
         total : OUT  std_logic_vector(3 downto 0);
         clk : IN  std_logic;
         clr : IN  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal input : std_logic_vector(3 downto 0) := (others => '0');
   signal clk : std_logic := '0';
   signal clr : std_logic := '0';

 	--Outputs
   signal total : std_logic_vector(3 downto 0);

   -- Clock period definitions
   constant clk_period : time := 1us;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: amountregister PORT MAP (
          input => input,
          total => total,
          clk => clk,
          clr => clr
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
      clr <= '1';
		wait for clk_period*2;
		clr <= '0';
		
		wait for clk_period*2;
			input <= "0001";
      wait for clk_period*2;
			input <= "0000";
		wait for clk_period*2;
			input <= "0011";
		wait for clk_period*2;

    assert false
      report "End of Simulation"
      severity failure;
   end process;

END;
