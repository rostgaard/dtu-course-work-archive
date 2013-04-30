with Ada.Strings.Fixed; use Ada.Strings.Fixed;
with Ada.Text_IO; use Ada.Text_IO;

with Railval.Trace;

package body Railval.Tokenizer is

   use Railval;

   -------------
   --  Check  --
   -------------

   function Check (Item : in Tokens) return Boolean is
      use Station_Names;
   begin
      case Item.Kind is
         when STAT =>
            return
              Item.Name /= Null_Bounded_String and
              Item.Identifer /= Null_Identification;
         when CONN =>
            return
              Item.Left  /= Null_Identification and
              Item.Right /= Null_Identification;
         when ENDP =>
            return
              Item.Closing /= Null_Identification;
      end case;
   end Check;

   procedure Parse_Line (Item : in String) is -- return Tokens is

      Context : constant String :=

      Separator_Position : constant Natural :=
        Index (Source    => Item,
               Pattern   => Separator_String) - Separator_String'Length;

      Keyword_String : String renames
        Item (Item'First .. Item'First + Separator_Position - 1);

      Remaining_String : String renames
        Item (Separator_Position + 2 .. Item'Last);

      Keyword : constant Keywords := Keywords'Value (Keyword_String);
   begin
      Put_Line (Keyword'Img);
      Put_Line (Remaining_String);
   exception
      when others =>
         Trace.Error (Context =>

   end Parse_Line;

end Railval.Tokenizer;
