package Railval.Parser is


   type Rail_Kinds is (Undefined, Link, Switchtrack, Station, End_Point);

   type Rails (Kind : Rail_Kinds := Undefined) is
      record
         case Kind is
            when Undefined =>
               null;
            when Link =>
               Identifier : Identifiers     := Null_Identifier;
               Link1      : Identifications := Null_Identification;
               Link2      : Identifications := Null_Identification;
            when others =>
               null;
         end case;
      end record
   with Type_Invariant => not Link2 = End_Point and
     (Link1 = End_Point and Link2 /= Null_Identification);

end Railval.Parser;
