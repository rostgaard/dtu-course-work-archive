with Railval.Trace;

package body Railval.Parser is
   use Railval;

   --  Defines a previously undefined track and allocates it.
   procedure Define (Object : in out Railway_Networks;
                     ID     : in Identifications) with
     Precondition  => not Object.Is_Defined (ID),
   Postcondition => Object.Map (ID) = New_Rail;

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

   function Check (Rail : in Frozen_Rails) return Boolean is
      use Ada.Containers;
   begin
      return Rail.Kind /= Invalid and Rail.Links.Length > 1;
   end Check;

   --------------
   --  Define  --
   --------------

   procedure Define (Object : in out Railway_Networks;
                     ID     : in     Identifications) is
      Context : constant String := Package_Name & ".Define";
   begin
      Trace.Debug (Context => Context,
                   Message =>
                     "Defining new non-station link " & Character (ID));

      Object.Map (ID) := New_Rail;
   end Define;

   -----------------------
   --  Define_Endpoint  --
   -----------------------

   procedure Define_Endpoint (Object         : in out Railway_Networks;
                              Identification : in Identifications) is
      Context : constant String := Package_Name & ".Define_Endpoint";

      Rail    : Rails renames Object.Map (Identification);
   begin

      if not Rail.Defined then
         Define (Object, Identification);
      end if;

      Object.Map (Identification).Links.Append
        (Tokenizer.End_Point_Identification);

      Trace.Debug (Context => Context,
                   Message => "Defining endpoint " &
                     Character (Identification));
   end Define_Endpoint;

   ----------------------
   --  Define_Station  --
   ----------------------

   procedure Define_Station (Object         : in out Railway_Networks;
                             Name           : in String;
                             Identification : in Identifications) is
      use Station_Names;

      Context : constant String := Package_Name & ".Define_Station";

   begin
      if Object.Map (Identification).Defined then
         Trace.Debug (Context => Context,
                      Message => Character (Identification) & " Defined");
      else
         Trace.Debug (Context => Context,
                      Message => Character (Identification) & " Not defined");
         Object.Map (Identification) :=
           (Defined        => True,
            Kind           => Station,
            Name           => To_Bounded_String (Name),
            Links          => Connection_Storage.Empty_Vector);
      end if;
   end Define_Station;

   --------------------
   --  Dump_Network  --
   --------------------

   procedure Dump_Network (Object : in out Railway_Networks) is
      Context : constant String := Package_Name & ".Dump_Network";
   begin
      for I in Identifications'Range loop
         if Object.Map (I).Defined then
            Trace.Debug (Context => Context,
                         Message => Image (I) & " " &
                           Image (Object.Map (I)));
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

      return Buffer (Buffer'First .. Position - 2);
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
     (Object         : in out Railway_Networks;
      Identification : in     Identifications) return Boolean
   is
   begin
      return Object.Map (Identification).Defined;
   end Is_Defined;

   ---------------
   --  Is_Link  --
   ---------------

   function Is_Link
     (Object         : in out Railway_Networks;
      Identification : in     Identifications) return Boolean
   is
   begin
      return Object.Map (Identification).Kind = Link;
   end Is_Link;

   ------------
   --  Link  --
   ------------

   procedure Link
     (Object          : in out Railway_Networks;
      Identification1 : in     Identifications;
      Identification2 : in     Identifications) is

      use Ada.Containers;

      Context : constant String := Package_Name & ".Link";

      Rail1   : Rails renames Object.Map (Identification1);
      Rail2   : Rails renames Object.Map (Identification2);

   begin
      if not Rail1.Defined then
         Define (Object, Identification1);
      end if;

      if not Rail2.Defined then
         Define (Object, Identification2);
         Trace.Debug (Context => Context,
                      Message =>
                        "Defining " & Character (Identification2));
      end if;

      Trace.Debug (Context => Context,
                   Message =>
                     "Linking " & Character (Identification1) & " and " &
                     Character (Identification2) & ".");

      Object.Map (Identification1).Links.Append (Identification2);
      Object.Map (Identification2).Links.Append (Identification1);

   end Link;

   ----------------------
   --  Load_From_File  --
   ----------------------

   function Load_From_File (File : File_Type) return Railway_Networks is
      Buffer      : String (1 .. 128);
      Filled      : Natural := 0;
      Railnet     : Parser.Railway_Networks;
   begin
      while not End_Of_File (File) loop
         Get_Line (File => File,
                   Item => Buffer,
                   Last => Filled);
         declare
            Token : constant Tokens :=
              Parse_Line (Buffer (Buffer'First .. Filled));
         begin
            case Token.Kind is
            when CONN =>
               Railnet.Link (Token.Left, Token.Right);
            when STAT =>
               Railnet.Define_Station (Name           => Token.Station_Name,
                                       Identification => Token.Identifier);
            when ENDP =>
               Railnet.Define_Endpoint (Identification => Token.Closing);
            when Undefined =>
               raise Constraint_Error with "File Syntax error detected.";
            end case;
         end;
      end loop;

      Railnet.Validate;

      return Railnet;
   end Load_From_File;

   ------------------
   --  Not_Linked  --
   ------------------

   function Not_Linked
     (Object : in out Railway_Networks;
      Identification1, Identification2 : in Identifications) return Boolean is
   begin
      if not Object.Is_Defined (Identification => Identification1) or
        not Object.Is_Defined (Identification => Identification2) then
         return True;
      end if;

      if Object.Map (Identification1).Links.Contains (Identification2) then
         return False;
      end if;

      if Object.Map (Identification2).Links.Contains (Identification1) then
         return False;
      end if;

      return False;

   end Not_Linked;

   ----------------
   --  Validate  --
   ----------------

   procedure Validate (Object : in out Railway_Networks) is
   begin
      for Item of Object.Map loop
         if Item.Defined then
            declare
               Freeze : Frozen_Rails := Frozen_Rails (Item);
               pragma Unreferenced (Freeze);
            begin
               null;
            end;
         end if;
      end loop;
   end Validate;

end Railval.Parser;
