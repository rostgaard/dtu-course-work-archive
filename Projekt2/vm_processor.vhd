-- This file corresponds to the vending machine processor
-- that you need to implement for project 2.

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
--use IEEE.STD_LOGIC_ARITH.ALL;
--use IEEE.STD_LOGIC_UNSIGNED.ALL;
use ieee.numeric_std.all;

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
  -- Enumerated type of our states
  type StateType is (s_reset, init, purchase, add1, add2, dispense,
                     return_all, return_change, merge_counters, wait_for_user);
  signal state_reg, state_next : StateType := s_reset;

  -- Input signals
  signal amount_eq_cost, amount_gt_cost, has_change : std_logic;
  
  -- Registers and Output signals
  signal CC1_reset,CC1_en : std_logic := '0';
  signal CC1_r_reg,CC1_r_next : std_logic_vector(3 downto 0);
  
  signal CC2_reset,CC2_en : std_logic := '0';
  signal CC2_r_reg,CC2_r_next : std_logic_vector(3 downto 0) := "0000";
  
  signal TCC1_reset,TCC1_en : std_logic := '0';
  signal TCC1_r_reg,TCC1_r_next : std_logic_vector(3 downto 0);
  
  signal TCC2_reset,TCC2_en : std_logic;
  signal TCC2_r_reg,TCC2_r_next : std_logic_vector(3 downto 0);
  
  signal CC2_sel : std_logic := '0';
  signal CC1_sel : std_logic_vector(1 downto 0) := "00";
  signal amount : std_logic_vector(3 downto 0);
  
  constant cost : std_logic_vector(3 downto 0) := "0111"; -- 7
  constant initial_cc1 : std_logic_vector(3 downto 0) := "0001"; -- 1
  constant initial_cc2 : std_logic_vector(3 downto 0) := "0010"; -- 2
  
  -- Simple D flip-flops for storing the value of output signals
  -- over several states
  signal slot_closed_bit, returned_all_coins_bit, 
         returned_change_bit, item_released_bit : std_logic := '0';

  signal slot_closed_reset,        slot_closed_en : std_logic := '0';
  signal returned_all_coins_reset, returned_all_coins_en : std_logic := '0';
  signal returned_change_reset,    returned_change_en : std_logic := '0';
  signal item_released_reset,      item_released_en : std_logic := '0';
  
begin
  NextStateDecoding : process (state_reg, clk, amount_gt_cost, amount_eq_cost, 
                               has_change, return_coins, kr1, kr2,
                               purchase_finished)
  begin
    --Initial values, or default values
    state_next <= state_reg;
    --Assignments
	 
    case state_reg is
      -- This state should only be reached at power on or hard reset
      when s_reset =>
        state_next <= init;

      -- Setting up the values pre-purchase
      when init => 
        state_next <= purchase;

      -- Purchase state
      when purchase =>
        -- if the amount inserted is above the cost and we are unable to return
        -- change, abort the transaction - corresponding to req. 10
        if amount_eq_cost = '1'
          or (amount_gt_cost = '1' and has_change = '1') then
          state_next <= merge_counters;          

        elsif (amount_gt_cost = '1' and has_change = '0')
          or return_coins = '1' then
          state_next <= return_all;

        elsif kr1 = '1' and kr2 = '0' then 
          state_next <= add1;

        elsif kr2 = '1' and kr1 = '0' then
          state_next <= add2;

        else
          state_next <= purchase;	
        end if;
      -- 1 kr has been inserted
      when add1 =>
        state_next <= purchase;	

      -- 2 kr has been inserted
      when add2 =>
        state_next <= purchase;					

      -- amount has reached cost
      when dispense =>
        if (amount_gt_cost = '1') then
          state_next <= return_change;
        else 
          state_next <= wait_for_user;
        end if;   
      when return_all =>
        state_next <= wait_for_user;

      -- someone wants their money back, or we do not have change
      when return_change =>
        state_next <= wait_for_user;
      when merge_counters =>
        state_next <= dispense;     

      -- wait for the user to remove the can
      when wait_for_user =>
        if purchase_finished = '1' then
          state_next <= init;
        else
          state_next <= wait_for_user;
        end if;

      -- Just to make sure
      when others => 
        state_next <= init;
    end case;
  end process NextStateDecoding;

  StateRegister: process (clk,reset)
  begin
    if reset = '1' then 
      state_reg <= s_reset;
    elsif clk'event and clk = '1' then
      state_reg <= state_next;
    end if;
  end process;

  -- This one corresponds to the FSM outputs
  OutputDecoding : process (state_reg)
  begin
    --Initial values
     TCC1_en <= '0'; TCC1_reset <= '0';
     TCC2_en <= '0'; TCC2_reset <= '0';
     CC1_en  <= '0'; CC1_reset <= '0';
     CC2_en  <= '0'; CC2_reset <= '0';
     CC1_sel <= "00";
     CC2_sel <= '0';

     slot_closed_en           <= '0';
     returned_all_coins_en    <= '0';
     returned_change_en       <= '0';
     item_released_en         <= '0';
     slot_closed_reset        <= '0';
     returned_all_coins_reset <= '0';
     returned_change_reset    <= '0';
     item_released_reset      <= '0';
	  
    case state_reg is
      when s_reset =>
        CC1_en <= '1';
        CC2_en <= '1';
        CC1_sel <= "00";
        CC2_sel <= '1'; 

      when init =>
        TCC1_en <= '1';
        TCC2_en <= '1';
        TCC1_reset <= '1';
        TCC2_reset <= '1';
        returned_all_coins_reset <= '1' ;
        returned_change_reset <= '1';
        slot_closed_reset <= '1';
        item_released_reset <= '1';
		  
      when add1 =>
        TCC1_en <= '1';
        
      when add2 =>
        TCC2_en <= '1';
        
      when purchase =>
      when dispense =>
        
        item_released_en <= '1';
        slot_closed_en <= '1';
        
      when return_all =>
        --empty temporary coin counter
        TCC1_reset <= '1';
        TCC2_reset <= '1';
        returned_all_coins_en <= '1';
        
      when return_change =>
        returned_change_en <= '1';
        -- return change
        CC1_en <= '1';
        CC1_sel <= "01";
        
      when merge_counters =>
        -- merge counters
        CC1_en <= '1';
        CC2_en <= '1';
        CC1_sel <= "10";
        CC2_sel <= '0';	  

      when wait_for_user =>
        null;

      when others => 
        null;
    end case;
  end process OutputDecoding;

  -- Coin counter for 1 kr's
  CC1_reg : process (clk,CC1_reset)
  begin
    if CC1_reset = '1' then
      CC1_r_reg <= (others => '0');
    elsif rising_edge(clk) and CC1_en = '1' then
      CC1_r_reg <= CC1_r_next;
    end if;
  end process;

  -- Coin counter for 2 kr's  
  CC2_reg : process (clk,CC2_reset)
  begin
    if CC2_reset = '1' then
      CC2_r_reg <= (others => '0');
    elsif rising_edge(clk) and CC2_en = '1' then
      CC2_r_reg <= CC2_r_next;
    end if;
  end process;

  -- Coin counter for 1 kr's inserted
  TCC1_reg : process (clk,TCC1_reset)
  begin
    if TCC1_reset = '1' then
      TCC1_r_reg <= (others => '0');
    elsif rising_edge(clk) and TCC1_en = '1' then
      TCC1_r_reg <= TCC1_r_next;
    end if;
  end process;

  -- Coin counter for 2 kr's inserted
  TCC2_reg : process (clk,TCC2_reset)
  begin
    if TCC2_reset = '1' then
      TCC2_r_reg <= (others => '0');
    elsif rising_edge(clk) and TCC2_en = '1' then
      TCC2_r_reg <= TCC2_r_next;
    end if;
  end process;

  -- D Flip-flops to hold values of various outputs
  slot_closed_reg : process (clk, slot_closed_reset)
  begin
    if slot_closed_reset = '1' then
      slot_closed_bit <= '0';
    elsif rising_edge(clk) and slot_closed_en = '1' then
      slot_closed_bit <= '1';
    end if;
  end process;
  
  returned_all_coins_reg : process (clk, returned_all_coins_reset)
  begin
    if returned_all_coins_reset = '1' then
      returned_all_coins_bit <= '0';
    elsif rising_edge(clk) and returned_all_coins_en = '1' then
      returned_all_coins_bit <= '1';
    end if;
  end process;  
  
  returned_change_reg : process (clk, returned_change_reset)
  begin
    if returned_change_reset = '1' then
      returned_change_bit <= '0';
    elsif rising_edge(clk) and returned_change_en = '1' then
      returned_change_bit <= '1';
    end if;
  end process;  

  item_released_reg : process (clk, item_released_reset)
  begin
    if item_released_reset = '1' then
      item_released_bit <= '0';
    elsif rising_edge(clk) and item_released_en = '1' then
      item_released_bit <= '1';
    end if;
  end process;  


  -- Single bit outputs not included in datapath diagram, although they
  -- probably should be
  slot_closed <= slot_closed_bit;
  returned_all_coins <= returned_all_coins_bit;
  returned_change <= returned_change_bit;
  item_released <= item_released_bit;

  -- Datapath outputs, some are multiplexed
  power_on <= not reset;  
  amount <= std_logic_vector((unsigned(TCC2_r_reg(2 downto 0) & '0'))+
                             unsigned(TCC1_r_reg));

  amount_gt_cost <= '1' when unsigned(amount) > unsigned(cost) else '0';
  amount_eq_cost <= '1' when unsigned(amount) = unsigned(cost) else '0';

  change_available <= '1' when unsigned(CC1_r_reg) > 0 else '0';
  
  has_change <= '1' when (unsigned(TCC1_r_reg) + unsigned(CC1_r_reg)) > 0
                else '0';

  TCC1_r_next <= std_logic_vector(unsigned(TCC1_r_reg)+1) when TCC1_en = '1'
                 else TCC1_r_reg;
  TCC2_r_next <= std_logic_vector(unsigned(TCC2_r_reg)+1) when TCC2_en = '1'
                 else TCC1_r_reg;

  CC2_r_next <= std_logic_vector(unsigned(TCC2_r_reg) + unsigned(CC2_r_reg))
                when CC2_sel = '0' else initial_cc2;
  
  datapath: process(CC1_sel,CC2_sel,TCC1_r_reg,CC1_r_reg)
  begin
    case CC1_sel is
      when "00" => CC1_r_next <= CC1_r_reg;
      when "01" => CC1_r_next <= std_logic_vector(unsigned(CC1_r_reg)-1);
      when "10" => CC1_r_next <= std_logic_vector(unsigned(TCC1_r_reg) +
                                                  unsigned(CC1_r_reg));
      when "11" => CC1_r_next <= initial_cc1;
      when others => CC1_r_next <= CC1_r_reg;
    end case;
  end process;

  -- debug, still part of datapath
  debugOutput : process (state_reg,debug,amount,TCC1_r_reg,TCC2_r_reg,
                         CC1_r_reg,CC2_r_reg)
  begin
    case debug is
      when "00" =>
        display <= X"000" & amount;
      when "01" =>
        display(15 downto 4) <= X"000";
        -- manual state encoding
        case state_reg is
          when s_reset =>
            display(3 downto 0) <= X"0";
          when init =>
            display(3 downto 0) <= X"1";
          when purchase =>
            display(3 downto 0) <= X"2";
          when add1 =>
            display(3 downto 0) <= X"3";
          when add2 =>
            display(3 downto 0) <= X"4";
          when dispense =>
            display(3 downto 0) <= X"5";
          when return_all =>
            display(3 downto 0) <= X"6";
          when return_change =>
            display(3 downto 0) <= X"7";
          when merge_counters =>
            display(3 downto 0) <= X"8";
          when wait_for_user =>
            display(3 downto 0) <= X"9";
          when others =>
            display(3 downto 0) <= X"F";
        end case;
      when "10" =>
        display <= X"0" &  CC2_r_reg & X"0" & CC1_r_reg;

        -- self-defined debug
      when others =>
        display( 7 downto  4) <= amount;
        display(11 downto  8) <= TCC1_r_reg;
        display(15 downto 12) <= TCC2_r_reg;
        case state_reg is

          when s_reset =>
            display(3 downto 0) <= "0000";
          when init =>
            display(3 downto 0) <= "0001";
          when purchase =>
            display(3 downto 0) <= "0010";
          when add1 =>
            display(3 downto 0) <= "0011";
          when add2 =>
            display(3 downto 0) <= "0100";
          when dispense =>
            display(3 downto 0) <= "0101";
          when return_all =>
            display(3 downto 0) <= "0110";
          when return_change =>
            display(3 downto 0) <= "0111";
          when merge_counters =>
            display(3 downto 0) <= "1000";
          when wait_for_user =>
            display(3 downto 0) <= "1001";
          when others =>
            display(3 downto 0) <= "1111";
        end case;
    end case;
  end process;
end Behavioral;
