/*! \file syscall.c

 This files holds the implementations of system calls.
 It is merged into the system_call_handler function in
 kernel.c through a pre-processor include.

 */

#include "threadqueue.h"


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

case SYSCALL_TERMINATE:
{
    SYSCALL_ARGUMENTS.rax=ERROR;

    int current_process = thread_table[thread_queue_head(&ready_queue)].data.owner;
    int parent_process  = process_table[current_process].parent;

    // Setting the owner to -1 effectively frees up the thread slot
    thread_table[thread_queue_head(&ready_queue)].data.owner=-1;

    // Check if the process is the last one
    if(! (--process_table[current_process].threads) ) {
        //Finally we cleanup
        cleanup_process(current_process);
    }

    thread_queue_dequeue(&ready_queue);
    SYSCALL_ARGUMENTS.rax=ALL_OK;
    break;
/*
    int i;
    for(i = 0; i < MAX_NUMBER_OF_THREADS; i++) {
        if(thread_table[i].data.owner == parent_process) {
            current_thread = i;
            SYSCALL_ARGUMENTS.rax=ALL_OK;
            break;
        };
    }
*/


  
}

case SYSCALL_CREATEPROCESS:
{
  SYSCALL_ARGUMENTS.rax=ERROR;

  /* Loop over all processes in the process table, finding an empty slot. */
    int i;
    for(i=0; i<MAX_NUMBER_OF_PROCESSES; i++)
    {
        if(!process_table[i].threads) {
            static struct prepare_process_return_value prepared_process;
            prepared_process = prepare_process(executable_table[SYSCALL_ARGUMENTS.rdi].elf_image,
                            i,
                            executable_table[SYSCALL_ARGUMENTS.rdi].memory_footprint_size);

            process_table[i].threads++;
            process_table[i].parent  = thread_table[thread_queue_head(&ready_queue)].data.owner;

            int new_thread = allocate_thread();
            thread_table[new_thread].data.owner = i;
            thread_table[new_thread].data.registers.integer_registers.rflags = 0x200;
            thread_table[new_thread].data.registers.integer_registers.rip = prepared_process.first_instruction_address;


            SYSCALL_ARGUMENTS.rax=ALL_OK;
            thread_queue_enqueue(&ready_queue, new_thread);

            kprints("Thread nr: ");
            kprinthex(new_thread);
            kprints(" added\n");

            break;
         }
    }
    break;
}

