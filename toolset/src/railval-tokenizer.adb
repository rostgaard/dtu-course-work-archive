with Ada.Strings.Fixed; use Ada.Strings.Fixed;

with Railval.Trace;

package body Railval.Tokenizer is
   use Station_Names;
   use Railval;

   -------------
   --  Check  --
   -------------

   function Check (Item : in Tokens) return Boolean is
   begin
      case Item.Kind is
         when STAT =>
            return
              Item.Name /= Null_Bounded_String and
              Item.Identifier /= Null_Identification;
         when CONN =>
            return
              Item.Left  /= Null_Identification and
              Item.Right /= Null_Identification;
         when ENDP =>
            return
              Item.Closing /= Null_Identification;
         when Undefined =>
            return True;
      end case;
   end Check;

   function Closing (Object : in Tokens) return Identifications is
   begin
      return Object.Closing;
   end Closing;

   function Identifier (Object : in Tokens) return Identifications is
   begin
      return Object.Identifier;
   end Identifier;

   function Image (Item : Tokens) return String is
   begin
      case Item.Kind is
         when STAT =>
            return Item.Kind'Img & Separator_String &
              To_String (Item.Name) & Separator_String &
              Character (Item.Identifier);

         when CONN =>
            return Item.Kind'Img & Separator_String &
              Character (Item.Left) & Separator_String &
              Character (Item.Right);
         when ENDP =>
            return Item.Kind'Img & Separator_String &
              Character (Item.Closing);
         when Undefined =>
            return "Undefined";
      end case;
   end Image;

   function Image (Item : in Identifications) return String is
   begin
      return (1 => Character (Item));
   end Image;

   function Left (Object : in Tokens) return Identifications is
   begin
      return Object.Left;
   end Left;

   ------------------
   --  Parse_Line  --
   ------------------

   function Parse_Line (Item : in String) return Tokens is

      Context : constant String := Package_Name & ".Parse_Line";

      Separator_Position : constant Natural :=
        Index (Source    => Item,
               Pattern   => Separator_String) - Separator_String'Length;

      Keyword_String : String renames
        Item (Item'First .. Separator_Position);

      Remaining_String : String renames
        Item (Separator_Position + 2 .. Item'Last);

      Keyword : constant Keywords := Keywords'Value (Keyword_String);
   begin
      case Keyword is
         when STAT =>
            declare
               Station_Position : constant Natural :=
                 Index (Source    => Remaining_String,
                        Pattern   => Separator_String) -
                 Separator_String'Length;
               Station_Name : String renames
                 Remaining_String
                   (Remaining_String'First .. Station_Position);
            begin
               return
                 (Kind       => STAT,
                  Name       => To_Bounded_String (Station_Name),
                  Identifier =>
                    Identifications (Remaining_String (Station_Position + 2)));
            end;
         when CONN =>
            return (Kind  => CONN,
                    Left  => Identifications
                      (Remaining_String (Remaining_String'First)),
                    Right => Identifications
                      (Remaining_String (Remaining_String'First + 2)));
         when ENDP =>
            return (Kind  => ENDP,
                    Closing  => Identifications
                      (Remaining_String (Remaining_String'First)));
         when Undefined =>
            raise Constraint_Error;
      end case;

   exception
      when others =>
         Trace.Error (Context => Context,
                      Message => "Could not process " & Item);
         return Null_Token;
   end Parse_Line;

   function Right (Object : in Tokens) return Identifications is
   begin
      return Object.Right;
   end Right;

   function Station_Name (Object : in Tokens) return String is
   begin
      return To_String (Object.Name);
   end Station_Name;

end Railval.Tokenizer;
