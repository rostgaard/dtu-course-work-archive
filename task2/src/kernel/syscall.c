/*! \file syscall.c 

 This files holds the implementations of all system calls.

 */

#include "kernel.h"


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

case SYSCALL_VERSION:
{
  SYSCALL_ARGUMENTS.rax=KERNEL_VERSION;
  break;
}

case SYSCALL_TERMINATE:
{
    SYSCALL_ARGUMENTS.rax=ERROR;

    // The current process is the one being owned by the currently executing thread.
    int current_process = thread_table[current_thread].data.owner;
    int parent_process  = process_table[current_process].parent;

    // Setting the owner to -1 effectively frees up the thread slot
    thread_table[current_thread].data.owner=-1;

    // Check if the process is not the last one (pid 1)
    if(! (--process_table[current_process].threads) ) {
        cleanup_process(current_process);
    }
    
    /* The last thing we do is to insert the thread of the parent function (the one
       that originally started the process we just terminated) in order to resume 
       execution.
    */ 
    int i;
    for(i = 0; i < MAX_NUMBER_OF_THREADS; i++) {
        if(thread_table[i].data.owner == parent_process) {
            current_thread = i;
            SYSCALL_ARGUMENTS.rax=ALL_OK;
            break;
        };
    }

  
  break;
}

case SYSCALL_CREATEPROCESS:
{
  SYSCALL_ARGUMENTS.rax=ERROR;

  /* Loop over all processes in the process table, finding an empty slot. */
  int i;
  for(i=0; i<MAX_NUMBER_OF_PROCESSES; i++)
 {

     if(!process_table[i].threads) { // Great, we found one
	// Preparing the executable image for executing
        static struct prepare_process_return_value prepared_process;
        prepared_process = prepare_process(executable_table[SYSCALL_ARGUMENTS.rdi].elf_image,
                        i,
                        executable_table[SYSCALL_ARGUMENTS.rdi].memory_footprint_size);

	// We now have one more thread executing the process        
	process_table[i].threads++;
        process_table[i].parent  = thread_table[current_thread].data.owner;

        int new_thread = allocate_thread();
        thread_table[new_thread].data.owner = i;
        thread_table[new_thread].data.registers.integer_registers.rflags = 0;
        thread_table[new_thread].data.registers.integer_registers.rip = prepared_process.first_instruction_address;

        
        SYSCALL_ARGUMENTS.rax=ALL_OK;
        current_thread = new_thread;
        break;
     }
 }

  break;
}


/* Add the implementation of more system calls here. */

