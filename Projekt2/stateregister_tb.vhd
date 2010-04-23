--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   11:46:57 04/23/2010
-- Design Name:   
-- Module Name:   D:/Projekt2/Projekt2/Src/Projekt2ColaDis/stateregister_tb.vhd
-- Project Name:  Projekt2ColaDis
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: stateregister
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
 
ENTITY stateregister_tb IS
END stateregister_tb;
 
ARCHITECTURE behavior OF stateregister_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT stateregister
    PORT(
         reset : IN  std_logic;
         kr1 : IN  std_logic;
         kr2 : IN  std_logic;
         clk : IN  std_logic;
         amount : OUT  std_logic_vector(3 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal reset : std_logic := '0';
   signal kr1 : std_logic := '0';
   signal kr2 : std_logic := '0';
   signal clk : std_logic := '0';

 	--Outputs
   signal amount : std_logic_vector(3 downto 0);

   -- Clock period definitions
   constant clk_period : time := 1us;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: stateregister PORT MAP (
          reset => reset,
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
      reset <= '1';
		wait for clk_period*2;
		reset <= '0';
		-- init
      wait until falling_edge(clk);
	

		kr1 <= '1';
		wait until falling_edge(clk);

		--kr1 <= '0';
		kr2 <= '1';
		wait until falling_edge(clk);
		
    assert false
      report "End of Simulation"
      severity failure;
   end process;

END;
