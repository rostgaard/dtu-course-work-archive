-- This file corresponds to the vending machine processor that you need to implement
--  for project 2.

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

---- Uncomment the following library declaration if instantiating
---- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity vm_processor is
  port( clk,reset,kr1,kr2,return_coins,purchase_finished : in std_logic;
        power_on,change_available,item_released : out std_logic;
        returned_all_coins,returned_change,slot_closed : out std_logic;
        debug : in std_logic_vector(1 downto 0);
        display : out std_logic_vector(15 downto 0));
end vm_processor;

architecture Behavioral of vm_processor is
  type StateType is (reset, init, purchase, add1, add2, dispense,
                     return_all, return_change);
  signal state_reg, state_next : StateType := init;

  -- Input signals
  signal amount_eq_cost, amount_gt_cost, has_change : std_logic;

  -- Output signals
  signal TTC1_sub : std_logic;
  
  -- Registers and 
  signal CC1_reset,CC1_en : std_logic;
  signal CC1_r_reg,CC1_r_next : unsigned;
  
  signal CC2_reset,CC2_en : std_logic;
  signal CC2_r_reg,CC2_r_next : unsigned;
  
  signal TCC1_reset,TCC1_en : std_logic;
  signal TCC1_r_reg,TCC1_r_next : unsigned;
  
  signal TCC2_reset,TCC2_en : std_logic;
  signal TCC2_r_reg,TCC2_r_next : unsigned;

  signal cost_reset,cost_en : std_logic;
  signal cost_r_reg,cost_r_next : unsigned;
  
  
begin

  NextStateDecoding : process (state_reg,clk)
  begin
    --Init values
    state_next <= state_reg;
    amount_out <= nextamount;
    --Assignments
    case state_reg is
      when reset =>
        state_next <= init;
        
      when init => 
        state_next <= purchase;

        -- Purchase state 
      when purchase =>
        -- if the amount inserted is above the cost and we are unable to return
        -- change, abort the transaction - corresponding to req. 10
        if amount_eq_cost = '1' or (amount_gt_cost = '1' and has_change = '1') then
          state_next <= dispense;          
        elsif (amount_gt_cost = '1' and has_change = '0') or return_coins = '1' then
          state_next <= return_all;
        elsif kr1 = '1' and kr2 = '0' then 
          state_next <= add1;
        elsif kr2 = '1' and kr1 = '0' then
          state_next <= add2;
        else
          state_next <= purchase;	
        end if;

      when add1 =>
        if kr1 = '1' and kr2 = '0' then 
          state_next <= add1;
        elsif kr2 = '1' and kr1 = '0' then 
          state_next <= add2;
        else
          state_next <= purchase;					
        end if;
        
      when add2 =>
        if kr1 = '1' and kr2 = '0' then 
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
  
  OutputDecoding : process (state_reg)
  begin
    case state_reg is
      when reset =>
        power_on <= '0';
        CC1_reset <= '1';
        CC2_reset <= '1';
        TCC1_reset <= '1';
        TCC2_reset <= '1';
      
      when init =>
        power_on <= '1';
        returned_all_coins <= '0' ;
        slot_closed <= '0';
        returned_change <= '0';
        item_released <= '0';
        
      when add1 =>
        TCC1_en <= '1';
        TCC2_en <= '0';
        
      when add2 =>
        TCC2_en <= '1';
        TCC1_en <= '0';
        
      when purchase =>
        TCC1_en <= '0';
        TCC2_en <= '0';

      when dispense =>
        
        Item_released <= '1';
        slot_closed <= '1';           
        
      when return_all =>
        --empty temporary coin counter
        TCC1_next <= 0;
        TCC2_next <= 0;
        nextamount <= 0;
        returned_all_coins <= '1';
        
      when return_change =>
        returned_change <= '1';
        -- Reset temporary coin counters
        TCC1_reset <= '1';
        TCC2_reset <= '1';
        
      when others => 
        null;
    end case;
  end process OutputDecoding;


  CC1_reg : process (clk,CC1_reset)
  begin
    if reset = '1' then
      CC1_r_reg <= 0;
    elsif rising_edge(clk) and CC1_en = '1' then
      CC1_r_reg <= CC1_r_next;
    end if;
  end CC1_reg;

  CC2_reg : process (clk,CC2_reset)
  begin
    if reset = '1' then
      CC2_r_reg <= 0;
    elsif rising_edge(clk) and CC2_en = '1' then
      CC2_r_reg <= CC2_r_next;
    end if;
  end CC2_reg;

  TCC1_reg : process (clk,TCC1_reset)
  begin
    if reset = '1' then
      TCC1_r_reg <= 0;
    elsif rising_edge(clk) and TCC1_en = '1' then
      TCC1_r_reg <= TCC1_r_next;
    end if;
  end TCC1_reg;

  TCC2_reg : process (clk,TCC2_reset)
  begin
    if reset = '1' then
      TCC2_r_reg <= 0;
    elsif rising_edge(clk) and TCC2_en = '1' then
      TCC2_r_reg <= TCC2_r_next;
    end if;
  end TCC2_reg;


  cost_reg : process (clk,cost_reset)
  begin
    if reset = '1' then
      cost_r_reg <= 7;
    elsif rising_edge(clk) and cost_en = '1' then
      cost_r_reg <= cost_r_next;
    end if;
  end TCC2_reg;
  
  
  -- debug
  debugOutput : process (state_reg)
  begin
    case state_reg is
      when reset =>
        display(1 downto 0) <= "0000";
      when init =>
        display(1 downto 0) <= "0001";
      when purchase =>
        display(1 downto 0) <= "0010";
      when add1 =>
        display(1 downto 0) <= "0011";
      when add2 =>
        display(1 downto 0) <= "0100";
      when dispense =>
        display(1 downto 0) <= "0101";
      when return_all =>
        display(1 downto 0) <= "0110";
      when return_change =>
        display(1 downto 0) <= "0111";
      when others =>
        display(1 downto 0) <= "1111";
    end case;

        
      when others => null;
    end case;
end Behavioral;
