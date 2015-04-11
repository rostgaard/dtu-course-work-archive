with Ada.Text_IO; use Ada.Text_IO;
with Ada.Float_Text_IO; use Ada.Float_Text_IO;
with Ada.Streams.Stream_IO;
with Ada.Streams; use Ada.Streams;
with Ada.Directories;  use Ada.Directories;
with Ada.Calendar;

package body File_IO_Utilities is
   use Ada.Calendar;

   function Load_File (Filename      : in String;
                       Show_Progress : Boolean := False) return String is

      Buffer : String (1 .. Natural (Size (Filename)));

      File         : Ada.Streams.Stream_IO.File_Type;
      Stream  : Ada.Streams.Stream_IO.Stream_Access;
      Bytes      : File_Size :=  0;

      Total      : File_Size := 0;
      Start : constant Ada.Calendar.Time := Ada.Calendar.Clock;

      procedure Progress;

      procedure Progress is
         Runtime : constant Duration := Ada.Calendar.Clock - Start;
         Tmp     : constant Natural :=
           Natural ((Float (Bytes) / Float (Runtime))/1024.0);
      begin
         Put (ASCII.ESC & "]" & ASCII.LF &
                ASCII.ESC & "[F" & ASCII.ESC & "[J");
         Put ("Processed" & Bytes'Img & " bytes of file " &
                Filename & " in ");

         Put (Item => Float (Runtime),
              Fore => 0,
              Aft  => 2,
              Exp  => 0);
         Put (" seconds (" & Tmp'Img & " kbytes/s).");
      end Progress;

   begin

      Ada.Streams.Stream_IO.Open
        (File => File,
         Name => Filename,
         Mode => Ada.Streams.Stream_IO.In_File);

      Total := Size (Filename);

      Stream := Ada.Streams.Stream_IO.Stream (File => File);

      while Bytes < Total loop
         Bytes := Bytes + 1;
         Buffer (Natural (Bytes)) := Character'Input (Stream);
      end loop;
      if Show_Progress then
         Progress;
      end if;

      Ada.Streams.Stream_IO.Close (File => File);

      return Buffer;
   end Load_File;

end File_IO_Utilities;
