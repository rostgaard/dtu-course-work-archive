-- TestBench Template 

LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE ieee.std_logic_unsigned.all;
USE ieee.numeric_std.ALL;
 
ENTITY Runner_Registy_tb IS
END Runner_Registy_tb;

  ARCHITECTURE behavior OF Runner_Registy_tb IS 

  -- Component Declaration
          COMPONENT Runner_Registy
	port(
			reset, clk, n2,n1,n0 : in std_logic;
			s2,s1,s0 : out std_logic
	);
          END COMPONENT;
   --Inputs
   signal clk : std_logic := '0';
   signal reset : std_logic := '0';
   signal n2 : std_logic := '0';
	signal n1 : std_logic := '0';
	signal n0 : std_logic := '0';

	--Outputs
	signal s2 : std_logic := '0';
	signal s1 : std_logic := '0';
   signal s0 : std_logic := '0';

   -- Clock period definitions
   constant clk_period : time := 20 ns; 
  BEGIN

  -- Component Instantiation
   uut: Runner_Registy PORT MAP (
          clk => clk,
			 reset => reset,
			 n2 => n2,
			 n1 => n1,
			 n0 => n0,
			 s2 => s2,
			 s1 => s1,
			 s0 => s0
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
	   wait until falling_edge(clk);
		
		n2 <= '0';
		n1 <= '0';
		n0 <= '1';
		wait until falling_edge(clk);
	   wait until falling_edge(clk);
		
		n2 <= '0';
		n1 <= '1';
		n0 <= '0';
		wait until falling_edge(clk);
		wait until falling_edge(clk);		


		assert false
			report "End of Simulation"
			severity failure;
   end process;

END;
