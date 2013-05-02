package Railval.Schedule_Validator is

   type Pairs is private with Type_Invariant =>  Check (Pairs);

   function Create (C1,C2 : in Character) return Pairs;

   type Schedule is array (Positive range <>, Positive range <>) of Character;
   procedure Validate (Item : in Schedule);

   type Valid_Square is private;

   procedure Image (Item : in Schedule);

   function Image (Item : in Valid_Square) return String ;

   function Check (Item : in Pairs) return Boolean;

   function Image (Item : Pairs) return String;


   function Create (Pair1, Pair2 : in Pairs) return Valid_Square;
   function Colliding (Pair1, Pair2 : in Pairs) return Boolean;
private


   type Pairs is
      record
         Left  : Character;
         Right : Character;
      end record;

   type Valid_Square is
      record
         Top    : Pairs;
         Bottom : Pairs;
      end record
        with Type_Invariant =>
           not (Valid_Square.Top.Left = Valid_Square.Bottom.Left or
                  Valid_Square.Top.Right = Valid_Square.Bottom.Right) and  -- On-top rule.
           not (Valid_Square.Top.Left = Valid_Square.Bottom.Right and
                Valid_Square.Top.Right = Valid_Square.Bottom.Left);   -- Cross rule.

end Railval.Schedule_Validator;
