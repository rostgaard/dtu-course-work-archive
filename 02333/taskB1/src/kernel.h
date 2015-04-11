/*! \file kernel.h

 This file holds kernel wide macros and declarations.

 */

#ifndef _KERNEL_H_
#define _KERNEL_H_

/* Type definitions */
typedef char byte;
/* Macros */

#define SYSCALL_ARGUMENTS (((struct context) saved_registers).integer_registers)
/*!< Macro used in the system call switch to access the arguments to the 
     system call. */

#define KERNEL_VERSION        (0x0000000100000000)
/*!< Kernel version number. */

#define ALL_OK                (0)
#define ERROR                 (-1)
#define ERROR_ILLEGAL_SYSCALL (-2)

#define SYSCALL_VERSION       (0)
#define SYSCALL_PRINTS        (1)
#define SYSCALL_PRINTHEX      (2)
#define SYSCALL_DEBUGGER      (3)

/* Type declarations */

/*! Defines an execution context. */
struct context
{
 byte     fpu_context[512];
 /*!< Stores the fpu/mmx/sse registers */
 struct
 {
  long    rax;
  long    rbx;
  long    rcx;
  long    rdx;
  long    rdi;		/*!< destination index for string operations */
  long    rsi;		/*!< source index for string operations */
  long    rbp;		/*!< stack base pointer for current stack frame */
  long    rsp;		/*!< stack pointer */
  long    r8;
  long    r9;
  long    r10;
  long    r11;
  long    r12;
  long    r13;
  long    r14;
  long    r15;
  long    rflags;	/*!< status flags */
  long    rip;		/*!< instruction pointer */
 }        integer_registers;
 /*!< Stores all user visible integer registers. The segment registers are not
      stored as the user mode processes can only use a code and a data segment
      descriptor. We assume that the cs is set to the code and all the rest
      of the segment registers are set to the data segment all the time. */
};

/* Variable declarations */

extern struct context
saved_registers;
/*!< Stores the callee's registers during a system call. */

/* Function declarations */

/*! This function initializes the kernel after the assembly code portion has
    set the system and the CPU up. */
extern void
initialize(void);

/*! This function gets called from the system call handler and responds to the
    system calls. */
extern void
system_call_handler(void);

/*! Outputs a string to the bochs console. */
extern void
kprints(const char* const string
        /*!< points to a null terminated string */
        );

/*! Prints a long formatted as a hexadecimal number to the bochs console. */
extern void
kprinthex(const register long value
          /*!< the value to be written */);

#endif
