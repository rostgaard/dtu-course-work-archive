package Railval.Trace is

   type Log_Streams is (Error, Debug);

   Mute : array (Log_Streams) of Boolean := (Debug => True,
                                             Error => False);

   procedure Debug (Context : in String;
                    Message : in String);

   procedure Error (Context : in String;
                    Message : in String);
end Railval.Trace;
