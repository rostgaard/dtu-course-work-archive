with Railval.Tokenizer;

package Railval.Validator is
   use Railval;
   use Tokenizer;

   type Transitions is private with
      Type_Invariant => Check (Transitions);

   type Schedules is array
     (Positive range <>, Positive range <>) of Transitions;

   procedure Validate (Schedule : in Schedules);

   function Check (Transition : in Transitions) return Boolean;

   function Image (Item : in Schedules) return String;

   function Image (Item : in Transitions) return String;

private
   type Transitions is array (1 ..  2) of Identifications;
end Railval.Validator;
