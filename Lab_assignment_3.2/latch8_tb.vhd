--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   12:15:13 02/23/2010
-- Design Name:   
-- Module Name:   C:/Programming/Tests/VHDL/02311/Lab3/tb_latch8.vhd
-- Project Name:  Lab3
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: latch8
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
 
ENTITY latch8_tb IS
END latch8_tb;
 
ARCHITECTURE behavior OF latch8_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT latch8
    PORT(
         clk : IN  std_logic;
         d : IN  std_logic_vector(7 downto 0);
         q : OUT  std_logic_vector(7 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal clk : std_logic := '0';
   signal d : std_logic_vector(7 downto 0) := (others => '0');

 	--Outputs
   signal q : std_logic_vector(7 downto 0);

   -- Clock period definitions
   constant clk_period : time := 20 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: latch8 PORT MAP (
          clk => clk,
          d => d,
          q => q
        );

   -- Clock process definitions
   clk_process :process
   begin
		clk <= '0';
		wait for clk_period/2;
		clk <= '1';
		wait for clk_period/2;
   end process;
 

	--**************************
   -- other stimulus
   --**************************
   process
   begin
		d <= X"10";
		wait until falling_edge(clk);
		wait until falling_edge(clk);

		d <= X"AA";
		wait until falling_edge(clk);
		wait until falling_edge(clk);

		d <= X"BB";
		wait for 2 ns;

		d <= X"CC";
		wait for 2 ns;

		d <= X"15";
		wait for 2 ns;

		d <= X"27";
		wait until falling_edge(clk);
		wait until rising_edge(clk);
		wait for 1 ns;

		d <= X"BB";
		wait for 2 ns;

		d <= X"CC";
		wait for 2 ns;

		d <= X"15";
		wait for 2 ns;

		d <= X"27";
		wait until falling_edge(clk);
		wait until falling_edge(clk);

		d <= X"05";
		wait until falling_edge(clk);
		wait until falling_edge(clk);
		

		assert false
			report "End of Simulation"
			severity failure;
			
   end process;

END;
