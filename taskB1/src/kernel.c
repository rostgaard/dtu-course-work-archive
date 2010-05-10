/*!
 * \file
 * \brief
 *  This is the main source code for the kernel. Here all important variables
 *  will be initialized.
 *
 *  \mainpage
 *  \section Overview
 *  In this task the goal is to get familiar with the skeleton code of the operating system.
 *  In addition you should write source code that handles a system call. The system call
 *  returns a number which identifies the version of the kernel.
 *
 *  \section Assembly
 *  In addition to the C code which is documented here, some low-level functionality is
 *  written in assembly code. The system boots up in 32-bit mode by executing the
 *  instructions in boot32.s. The 64-bit part of the boot sequence is contained in boot64.s
 *  and finally the assembly code which handles context switches is found in enter.s. This
 *  code, like most assembly, code is far from easy to understand. Take help from a teaching
 *  assistant.
 */
#include "kernel.h"

/* Note: Look in kernel.h for documentation of global variables and functions. */

/* Variables */

struct context
saved_registers;

/* Function definitions */

/*! Wrapper for a byte out instruction. */
inline static void
outb(const short port_number, const char output_value)
{
 __asm volatile("outb %%al,%%dx" : : "d" (port_number), "a" (output_value));
}

/*! Wrapper for a word out instruction. */
inline static void
outw(const short port_number, const short output_value)
{
 __asm volatile("outw %%ax,%%dx" : : "d" (port_number), "a" (output_value));
}

void
kprints(const char* string)
{
 /* Loop until we have found the null character. */
 while(1)
 {
  register const char curr = *string++;

  if (curr)
  {
   outb(0xe9, curr);
  }
  else
  {
   return;
  }
 }
}

void
kprinthex(const register long value)
{
 const static char hex_helper[16]="0123456789abcdef";
 register int      i;

 /* Print each character of the hexadecimal number. This is a very inefficient
    way of printing hexadecimal numbers. It is, however, very compact in terms
    of number of source code lines. */
 for(i=15; i>=0; i--)
 {
  outb(0xe9, hex_helper[(value>>(i*4))&15]);
 }
}

void
initialize(void)
{
 kprints("\n\n\nThe kernel has booted!\n\n\n");
}

extern void
system_call_handler(void)
{
 switch(SYSCALL_ARGUMENTS.rax)
 {
# include "syscall.c"
  default:
  {
   /* No system call defined. */
   SYSCALL_ARGUMENTS.rax=ERROR_ILLEGAL_SYSCALL;
  }
 }
}
