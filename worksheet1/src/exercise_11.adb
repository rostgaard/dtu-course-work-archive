with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;

procedure Exercise_11 is

   type Byte is mod 2**4;

   procedure Print_Header;
   procedure Print_Line (Index     : in Natural;
                         P_Value   : in Byte;
                         XOR_Value : in Byte;
                         C_Value   : in Byte);

   Table : constant array (Byte) of Byte :=
     (0 => 13,
      1 => 4,
      2 => 3,
      3 => 12,
      4 => 1,
      5 => 0,
      6 => 8,
      7 => 10,
      8 => 14,
      9 => 6,
      10 => 9,
      11 => 15,
      12 => 11,
      13 => 2,
      14 => 5,
      15 => 7);

   Value : constant array (Natural range <>) of Byte
     := (3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
         3, 3, 3, 3, 3, 3, 3, 3);

   package Byte_IO is new Ada.Text_IO.Modular_IO (Num => Byte);

   Res     : Byte := 0;
   Xor_Val : Byte := 0;

   Padding       : constant String := "  ";
   Index_Label : constant String := "Index";
   P_Value_Label : constant String := "P_Value";
   XOR_Label : constant String := "XOR";
   C_Value_Label : constant String := "C_Value";

   procedure Print_Header is
   begin
      Put (Item  => Index_Label);
      Put (Item  => Padding);
      Put (Item  => P_Value_Label);
      Put (Item  => Padding);
      Put (Item  => XOR_Label);
      Put (Item  => Padding);
      Put (Item  => C_Value_Label);
      New_Line;
   end Print_Header;

   procedure Print_Line (Index     : in Natural;
                         P_Value   : in Byte;
                         XOR_Value : in Byte;
                         C_Value   : in Byte) is
   begin
      Put (Item  => Index,
           Width => Index_Label'Last,
           Base  => 10);

      Byte_IO.Put (Item  => P_Value,
                   Width => Padding'Last + P_Value_Label'Last,
                   Base  => 10);

      Byte_IO.Put (Item  => XOR_Value,
                   Width => Padding'Last + XOR_Label'Last,
                   Base  => 10);

      Byte_IO.Put (Item  => C_Value,
                   Width => Padding'Last + C_Value_Label'Last,
                   Base  => 10);
      New_Line;
   end Print_Line;

begin

   for IV in Byte'Range loop

      New_Line;
      Put_Line ("IV:" & IV'Img);
      New_Line;
      Print_Header;

      --  Calculate initial value
      Xor_Val := IV xor Value (1);
      Res := Table (Xor_Val);

      Print_Line (Index     => 1,
                  P_Value   => Value (1),
                  XOR_Value => Xor_Val,
                  C_Value   => Res);

      for I in Value'First + 1 .. Value'Last loop
         Xor_Val := Res xor Value (I);
         Res := Table (Xor_Val);

         Print_Line (Index     => I,
                     P_Value   => Value (I),
                     XOR_Value => Xor_Val,
                     C_Value   => Res);
      end loop;
   end loop;
end Exercise_11;
