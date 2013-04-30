with Railval.Trace;

package body Railval.Parser is
   use Railval;

   function Check (Item : in Rails) return Boolean is
   begin
      return Item.Defined and Item.Kind = Invalid;
   end Check;

   procedure Define_Station (Name          : in String;
                             Identification : in Identifications) is
      use Station_Names;

      Context : constant String := Package_Name & ".Define_Station";

   begin
      if Allocation_Map (Identification).Defined then
         Trace.Debug (Context => Context,
                      Message => Character (Identification) & " Defined");
      else
         Trace.Debug (Context => Context,
                      Message => Character (Identification) & " Not defined");
         Allocation_Map (Identification) :=
           (Defined        => True,
            Kind           => Station,
            Name           => To_Bounded_String (Name),
            Link1          => Null_Identification,
            Link2          => Null_Identification);
      end if;
   end Define_Station;

end Railval.Parser;
