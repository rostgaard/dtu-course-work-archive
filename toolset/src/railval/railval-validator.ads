with Railval.Tokenizer;
with Ada.Containers.Vectors;
with Ada.Strings.Unbounded;

package Railval.Validator is
   use Railval;
   use Tokenizer;
   use Ada.Containers;
   use Ada.Strings.Unbounded;

   Null_Token : constant String := "";

   Train_Collision : exception;

   package Row_Element_Container is new Vectors (Natural, Unbounded_String);

   function "=" (Left, Right : in Row_Element_Container.Vector) return Boolean;

   function Skip_Whitespace (Item : in String) return Natural;

   function Next_Word (Item : in String) return String;

   function Parse_Line (Item : in String) return Row_Element_Container.Vector;

   package Row_Container is new
     Vectors (Natural, Row_Element_Container.Vector);

   type Transitions is private with
      Type_Invariant => Check (Transitions);

   type Schedules is array
     (Natural range <>, Natural range <>) of Transitions;

   function Create (From : in Row_Container.Vector) return Schedules;

   function Create (Item : in String) return Transitions;

   procedure Validate (Schedule : in Schedules);

   function Check (Transition : in Transitions) return Boolean;

   function Image (Item : in Schedules) return String;

   function Image (Item : in Transitions) return String;
   Null_Schedule : constant Schedules;

private
   type Transitions is array (1 ..  2) of Identifications;
   Null_Schedule : constant Schedules (0 .. 0, 0 .. 0) :=
     (others =>
        (others => "00"));

   function Create_Empty (X, Y : in Positive) return Schedules;

end Railval.Validator;
