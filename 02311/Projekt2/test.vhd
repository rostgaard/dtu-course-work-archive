----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    14:50:00 04/23/2010 
-- Design Name: 
-- Module Name:    sodamachine - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional comments: 
--
----------------------------------------------------------------------------------
library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;
use ieee.std_logic_unsigned.all;

---- uncomment the following library declaration if instantiating
---- any xilinx primitives in this code.
--library unisim;
--use unisim.vcomponents.all;

entity test is port (
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
  cur_state          : out std_logic_vector(3 downto 0));
end test;

architecture behavioral of test is
  type StateType is (init, purchase, add1, add2, dispense,
                     return_all, return_change);
  signal state_reg, state_next : StateType := init;

  signal cost : integer := 7;

  signal TCC1,TCC2,TCC1_next,TCC2_next : integer;
  signal CC1,CC2,CC1_next,CC2_next : integer;

  signal nextamount, currentamount : integer := 0;

begin

  NextStateDecoding : process (state_reg,clk)
  begin
    --Init values
    state_next <= state_reg;
    amount_out <= nextamount;
    --Assignments
    case state_reg is
      when init => 
        state_next <= purchase;

        -- Purchase state 
      when purchase =>
        -- if the amount inserted is above the cost and we are unable to return
        -- change, abort the transaction - corresponding to req. 10
        if currentamount >= cost then
          if CC1 = 0 and TCC1 = 0 then
            state_next <= return_all;
          else
            state_next <= dispense;
          end if;
          
        -- We also respond to the return_coins button.
        elsif return_coins = '1' then
          state_next <= return_all;
        else 
          if kr1 = '1' and kr2 = '0' then 
            state_next <= add1;
          elsif kr2 = '1' and kr1 = '0' then 
            state_next <= add2;
          else
            state_next <= purchase;					
          end if;
        end if;
        
      when add1 =>
        if kr1 = '0' then
          state_next <= purchase;
        end if;
        
      when add2 =>
        if(kr1 = '1') and kr2 = '0' then 
          state_next <= add1;
        elsif kr2 = '1' and kr1 = '0' then 
          state_next <= add2;
        else
          state_next <= purchase;					
        end if;

      when dispense =>
        state_next <= return_change;
        
      when return_all =>
        if purchase_finished = '1' then
          state_next <= purchase;
        else
          state_next <= return_all;
        end if;
        
      when return_change =>
        if purchase_finished = '1' then
          state_next <= purchase;
        else
          state_next <= return_change;
        end if;

      when others => 
        state_next <= init;
    end case;
  end process NextStateDecoding;

  StateRegister: process (clk,reset)
  begin
    if reset = '1' then 
      state_reg <= init;
    elsif clk'event and clk = '1' then
      state_reg <= state_next;
    end if;
  end process;
  
  
  AmountRegister: process (clk)
  begin
    if clk'event and clk = '1' then
      currentamount <= nextamount;
    end if;
  end process;

  CoincounterRegisters: process (clk)
  begin
    if clk'event and clk = '1' then
      CC1 <= CC1_next;
      CC2 <= CC2_next;
      TCC1 <= TCC1_next;
      TCC2 <= TCC2_next;
    end if;
  end process;
  

  OutputDecoding : process (state_reg)
  begin
    case state_reg is
      when init =>
        CC1_next <= 2;
        CC2_next <= 2;
        power_on <= '0';
        nextamount <= 0;
        cur_state <= "0001";
        
      when add1 =>
        nextamount <= currentamount+1;
        cur_state <= "0010";
        
      when add2 =>
        nextamount <= currentamount+2;
        cur_state <= "0011";
        
      when purchase =>
        power_on <= '1';
        cur_state <= "0100";
        returned_all_coins <= '0' ;
        
        slot_closed <= '0';
 
        
        -- Check if change is available
        if CC1 = 0 and TCC1 = 0 then
          change_available <= '0';
        else
          change_available <= '1';
        end if;
        
        returned_change <= '0';
        item_released <= '0';
        
        
      when dispense =>
        cur_state <= "0101";
--        CC1_next <= CC1 + TCC1;
--        CC2_next <= CC2 + TCC2;
        Item_released <= '1';
        slot_closed <= '1';           
        
      when return_all =>
        --empty temporary coin counter
        TCC1_next <= 0;
        TCC2_next <= 0;
        nextamount <= 0;
        returned_all_coins <= '1';
        
        
        cur_state <= "0110";
        
      when return_change =>
        returned_change <= '1';
        cur_state <= "0111";
        -- Reset temporary coin counters
        TCC1_next <= 0;
        TCC2_next <= 0;
        
      when others => 
        cur_state <= "0000";
        
    end case;
  end process OutputDecoding;

  -- debug

  

end Behavioral;
