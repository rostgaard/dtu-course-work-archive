/*! \file sysdefines.h
 *  This file defines constants used by system calls.
 *
 */ 
 
#ifndef _SYSDEFINES_H_
#define _SYSDEFINES_H_ 

/*! Return code when system call returns normally. */
#define ALL_OK                  (0)
/*! Return code when system call returns with an error. */
#define ERROR                   (-1)
/*! Return code when system call is unknown. */
#define ERROR_ILLEGAL_SYSCALL   (-2)

/*! System call that returns the version of the kernel. */
#define SYSCALL_VERSION         (0)
/*! System call that prints a string. */
#define SYSCALL_PRINTS          (1)
/*! System call that prints a hexadecimal value. */
#define SYSCALL_PRINTHEX        (2)
/*! System call that pauses execution by invoking the bochs debugger. */
#define SYSCALL_DEBUGGER        (3) 


/*! System call that terminates the currently running
 *  thread. It takes no parameters. Terminates the process
 *  when there are no threads left. */
#define SYSCALL_TERMINATE       (4)

/*! System call that creates a new process with one single
 *  thread. It takes an index into the executable table in
 *  rdi. The program used is the executable whose index is
 *  passed in rdi. */
#define SYSCALL_CREATEPROCESS   (5)

/*! System call that blocks the calling thread a number of clocks ticks. The
   number of clock ticks is passed in rdi. There are 200 clock
   ticks per second.*/
#define SYSCALL_PAUSE           (6)

/*! System call that returns the current system time. The system time is the 
    number of clock ticks since system start. There are 200 clock ticks per 
    second. */
#define SYSCALL_TIME            (7)

#endif