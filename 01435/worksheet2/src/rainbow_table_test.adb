with Rainbow_Table; use Rainbow_Table;
with Unsigned_Types;

procedure Rainbow_Table_Test is
   use Unsigned_Types;
   Table : Rainbow_Table.Instance := Rainbow_Table.Create (Length => 2**15,
                                                           Rows   => 2**10);

begin
   null;
end Rainbow_Table_Test;
