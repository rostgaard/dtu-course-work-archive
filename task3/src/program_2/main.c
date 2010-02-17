/*! \file
 *      \brief The third user program - never ending loop that either busy 
 *             waits in a system un-friendly way or waits in a very system
 *             friendly way.
 *
 */

#include <scwrapper.h>

void 
main(int argc, char* argv[])
{
 while(1)
 {
  /* When testing the preemptive scheduler, replace 0 in the c pre-processor
     if below with a 1.  */
#if 1
  volatile long curr_time=0;
  
  while(curr_time++ < 100000);
#else
  pause(10000);
#endif

  prints("Pang\n");
 }
}
