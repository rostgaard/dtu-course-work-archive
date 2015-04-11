with Ada.Strings.Bounded; use Ada.Strings.Bounded;

package Railval.Tokenizer is

   Package_Name : constant String := "Railval.Tokenizer";

   type Keywords is (Undefined, STAT, CONN, ENDP);
   --  The keywords we are expecting, plus a value for tagging undefined.

   Maximum_Station_Name_Length : constant Natural := 128;
   --  As we are using bounded-length strings for station names, we
   --  need to specify a maximum.

   type Identifications is new Character range ASCII.NUL .. 'z';
   --  Valid identifications which _should_ rather be defined as a character
   --  set, but this is "acceptable" for now.

   Separator_String : constant String := " ";
   --  Whitespace character.

   Null_Identification : constant Identifications;
   End_Point_Identification : constant Identifications;
   --  Specific predefined constant objects.

   package Station_Names is new Ada.Strings.Bounded.Generic_Bounded_Length
     (Max => Maximum_Station_Name_Length);
   --  Bounded-length station name declaration.

   type Tokens (Kind : Keywords) is tagged private with
      Type_Invariant => Check (Item => Tokens);
   --  "Fat" Token with additional payload information.

   function Check (Item : in Tokens) return Boolean;
   --  Type invariant check for valid Tokens.

   function Parse_Line (Item : in String) return Tokens;
   --  Parses an entire line into a Token.

   Null_Token : constant Tokens;
   --  Default return value for the Parse_Line procedure when parser fails.

   function Image (Item : in Tokens) return String;
   function Image (Item : in Identifications) return String;
   --  Image (stringification) functions.

   function Station_Name (Object : in Tokens) return String with
     Precondition => Object.Kind = STAT;
   --  Accessor method.

   function Identifier (Object : in Tokens) return Identifications with
     Precondition => Object.Kind = STAT;
   --  Accessor method.

   function Left (Object : in Tokens) return Identifications with
     Precondition => Object.Kind = CONN;
   --  Accessor method.

   function Right (Object : in Tokens) return Identifications with
     Precondition => Object.Kind = CONN;
   --  Accessor method.

   function Closing (Object : in Tokens) return Identifications with
     Precondition => Object.Kind = ENDP;
   --  Accessor method.

private

   Null_Identification : constant Identifications :=
     Identifications (ASCII.NUL);

   End_Point_Identification : constant Identifications :=
     Identifications (ASCII.SHARP);

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
