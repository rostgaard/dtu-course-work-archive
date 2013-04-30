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

   --  "Fat" Token with additional payload information.
   type Tokens (Kind : Keywords) is tagged private with
      Type_Invariant => Check (Item => Tokens);

   function Check (Item : in Tokens) return Boolean;

   function Parse_Line (Item : in String) return Tokens;

   Null_Token : constant Tokens;

   function Image (Item : in Tokens) return String;

   function Station_Name (Object : in Tokens) return String with
     Precondition => Object.Kind = STAT;

   function Identifier (Object : in Tokens) return Identifications with
     Precondition => Object.Kind = STAT;

   function Left (Object : in Tokens) return Identifications with
     Precondition => Object.Kind = CONN;

   function Right (Object : in Tokens) return Identifications with
     Precondition => Object.Kind = CONN;

   function Closing (Object : in Tokens) return Identifications with
     Precondition => Object.Kind = ENDP;

private

   Null_Identification : constant Identifications :=
     Identifications (ASCII.NUL);

   type Tokens (Kind : Keywords) is tagged
      record
         case Kind is
            when STAT =>
               Identifier : Identifications;
               Name       : Station_Names.Bounded_String;
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
