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
	clk                : in  std_logic;
	reset              : in  std_logic;
	kr1                : in  std_logic;
	kr2                : in  std_logic;
	return_coins       : in  std_logic;
	purchase_finished  : in  std_logic;
	debug              : in  std_logic_vector(1 downto 0);

   power_on           : out std_logic;
   change_available   : out std_logic;
   item_released      : out std_logic;
	returned_all_coins : out std_logic;
	returned_change    : out std_logic;
	slot_closed        : out std_logic;
	display            : out std_logic_vector(15 downto 0);
	
	amount_out         : out integer;
	cur_state          : out std_logic_vector(3 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal clk : std_logic := '0';
	signal reset : std_logic := '0';
	signal kr1 : std_logic := '0';
   signal kr2 : std_logic := '0';
   signal return_coins : std_logic := '0';
   signal purchase_finished : std_logic := '0';
	signal debug : std_logic_vector(1 downto 0);
   
 	--Outputs
   
   signal power_on : std_logic;
   signal change_available : std_logic;
   signal item_released : std_logic;
   signal returned_all_coins : std_logic;
   signal returned_change : std_logic;
   signal slot_closed : std_logic;
   signal display : std_logic_vector(15 downto 0);
	
	signal amount_out : integer;
	signal cur_state : std_logic_vector(3 downto 0);
	
	
	
	
	

   -- Clock period definitions
   constant clk_period : time := 1us;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: test PORT MAP (
	clk                => clk,
	reset              => reset,
	kr1                => kr1,
	kr2                => kr2,
	return_coins       => return_coins,
	purchase_finished  => purchase_finished,
	debug              => debug,

   power_on           => power_on,
   change_available   => change_available,
   item_released      => item_released,
	returned_all_coins => returned_all_coins,
	returned_change    => returned_change,
	slot_closed        => slot_closed,
	display            => display,
	
	amount_out         => amount_out,
	cur_state          => cur_state
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
		wait for clk_period*2;
		kr1 <= '0';

		wait for clk_period*2;
		kr1 <= '1';
		wait for clk_period*2;
		kr1 <= '0';
		wait for clk_period*2;

		kr2 <= '1';
		wait for clk_period*2;
		kr2 <= '0';
		wait for clk_period*2;

		kr2 <= '1';
		wait for clk_period*2;
		kr2 <= '0';
		wait for clk_period*2;

		return_coins <= '1';
		wait for clk_period;
		return_coins <= '0';
		wait for clk_period;

		kr2 <= '1';
		wait for clk_period*2;
		kr2 <= '0';
		wait for clk_period*2;
		
		purchase_finished <= '1';
		wait for clk_period;
		purchase_finished <= '0';
		wait for clk_period;

		kr2 <= '1';
		wait for clk_period;
		kr2 <= '0';
		wait for clk_period;
		
		kr2 <= '1';
		wait for clk_period;
		kr2 <= '0';
		wait for clk_period;

		kr2 <= '1';
		wait for clk_period;
		kr2 <= '0';
		wait for clk_period;

		kr2 <= '1';
		wait for clk_period;
		kr2 <= '0';
		wait for clk_period;



      -- insert stimulus here 
    assert false
      report "End of Simulation"
      severity failure;
   end process;

END;
