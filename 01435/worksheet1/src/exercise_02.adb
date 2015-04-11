with Ada.Text_IO; use Ada.Text_IO;
with Ada.Strings.Unbounded;

with Caesar_Cipher_Evaluation; use Caesar_Cipher_Evaluation;
with Caesar_Cipher; use Caesar_Cipher;

procedure Exercise_02 is
   function Possible_Decodings (Item : in String) return String;

   function Possible_Decodings (Item : in String) return String is
      use Ada.Strings.Unbounded;

      Buffer : Unbounded_String;
   begin
      for I in 1 .. 25 loop
         Append (Buffer, Decode (Item => Item,
                                 Key  => I));
         if I /= 25 then
            Append (Buffer, " ");
         end if;
      end loop;

      return To_String (Buffer);
   end Possible_Decodings;

   task type Processor (From, To : Character);

   task body Processor is
   begin
      for C1 in From .. To loop
         for C2 in Character'('A') .. Character'('Z') loop
            for C3 in Character'('A') .. Character'('Z') loop
               for C4 in Character'('A') .. Character'('Z') loop
                  if
                    Try_String
                      (Item          => Possible_Decodings (C1 & C2 & C3 & C4),
                       Num_Arguments => 25) > 1
                  then

                     Put_Line (C1 & C2 & C3 & C4 & " => " &
                                 Possible_Decodings (C1 & C2 & C3 & C4));
                  end if;
               end loop;
            end loop;
         end loop;
      end loop;
   end Processor;

begin

   delay 1.0;
   declare
      Task1 : Processor (From => 'A', To => 'Z');
   begin
      null;
   end;

   Put_Line ("Done!");

end Exercise_02;
