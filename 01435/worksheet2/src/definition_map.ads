with Unsigned_Types;

package Definition_Map is

   procedure Add (Item : in Unsigned_Types.Unsigned_20);

   function Coverage return Float;

   function Collisions return Natural;

end Definition_Map;
