/*! \file
 * 	\brief The first user program - it creates a processe and then waits
 *             for it to finish. The entire process is repeated over and over.
 *
 */

#include <scwrapper.h>

void
main(int argc, char* argv[])
{
 while(1)
 {
  prints("Process 0: Trying to start process 1.\n");
  /* Try to create and run process 1. */
  if (0 != createprocess(1))
  {
   prints("createprocess failed.\n");
  }
  else
  {
   prints("Process 0: Process 1 terminated.\n");
  }
  debugger();
 }
}
