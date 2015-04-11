/*! \file
 * 	\brief The second user program. It is similar to the first.
 *
 */

#include <scwrapper.h>

void
main(int argc, char* argv[])
{
 prints("Process 1: Trying to start process 2.\n");
  /* Try to create and run process 2. */
 if (0 != createprocess(2))
 {
  prints("createprocess failed.\n");
 }
 prints("Process 1: Process 2 terminated.\n");
}
