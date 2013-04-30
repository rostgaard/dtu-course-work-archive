with Ada.Text_IO; use Ada.Text_IO;
with Ada.Command_Line; use Ada.Command_Line;
with Ada.Strings.Fixed; use Ada.Strings.Fixed;
with Ada.Strings.Unbounded; use Ada.Strings.Unbounded;

with Ada.Exceptions;

procedure Parser is

   type Identifiers is new Unbounded_String;

   Null_Identifier : constant Identifiers := To_Unbounded_String ("");

   Null_Identification : constant Identifications :=
     Identifications (ASCII.NUL);
   End_Point_ID : constant Identifications := Identifications (ASCII.SHARP);

   function Image (Item : in Identifications) return String;

   function Image (Item : in Identifications) return String is
      Buf : constant String (1 .. 1) := (1 => Character (Item));
   begin
      return Buf;
   end Image;

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


   function Create (Identifier : in Identifiers) return Rails is
   begin
      return (Defined    => True,
              Identifier => Identifier,
              Link1      => Null_Identification,
              Link2      => Null_Identification);
   end Create;

   Undefined_Rail : constant Rails := (Defined => False);

   --  We initialize the railmap with unidentified
   Identification_Map : array (Identifications) of Rails :=
     (others => Undefined_Rail);

   File_Handle : File_Type;
   Buffer      : String (1 .. 128);
   Filled      : Natural := 0;

   procedure Usage is
   begin
      Put_Line (Command_Name & " input_file");
   end Usage;



   function Identifier_Of (Item : in String) return Keywords is
      Separator_Position :
        constant Natural :=
           Index (Source    => Item,
                  Pattern   => Separator_String)
           - Separator_String'Length;

      Keyword_String : String renames
        Item (Item'First .. Item'First+Separator_Position-1);
   begin
      return (Keywords'Value(Keyword_String));
   end Identifier_Of;


   procedure Define (Rail : in out Rails; As : in Identifications) is
   begin
      if Identification_Map(As).Defined then
         raise Constraint_Error with Image (As) & " already defined";

      end if;

      Identification_Map(As) := Rail;
   end Define;


   --  Add precondition and postcondition.
   procedure Link (ID1, ID2 : in out Identifications) is
      Rail1 : Rails renames Identification_Map (ID1);
      Rail2 : Rails renames Identification_Map (ID2);

   begin
      if Rail1.Link1 = Null_Identification then
         Rail1.Link1 := ID2;
      else
         Rail1.Link2 := ID2;
      end if;

      if Rail2.Link1 = Null_Identification then
         Rail2.Link1 := ID1;
      else
         Rail2.Link2 := ID1;
      end if;
   end Link;

  Test_Rail : Rails;

begin
   Define (Rail => Test_Rail,
           As   => '0');
   Define (Rail => Test_Rail,
           As   => '0');

   for I in Identifications'Range loop
      if Identification_Map(I).Defined then
         Put_Line ("Found a defined!");
      end if;
   end loop;

   if Argument_Count = 0 then
      Usage;
      return; -- Exit
   end if;

   Open (File => File_Handle,
         Mode => In_File,
         Name => Argument(1));

   while not End_Of_File (File_Handle) loop
      Get_Line (File => File_Handle,
                Item => Buffer,
                Last => Filled);
      Put_Line (Keyword_Of (Buffer (Buffer'First .. Buffer'First+Filled-1))'Img);

   end loop;

exception
   when E : Name_Error =>
      Put_Line (Ada.Exceptions.Exception_Message (E));

end Parser;
