with Ada.Numerics.Discrete_Random;
with Ada.Text_IO; use Ada.Text_IO;
with Ada.Float_Text_IO; use Ada.Float_Text_IO;
with Utilities;

package body Rainbow_Table is

   package Random_Numbers is new Ada.Numerics.Discrete_Random (Unsigned_32);
   use Random_Numbers;

   G : Random_Numbers.Generator;

   function Create (Length : Unsigned_20 := 2**10;
                    Rows   : Unsigned_20 := 2**18) return Instance is
      Rainbow : Instance;

   begin

      Rainbow.Rows        := Rows;
      Rainbow.ChainLength := Length;

      for I in 0 .. Rainbow.Rows - 1 loop
         if (I mod (Rainbow.Rows / 10)) = 0 then
            Put (Item  => (Float (I) * 100.0) / Float (Rainbow.Rows),
                 Aft   => 2,
                 Fore  => 0,
                 Exp   => 0);
            New_Line;
         end if;

         Chain : declare
            Start_Value : constant Unsigned_32 := Random (G);
            Feedback    : Unsigned_32 := Start_Value;

            procedure Update (Key     : in     Unsigned_32;
                              Element : in out Value_Storage.Set);

            procedure Update (Key     : in     Unsigned_32;
                              Element : in out Value_Storage.Set) is
               pragma Unreferenced (Key);
            begin
               Element.Insert (New_Item => Start_Value);
            end Update;

         begin
            for J in 0 .. Rainbow.ChainLength - 1 loop
               Feedback :=
                 Unsigned_32
                   (Utilities.Reduction_Function
                        (Cipher => Unsigned_20
                             (Utilities.MD5_Redux
                                  (Item => Feedback) and 16#FFFFF#),
                         I      => J,
                         Size   => Unsigned_20'Last));

               if not Rainbow.Table.Contains
                 (Key => Feedback) then
--                  Put_Line ("Inserting into new chain:" & Feedback'Img);
--                    declare
--                       New_Set  : Value_Storage.Set;
--                       C        : Table_Storage.Cursor;
--                       Inserted : Boolean := False;
--                    begin
--                       Rainbow.Table.Insert (Key      => Feedback,
--                                             New_Item => New_Set,
--                                             Position => C,
--                                             Inserted => Inserted);
--                       if Inserted then
--                          Rainbow.Table.Update_Element
--                            (Position => C,
--                             Process  => Update'Access);
--                       end if;
--
--                    end;
null;
--                 else
--  --                  Put_Line ("Inserting into existing chain:" & Feedback'Img);
--
--                    if not
--                      Rainbow.Table.Element
--                        (Key => Feedback).Contains
--                      (Item => Start_Value)
--                    then
--  --                    Put_Line ("Inserting new value in list");
--                       Rainbow.Table.Update_Element
--                         (Position => Rainbow.Table.Find
--                            (Key => Feedback),
--                          Process  => Update'Access);
--  --                      else
--  --                         Put_Line ("Skipping already processed element.");
--                    end if;
               end if;
               Reset (G);
            end loop;
         end Chain;
      end loop;

      return Rainbow;

   end Create;

   function Equivalent_Elements (Left, Right : in Unsigned_20)
                                 return Boolean
   is
   begin
      return Left = Right;
   end Equivalent_Elements;

   function Equivalent_Elements (Left, Right : in Unsigned_32)
                                 return Boolean
   is
   begin
      return Left = Right;
   end Equivalent_Elements;

   function Equivalent_Keys (Left, Right : in Unsigned_20)
                             return Boolean
   is
   begin
      return Left = Right;
   end Equivalent_Keys;

   function Equivalent_Keys (Left, Right : in Unsigned_32)
                             return Boolean
   is
   begin
      return Left = Right;
   end Equivalent_Keys;

   function Hash (Item : in Unsigned_20) return Hash_Type is
   begin
      return Hash_Type (Item);
   end Hash;

   function Hash (Item : in Unsigned_32) return Hash_Type is
   begin
      return Hash_Type (Item);
   end Hash;

end Rainbow_Table;
