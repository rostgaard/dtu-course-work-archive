with Ada.Containers.Vectors;

with Railval.Tokenizer;

package Railval.Parser is
   use Railval.Tokenizer;

   Package_Name : constant String := "Railval.Parser";

   type Rail_Kinds is (Invalid, Link, Station);

   type Rails (Defined : Boolean := False;
               Kind    : Rail_Kinds := Invalid) is private with
      Type_Invariant => Check (Rails);

   Null_Rail : constant Rails;

   function Check (Item : in Rails) return Boolean;
   --  Defines a station.
   --  This can be done under the following premises;
   --   1. The station is not defined as a track.
   --   2. The station is already defined as a track.
   --  When the stations is already defined, we need to "upgrade" the track
   --  to a station.
   procedure Define_Station (Name           : in String;
                             Identification : in Identifications) with
     Precondition => not Is_Defined (Identification) or
                         Is_Link (Identification);

   function Is_Defined (Identification : in Identifications) return
     Boolean;

   function Is_Link (Identification : in Identifications) return
     Boolean;

   procedure Link (Identification1, Identification2 : in Identifications);

   procedure Define_Endpoint (Identification : in Identifications);

   --   function Not_Defined (Item : in Identifications) return Boolean;

   --  Freezing rails involves upgrading the number of checks performed
   --  on them, nothing else. They are distinct types, and thus
   --  no operations intended for rails can be performed on them.
   type Frozen_Rails is private with
   Type_Invariant => Check (Frozen_Rails);

   function Check (Item : in Frozen_Rails) return Boolean;

   procedure Dump_Network;

   function Image (Item : in Rails) return String;

private

   type Connection_Count is new Natural range 0 .. 3;

   package Connection_Storage is new
     Ada.Containers.Vectors (Index_Type   => Connection_Count,
                             Element_Type => Identifications);

   function Image (Item : Connection_Storage.Vector) return String;

   type Rails (Defined : Boolean    := False;
               Kind    : Rail_Kinds := Invalid) is
      record
         case Defined is
            when False =>
               null;
            when True =>
               Links : Connection_Storage.Vector;
               case Kind is
                  when Station =>
                     Name : Station_Names.Bounded_String;
                  when Invalid | Link =>
                     null;
               end case;
         end case;
      end record;

   Null_Rail      : constant Rails := (Defined => False,
                                       Kind    => Invalid);

   New_Rail       : constant Rails :=
     (Defined        => True,
      Kind           => Link,
      Links          => Connection_Storage.Empty_Vector);

   type Frozen_Rails is new Rails;

   Allocation_Map : array (Identifications) of Rails;

end Railval.Parser;
