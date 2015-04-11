----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    12:02:37 02/19/2010 
-- Design Name: 
-- Module Name:    thgh - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
USE ieee.numeric_std.ALL;
 
---- Uncomment the following library declaration if instantiating
---- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity thgh is
    Port ( a : in  STD_LOGIC_VECTOR (1 downto 0);
           b : in  STD_LOGIC_VECTOR (1 downto 0);
           f : out  STD_LOGIC);
end thgh;

architecture Behavioral of thgh is
     signal p1,p2,p3,p4,p5,p6:std_logic;
begin
--sum of products
f <= p1 or p2 or p3 or p4 or p5 or p6;
-- products

p1 <= not a(0) and a(1) and not b(0) and not b(1);
p2 <= a(0) and not a(1) and not b(0) and not b(1);
p3 <= a(0) and not a(1) and not b(0) and b(1);
p4 <= a(0) and a(1) and not b(0) and not b(1);
p5 <= a(0) and a(1) and not b(0) and b(1);
p6 <= a(0) and a(1) and b(0) and not b(1);

end Behavioral;

