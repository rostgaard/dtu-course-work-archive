with Ada.Text_IO;

procedure Railval.Validator.Test is
   Case1 : constant Schedules := (("aa", "bb"),
                                  ("cc", "dd"));

   Case2 : constant Schedules := (("aa", "bb"),
                                  ("cc", "bb"));
begin
   Ada.Text_IO.Put_Line (Railval.Validator.Image (Case1));
   Railval.Validator.Validate (Case1);
   Railval.Validator.Validate (Case2);

end Railval.Validator.Test;
