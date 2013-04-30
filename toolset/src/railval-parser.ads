with Railval.Tokenizer;

package Railval.Parser is
   use Railval.Tokenizer;

   type Rail_Kinds is (Invalid, Link, Switchtrack, Station, End_Point);

   type Rails (Defined : Boolean := False;
               Kind    : Rail_Kinds := Invalid) is private with
   Type_Invariant => Check (Rails);

   Null_Rail : constant Rails;

   --     procedure Replace (Item     : in     Rails;
   --                        New_Item : in     Rails) with
   --       Precondition => Item.Kind /= Invalid;

   function Check (Item : in Rails) return Boolean;
   --  Defines a station.
   --  This can be done under the following premises;
   --   1. The station is not defined as a track.
   --   2. The station is already defined as a track.
   --  When the stations is already defined, we need to "upgrade" the track
   --  to a station.
   procedure Define_Station (Name           : in String;
                             Identification : in Identifications);

   procedure Link (Identification1, Identification2 : in Identifications);

   --   function Not_Defined (Item : in Identifications) return Boolean;

   --  Freezing rails involves upgrading the number of checks performed
   --  on them, nothing else. They are distinct types, and therefore
   --  no operations intended for rails can be performed on them.
   type Frozen_Rails is private with
   Type_Invariant => Check (Frozen_Rails);

   function Check (Item : in Frozen_Rails) return Boolean;
private

   type Rails (Defined : Boolean    := False;
               Kind    : Rail_Kinds := Invalid) is
      record
         case Defined is
            when False =>
               null;
            when True =>
               Link1      : Identifications := Null_Identification;
               Link2      : Identifications := Null_Identification;
               case Kind is
                  when Station =>
                     Name : Station_Names.Bounded_String;
                  when Switchtrack =>
                     Link3      : Identifications := Null_Identification;
                  when End_Point | Invalid | Link =>
                     null;
               end case;
         end case;
      end record;

   Null_Rail      : constant Rails := (Defined => False,
                                       Kind    => Invalid);

   type Frozen_Rails is new Rails;

   Allocation_Map : array (Identifications) of Rails;

end Railval.Parser;
