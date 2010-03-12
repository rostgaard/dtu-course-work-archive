/*! \file syscall.c

 This files holds the implementations of system calls.
 It is merged into the system_call_handler function in
 kernel.c through a pre-processor include.

 */

#include "threadqueue.h"


    case SYSCALL_PRINTS:
{
    kprints((char*) (SYSCALL_ARGUMENTS.rdi));
    SYSCALL_ARGUMENTS.rax = ALL_OK;
    break;
}

    case SYSCALL_PRINTHEX:
{
    kprinthex(SYSCALL_ARGUMENTS.rdi);
    SYSCALL_ARGUMENTS.rax = ALL_OK;
    break;
}

    case SYSCALL_DEBUGGER:
{
    /* Enable the bochs iodevice and force a return to the debugger. */
    outw(0x8a00, 0x8a00);
    outw(0x8a00, 0x8ae0);

    SYSCALL_ARGUMENTS.rax = ALL_OK;
    break;
}

/* Add the implementation of more system calls here. */

    case SYSCALL_TERMINATE:
{
    SYSCALL_ARGUMENTS.rax = ERROR;
    schedule = 1;

    int current_process = thread_table[cpu_private_data.thread_index].data.owner;
    //int parent_process = process_table[current_process].parent;

    // Setting the owner to -1 effectively frees up the thread slot
    thread_table[cpu_private_data.thread_index].data.owner = -1;

    // Check if the thread is the last one
    if (!(--process_table[current_process].threads)) {
        //Finally we cleanup
        cleanup_process(current_process);
    }


    // Free up all ports used by the process
    free_all_ports(current_process);


    //thread_queue_dequeue(&ready_queue);
    SYSCALL_ARGUMENTS.rax = ALL_OK;
    break;



}

    case SYSCALL_CREATEPROCESS:
{
    SYSCALL_ARGUMENTS.rax = ERROR;
    // Sanity check. Cant create more processes than the system allows
    if (SYSCALL_ARGUMENTS.rdi < MAX_NUMBER_OF_PROCESSES) {
        /* Loop over all processes in the process table, finding an empty slot. */
        int i;
        for (i = 0; i < MAX_NUMBER_OF_PROCESSES; i++) {
            // Check if the process has any threads. 
            if (!process_table[i].threads) {
                static struct prepare_process_return_value prepared_process;
                prepared_process = prepare_process(executable_table[SYSCALL_ARGUMENTS.rdi].elf_image,
                        i,
                        executable_table[SYSCALL_ARGUMENTS.rdi].memory_footprint_size);

                process_table[i].threads++;
                process_table[i].parent = thread_table[cpu_private_data.thread_index].data.owner;

                if (allocate_port(0, i) == -1) {
                    SYSCALL_ARGUMENTS.rax = ERROR;
                    break;
                }

                int new_thread = allocate_thread();
                thread_table[new_thread].data.owner = i;
                thread_table[new_thread].data.registers.integer_registers.rflags = 0x200;
                thread_table[new_thread].data.registers.integer_registers.rip = prepared_process.first_instruction_address;


                // Enqueue the new thread
                thread_queue_enqueue(&ready_queue, new_thread);


                /*
                                kprints("Thread nr: ");
                                kprinthex(new_thread);
                                kprints(" added\n");
                 */

                SYSCALL_ARGUMENTS.rax = ALL_OK;
                break;
            }
        }
    }
    break;
}
    case SYSCALL_SEND:
{
    //a sender will block until some other thread performs a receive on the same port.
    //if receive already ?
    //SYSCALL_ARGUMENTS.rax = status code
    //SYSCALL_ARGUMENTS.rdi = port index
    //SYSCALL_ARGUMENTS.rsi = message type
    //SYSCALL_ARGUMENTS.rbx = message ptr



    int sender_process = thread_table[cpu_private_data.thread_index].data.owner;
    int receiver_thread = port_table[SYSCALL_ARGUMENTS.rdi].receiver;

    // Sanity check the arguments
    if (SYSCALL_ARGUMENTS.rsi != SYSCALL_MSG_SHORT ||
            port_table[SYSCALL_ARGUMENTS.rdi].owner == -1) {
        SYSCALL_ARGUMENTS.rax = ERROR;
        break;
    } else {
        SYSCALL_ARGUMENTS.rax = ALL_OK;
    }

    if (receiver_thread != -1) {
        // Transfer message to receiver
        struct message *rec_msg, *send_msg;
        send_msg = (struct message *) SYSCALL_ARGUMENTS.rbx;
        rec_msg = (struct message *) thread_table[receiver_thread].data.registers.integer_registers.rbx;
        (*rec_msg).quad_0 = (*send_msg).quad_0;
        (*rec_msg).quad_1 = (*send_msg).quad_1;
        (*rec_msg).quad_2 = (*send_msg).quad_2;
        (*rec_msg).quad_3 = (*send_msg).quad_3;
        (*rec_msg).quad_4 = (*send_msg).quad_4;
        (*rec_msg).quad_5 = (*send_msg).quad_5;
        (*rec_msg).quad_6 = (*send_msg).quad_6;
        (*rec_msg).quad_7 = (*send_msg).quad_7;

        //1 send data to receiver
        thread_table[receiver_thread].data.registers.integer_registers.rax = ALL_OK;
        thread_table[receiver_thread].data.registers.integer_registers.rdi = sender_process;
        thread_table[receiver_thread].data.registers.integer_registers.rsi = SYSCALL_ARGUMENTS.rsi;
        //thread_table[receiver_thread].data.registers.integer_registers.rbx = SYSCALL_ARGUMENTS.rbx;

        //2 unblock receiving thread
        thread_queue_enqueue(&ready_queue, receiver_thread);
        port_table[SYSCALL_ARGUMENTS.rdi].receiver = -1;

    } else {
        //block current thread
        thread_queue_enqueue(&port_table[SYSCALL_ARGUMENTS.rdi].sender_queue, cpu_private_data.thread_index);
        cpu_private_data.thread_index = thread_queue_dequeue(&ready_queue);
    }

    break;
}

    case SYSCALL_RECEIVE:
{

    //int sender_process = thread_table[cpu_private_data.thread_index].data.owner;


    //a thread that performs a receive primitive will block until some other thread sends a message to the same port
    //is there a message ready already?

    //port_table[SYSCALL_ARGUMENTS.rdi].receiver != -1
    if (SYSCALL_ARGUMENTS.rsi != SYSCALL_MSG_SHORT ||
            port_table[SYSCALL_ARGUMENTS.rdi].owner == -1 ||
            port_table[SYSCALL_ARGUMENTS.rdi].receiver != -1) {
        SYSCALL_ARGUMENTS.rax = ERROR;
        break;
    } else {
        SYSCALL_ARGUMENTS.rax = ALL_OK;
    }

    if (!thread_queue_is_empty(&port_table[SYSCALL_ARGUMENTS.rdi].sender_queue)) {
        int sender_thread = thread_queue_dequeue(&port_table[SYSCALL_ARGUMENTS.rdi].sender_queue);


        struct message *rec_msg, *send_msg;
        send_msg = (struct message *) thread_table[sender_thread].data.registers.integer_registers.rbx;
        rec_msg = (struct message *) SYSCALL_ARGUMENTS.rbx;
        (*rec_msg).quad_0 = (*send_msg).quad_0;
        (*rec_msg).quad_1 = (*send_msg).quad_1;
        (*rec_msg).quad_2 = (*send_msg).quad_2;
        (*rec_msg).quad_3 = (*send_msg).quad_3;
        (*rec_msg).quad_4 = (*send_msg).quad_4;
        (*rec_msg).quad_5 = (*send_msg).quad_5;
        (*rec_msg).quad_6 = (*send_msg).quad_6;
        (*rec_msg).quad_7 = (*send_msg).quad_7;

        SYSCALL_ARGUMENTS.rdi = thread_table[sender_thread].data.owner;
        SYSCALL_ARGUMENTS.rsi = thread_table[sender_thread].data.registers.integer_registers.rsi;
        //SYSCALL_ARGUMENTS.rbx = thread_table[thread].data.registers.integer_registers.rbx;

        thread_queue_enqueue(&ready_queue, sender_thread);
    } else {
        //block current thread
        port_table[SYSCALL_ARGUMENTS.rdi].receiver = cpu_private_data.thread_index;
        cpu_private_data.thread_index = thread_queue_dequeue(&ready_queue);
    }

    break;
}









