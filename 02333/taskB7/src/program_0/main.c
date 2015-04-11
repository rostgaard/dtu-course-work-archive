/*! \file
 *      \brief The first user program - 
 *
 */

#include <scwrapper.h>

void
main(int argc, char* argv[])
{
 printat(0, 0, "This is the top line");

 printat(1, 2, "This is the second line and third column");



 printat(MAX_ROWS-1, MAX_COLS-3, "Hej");
 while(1)
 {
  register long scan_code=getscancode();
  if (0x1c==scan_code)
  {
   printat(2, 3, "Enter pressed ");
  } else if (0x9c==scan_code)
  {
   printat(2, 3, "Enter released");
  }
 }
}



/*
#include <scwrapper.h>

void
main(int argc, char* argv[])
{
 printat(0, 0, "This is the top line");

 printat(1, 2, "This is the second line and third column");



 printat(MAX_ROWS-1, MAX_COLS-3, "Hej");
 while(1)
 {
  register long scan_code=getscancode();
  if (0x1c==scan_code)
  {
   printat(2, 3, "Enter pressed ");
  } else if (0x9c==scan_code)
  {
   printat(2, 3, "Enter released");
  }
 }
}

*/