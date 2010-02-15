/*! \file
 *      \brief The first user program - it creates two processes and then 
 *             goes into an never ending loop in which Ping is printed every
 *             100th clock tick.
 *
 */


#include <scwrapper.h>

void 
main(int argc, char* argv[])
{
 if (0 != createprocess(1))
 {
  prints("createprocess of program 1 failed.\n");
  return;
 }

 if (0 != createprocess(2))
 {
  prints("createprocess of program 2 failed.\n");
  return;
 }

 while(1)
 {
  pause(100);
  prints("Ping\n");
 }
}
