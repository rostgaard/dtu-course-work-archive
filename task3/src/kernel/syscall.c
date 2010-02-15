/*! \file syscall.c

 This files holds the implementations of system calls.
 It is merged into the system_call_handler function in
 kernel.c through a pre-processor include.

 */

case SYSCALL_PRINTS:
{
 kprints((char*) (SYSCALL_ARGUMENTS.rdi));
 SYSCALL_ARGUMENTS.rax=ALL_OK;
 break;
}

case SYSCALL_PRINTHEX:
{
 kprinthex(SYSCALL_ARGUMENTS.rdi);
 SYSCALL_ARGUMENTS.rax=ALL_OK;
 break;
}

case SYSCALL_DEBUGGER:
{
 /* Enable the bochs iodevice and force a return to the debugger. */
 outw(0x8a00, 0x8a00);
 outw(0x8a00, 0x8ae0);

 SYSCALL_ARGUMENTS.rax=ALL_OK;
 break;
}

/* Add the implementation of more system calls here. */

