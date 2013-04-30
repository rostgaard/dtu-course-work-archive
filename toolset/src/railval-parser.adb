with Railval.Trace;

package body Railval.Parser is
   use Railval;

   --  Defines a previously undefined track and allocates it.
   procedure Define (ID : in Identifications) with
     Precondition  => not Is_Defined (ID),
     Postcondition => Allocation_Map (ID) = New_Rail;

   -------------
   --  Check  --
   -------------

   function Check (Item : in Rails) return Boolean is
   begin
      return Item.Defined and Item.Kind = Invalid;
   end Check;

   -------------
   --  Check  --
   -------------

   function Check (Item : in Frozen_Rails) return Boolean is
      use Ada.Containers;
   begin
      return Item.Links.Length > 1;
   end Check;

   --------------
   --  Define  --
   --------------

   procedure Define (ID : in Identifications) is
      Context : constant String := Package_Name & ".Define";
   begin
      Trace.Debug (Context => Context,
                      Message =>
                        "Defining new non-station link " & Character (ID));

      Allocation_Map (ID) := New_Rail;
   end Define;

   -----------------------
   --  Define_Endpoint  --
   -----------------------

   procedure Define_Endpoint (Identification : in Identifications) is
      Context : constant String := Package_Name & ".Define_Endpoint";

      Rail    : Rails renames Allocation_Map (Identification);
   begin

      if not Rail.Defined then
         Define (Identification);
      end if;

      Allocation_Map (Identification).Links.Append
        (Tokenizer.End_Point_Identification);

      Trace.Debug (Context => Context,
                   Message => "Defining endpoint " &
                     Character (Identification));
   end Define_Endpoint;

   --------------------
   --  Dump_Network  --
   --------------------

   procedure Dump_Network is
      Context : constant String := Package_Name & ".Dump_Network";
   begin
      for I in Identifications'Range loop
         if Allocation_Map (I).Defined then
            Trace.Debug (Context => Context,
                         Message => Image (I) & " " &
                           Image (Allocation_Map (I)));
         end if;
      end loop;
   end Dump_Network;

   -------------
   --  Image  --
   -------------

   function Image (Item : Connection_Storage.Vector) return String is
      Buffer   : String (1 .. 16);
      Position : Natural := Buffer'First;
   begin
      for Element of Item loop
         Buffer (Position) := Character (Element);
         Buffer (Position + 1) := ' ';
         Position          := Position + 2;
      end loop;

      return Buffer (Buffer'First .. Position);
   end Image;

   -------------
   --  Image  --
   -------------

   function Image (Item : in Rails) return String is
      use Station_Names;
   begin
      case Item.Kind is
         when Invalid =>
            return Item.Kind'Img;
         when Link =>
            return Item.Kind'Img & " " & Image (Item.Links);
         when Station =>
            return Item.Kind'Img & " " &
              To_String (Item.Name) & " " & Image (Item.Links);
      end case;
   end Image;

   ------------------
   --  Is_Defined  --
   ------------------

   function Is_Defined
     (Identification : in Identifications) return Boolean
   is
   begin
      return Allocation_Map (Identification).Defined;
   end Is_Defined;

   ---------------
   --  Is_Link  --
   ---------------

   function Is_Link
     (Identification : in Identifications) return Boolean
   is
   begin
      return Allocation_Map (Identification).Kind = Link;
   end Is_Link;

   ------------
   --  Link  --
   ------------

   procedure Link (Identification1, Identification2 : in Identifications) is
      Context : constant String := Package_Name & ".Link";

      Rail1   : Rails renames Allocation_Map (Identification1);
      Rail2   : Rails renames Allocation_Map (Identification2);

   begin
      Trace.Debug (Context => Context,
                   Message =>
                     "Linking " & Character (Identification1) & " and " &
                     Character (Identification2));

      if not Rail1.Defined then
         Define (Identification1);
      end if;

      if not Rail2.Defined then
         Define (Identification2);
         Trace.Debug (Context => Context,
                      Message =>
                        "Defining " & Character (Identification2));
      end if;

      Allocation_Map (Identification1).Links.Append (Identification2);
      Allocation_Map (Identification2).Links.Append (Identification1);

   end Link;

   ----------------------
   --  Define_Station  --
   ----------------------

   procedure Define_Station (Name           : in String;
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
            Links          => Connection_Storage.Empty_Vector);
      end if;
   end Define_Station;

end Railval.Parser;
