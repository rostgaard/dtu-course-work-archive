with Ada.Strings.Bounded; use Ada.Strings.Bounded;

package Railval.Tokenizer is

   Package_Name : constant String := "Railval.Tokenizer";

   type Keywords is (Undefined, STAT, CONN, ENDP);

   Maximum_Station_Name_Length : constant Natural := 128;

   type Identifications is new Character range ASCII.NUL .. 'z';

   Separator_String : constant String := " ";

   Null_Identification : constant Identifications;

   package Station_Names is new Ada.Strings.Bounded.Generic_Bounded_Length
     (Max => Maximum_Station_Name_Length);

   type Tokens (Kind : Keywords) is private; --  with
--      Type_Invariant => Check (Item => Tokens);

   function Check (Item : in Tokens) return Boolean;

   function Parse_Line (Item : in String) return Tokens;

   Null_Token : constant Tokens;

   function Image (Item : Tokens) return String;

private

   Null_Identification : constant Identifications :=
     Identifications (ASCII.NUL);

   type Tokens (Kind : Keywords) is
      record
         case Kind is
            when STAT =>
               Name       : Station_Names.Bounded_String;
               Identifier : Identifications;
            when CONN =>
               Left       : Identifications;
               Right      : Identifications;
            when ENDP =>
               Closing    : Identifications;
            when Undefined =>
               null;
         end case;
      end record;

   Null_Token : constant Tokens := (Kind => Undefined);

end Railval.Tokenizer;
