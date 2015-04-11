WITH Ada.Text_IO;
WITH Ada.Integer_Text_IO;
WITH Ada.Long_Long_Integer_Text_IO;
WITH Ada.Float_Text_IO;
WITH Ada.Long_Float_Text_IO;

PROCEDURE NumericTypes IS
   --------------------------------------------------------------
   --| Displays various integer and float attributes
   --| Author: M. B. Feldman, The George Washington University
   --| Last Modified: August 1998
   --| Modified by Chris Reedy, WWU, November 2004
   --------------------------------------------------------------

   I1, I2 : Integer;
   J1, J2 : Long_Long_Integer;

BEGIN -- Attributes

   Ada.Text_IO.Put(Item => "Smallest Integer is ");
   Ada.Integer_Text_IO.Put(Item => Integer'First);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put(Item => "Largest Integer is ");
   Ada.Integer_Text_IO.Put(Item => Integer'Last);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put(Item => "Bits in an Integer ");
   Ada.Integer_Text_IO.Put(Item => Integer'Size, Width => 1);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.New_Line;

   Ada.Text_IO.Put(Item => "Smallest Long_Long_Integer is ");
   Ada.Long_Long_Integer_Text_IO.Put(Item => Long_Long_Integer'First);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put(Item => "Largest Long_Long_Integer is ");
   Ada.Long_Long_Integer_Text_IO.Put(Item => Long_Long_Integer'Last);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put(Item => "Bits in an Long_Long_Integer ");
   Ada.Long_Long_Integer_Text_IO.Put(Item => Long_Long_Integer'Size, Width => 1);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.New_Line;

   Ada.Text_IO.Put(Item => "Smallest float is ");
   Ada.Float_Text_IO.Put(Item => Float'First);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put(Item => "Largest float is ");
   Ada.Float_Text_IO.Put(Item => Float'Last);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put(Item => "Bits in a float ");
   Ada.Integer_Text_IO.Put(Item => Float'Size, Width => 1);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put(Item => "Bits in a mantissa ");
   Ada.Integer_Text_IO.Put(Item => Float'Mantissa, Width => 1);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put(Item => "Machine_Mantissa (See ARM) ");
   Ada.Integer_Text_IO.Put(Item => Float'Machine_Mantissa, Width => 1);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put(Item => "Model_Mantissa (See ARM) ");
   Ada.Integer_Text_IO.Put(Item => Float'Model_Mantissa, Width => 1);
   Ada.Text_IO.New_Line;

   I1 := 2 ** (Float'Mantissa - 1);
   WHILE I1 + 1 = Integer(Float(I1 + 1)) LOOP
      I1 := 2 * I1;
   END LOOP;

   -- I1 := 2 ** 24 -- = 16777216;
   I2 := Integer(Float(I1));
   Ada.Integer_Text_IO.Put(Item => I1, Width => 1);
   IF I1 = I2 THEN
      Ada.Text_IO.Put(Item => " = ");
   ELSE
      Ada.Text_IO.Put(Item => " /= ");
   END IF;
   Ada.Text_IO.Put(Item => "Integer(Float( ");
   Ada.Integer_Text_IO.Put(Item => I1, Width => 1);
   Ada.Text_IO.Put_Line(" ))");

   I1 := I1 + 1;
   I2 := Integer(Float(I1));
   Ada.Integer_Text_IO.Put(Item => I1, Width => 1);
   IF I1 = I2 THEN
      Ada.Text_IO.Put(Item => " = ");
   ELSE
      Ada.Text_IO.Put(Item => " /= ");
   END IF;
   Ada.Text_IO.Put(Item => "Integer(Float( ");
   Ada.Integer_Text_IO.Put(Item => I1, Width => 1);
   Ada.Text_IO.Put(" ))");
   IF I1 /= I2 THEN
      Ada.Text_IO.Put(" = ");
      Ada.Integer_Text_IO.Put(Item => I2, Width => 1);
   END IF;
   Ada.Text_IO.New_Line;
   Ada.Text_IO.New_Line;

   Ada.Text_IO.Put(Item => "Smallest Long_Float is ");
   Ada.Long_Float_Text_IO.Put(Item => Long_Float'First);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put(Item => "Largest Long_Float is ");
   Ada.Long_Float_Text_IO.Put(Item => Long_Float'Last);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put(Item => "Bits in a Long_Float ");
   Ada.Integer_Text_IO.Put(Item => Long_Float'Size, Width => 1);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put(Item => "Bits in a Long_Float mantissa ");
   Ada.Integer_Text_IO.Put(Item => Long_Float'Mantissa, Width => 1);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put(Item => "Long_Float Machine_Mantissa (See ARM) ");
   Ada.Integer_Text_IO.Put(Item => Long_Float'Machine_Mantissa, Width => 1);
   Ada.Text_IO.New_Line;
   Ada.Text_IO.Put(Item => "Long_Float Model_Mantissa (See ARM) ");
   Ada.Integer_Text_IO.Put(Item => Long_Float'Model_Mantissa, Width => 1);
   Ada.Text_IO.New_Line;

   J1 := 2 ** (Long_Float'Mantissa - 1);
   WHILE J1 + 1 = Long_Long_Integer(Long_Float(J1 + 1)) LOOP
      J1 := 2 * J1;
   END LOOP;

   -- I1 := 2 ** 24 -- = 16777216;
   J2 := Long_Long_Integer(Long_Float(J1));
   Ada.Long_Long_Integer_Text_IO.Put(Item => J1, Width => 1);
   IF J1 = J2 THEN
      Ada.Text_IO.Put(Item => " = ");
   ELSE
      Ada.Text_IO.Put(Item => " /= ");
   END IF;
   Ada.Text_IO.Put(Item => "Long_Long_Integer(Long_Float( ");
   Ada.Long_Long_Integer_Text_IO.Put(Item => J1, Width => 1);
   Ada.Text_IO.Put_Line(" ))");

   J1 := J1 + 1;
   J2 := Long_Long_Integer(Long_Float(J1));
   Ada.Long_Long_Integer_Text_IO.Put(Item => J1, Width => 1);
   IF J1 = J2 THEN
      Ada.Text_IO.Put(Item => " = ");
   ELSE
      Ada.Text_IO.Put(Item => " /= ");
   END IF;
   Ada.Text_IO.Put(Item => "Long_Long_Integer(Long_Float( ");
   Ada.Long_Long_Integer_Text_IO.Put(Item => J1, Width => 1);
   Ada.Text_IO.Put(" ))");
   IF J1 /= J2 THEN
      Ada.Text_IO.Put(" = ");
      Ada.Long_Long_Integer_Text_IO.Put(Item => J2, Width => 1);
   END IF;
   Ada.Text_IO.New_Line;

END NumericTypes;
