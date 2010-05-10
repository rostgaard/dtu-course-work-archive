/*! \file scwrapper.h
 *  This file contains C wrapper functions for system calls.
 *  The wrappers are used by user programs to perform system
 *  calls from C.
 */

#ifndef _SCWRAPPER_H_
#define _SCWRAPPER_H_

/* Include the constants that identifies system calls. */
#include "sysdefines.h"

/*! Wrapper for the system call that returns the version of the kernel. */
static inline unsigned long
version(void)
{
 unsigned long return_value;
 __asm volatile("syscall" :
                 "=a" (return_value) :
                 "a" (SYSCALL_VERSION) :
                 "cc", "%rcx", "%r11");
 return return_value;
}

/*! Wrapper for the system call that prints a string.
 * @param string Pointer to the string to be printed.
 * */
static inline unsigned long
prints(const char* const string)
{
 unsigned long return_value;
 __asm volatile("syscall" :
                 "=a" (return_value) :
                 "a" (SYSCALL_PRINTS), "D" (string) :
                 "cc", "%rcx", "%r11");
 return return_value;
}

/*! Wrapper for the system call that prints a hexadecimal value.
 * @param value Hexadecimal value to be printed.
 */
static inline unsigned long
printhex(const unsigned long value)
{
 unsigned long return_value;
 __asm volatile("syscall" :
                 "=a" (return_value) :
                 "a" (SYSCALL_PRINTHEX), "D" (value) :
                 "cc", "%rcx", "%r11");
 return return_value;
}

/*! Wrapper for the system call that invokes the bochs debugger */
static inline unsigned long
debugger(void)
{
 unsigned long return_value;
 __asm volatile("syscall" :
                 "=a" (return_value) :
                 "a" (SYSCALL_DEBUGGER) :
                 "cc", "%rcx", "%r11");
 return return_value;
}

/*! Wrapper for the system call that terminates threads and processes. */
static inline void
terminate(void)
{
 __asm volatile("syscall" :
                 :
                 "a" (SYSCALL_TERMINATE) :
                 "cc", "%rcx", "%r11");
}

/*! Wrapper for the system call that creates processes.
 * @param executable integer identifying the program which should be loaded 
 *  and run as a process.
 */
static inline unsigned long
createprocess(const int executable)
{
 unsigned long return_value;
 __asm volatile("syscall" :
                 "=a" (return_value) :
                 "a" (SYSCALL_CREATEPROCESS), "D" (executable) :
                 "cc", "%r11", "%rcx");
 return return_value;
}

#endif