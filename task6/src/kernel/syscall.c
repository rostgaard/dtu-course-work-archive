/*! \file syscall.c

 This files holds the implementations of system calls.
 It is merged into the system_call_handler function in
 kernel.c through a pre-processor include.

 */

#include "threadqueue.h"
#include "semaphore.h"
#include "sync.h"


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
	*rec_msg = *send_msg;

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
        schedule = 1;
        //cpu_private_data.thread_index = thread_queue_dequeue(&ready_queue);
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
	*rec_msg = *send_msg;

        SYSCALL_ARGUMENTS.rdi = thread_table[sender_thread].data.owner;
        SYSCALL_ARGUMENTS.rsi = thread_table[sender_thread].data.registers.integer_registers.rsi;

        thread_queue_enqueue(&ready_queue, sender_thread);
    } else {
        //block current thread
        port_table[SYSCALL_ARGUMENTS.rdi].receiver = cpu_private_data.thread_index;
        //cpu_private_data.thread_index = thread_queue_dequeue(&ready_queue);
        schedule = 1;
    }

    break;
}

    case SYSCALL_CREATETHREAD:
    {
        SYSCALL_ARGUMENTS.rax = ERROR;

        int current_process = thread_table[cpu_private_data.thread_index].data.owner;


        int new_thread = allocate_thread();
        if(new_thread != -1) {
            thread_table[new_thread].data.owner = current_process   ;
            thread_table[new_thread].data.registers.integer_registers.rflags = 0x200;
            thread_table[new_thread].data.registers.integer_registers.rip = SYSCALL_ARGUMENTS.rdi;
            thread_table[new_thread].data.registers.integer_registers.rsp = SYSCALL_ARGUMENTS.rsi;


            // Enqueue the new thread
            thread_queue_enqueue(&ready_queue, new_thread);


            process_table[current_process].threads++;
            /*
                            kprints("Thread nr: ");
                            kprinthex(new_thread);
                            kprints(" added\n");
             */

            SYSCALL_ARGUMENTS.rax = ALL_OK;
        }

        break;
    }

    case SYSCALL_CREATESEMAPHORE:
    {
        SYSCALL_ARGUMENTS.rax = ERROR;

        long i;

        for(i = 0; i<MAX_NUMBER_OF_SEMAPHORES;i++) {
            if(semaphore_table[i].calling_process == -1) {
                kprints("Found a free entry in semaphore_table\n");
                break;
            }
        }

        if(i!=MAX_NUMBER_OF_SEMAPHORES && SYSCALL_ARGUMENTS.rdi >= 0){
            kprints("Sanity check ok\n");
            semaphore_table[i].count = SYSCALL_ARGUMENTS.rdi;

            semaphore_table[i].calling_process =
                thread_table[cpu_private_data.thread_index].data.owner;

            thread_queue_init(&semaphore_table[i].blocked_threads);
            SYSCALL_ARGUMENTS.rax = i;
        }
        break;

    }

    case SYSCALL_SEMAPHOREDOWN:
    {
        SYSCALL_ARGUMENTS.rax = ERROR;

        int s_index = SYSCALL_ARGUMENTS.rdi;


        // Check if the calling thread's process owns the semaphore
        if(semaphore_table[s_index].calling_process == thread_table[cpu_private_data.thread_index].data.owner &&
           s_index >= 0 && s_index < MAX_NUMBER_OF_SEMAPHORES ) {


            if (semaphore_table[s_index].count > 0) {
                semaphore_table[s_index].count--;
            }
            else
            {
                // block the current thread


                thread_queue_enqueue(&semaphore_table[s_index].blocked_threads,
                                     cpu_private_data.thread_index);
                // Force reschedule
                schedule = 1;


            }
            SYSCALL_ARGUMENTS.rax = ALL_OK;

        }


        break;
    }

    case SYSCALL_SEMAPHOREUP:
    {
        SYSCALL_ARGUMENTS.rax = ERROR;

        int s_index = SYSCALL_ARGUMENTS.rdi;


        // Check if the calling thread's process owns the semaphore
        if(semaphore_table[s_index].calling_process == thread_table[cpu_private_data.thread_index].data.owner &&
            s_index >= 0 && s_index < MAX_NUMBER_OF_SEMAPHORES ) {
            
            int released_thread;

            if(!thread_queue_is_empty(&semaphore_table[s_index].blocked_threads)) {
                released_thread = thread_queue_dequeue(&semaphore_table[s_index].blocked_threads);
                thread_queue_enqueue(&ready_queue,released_thread);
            }
            else
            {
                semaphore_table[s_index].count++;
            }

            SYSCALL_ARGUMENTS.rax = ALL_OK;

        }


        break;
    }

    case SYSCALL_CREATEMUTEX:
    {
        SYSCALL_ARGUMENTS.rax = ERROR;

        long i;

        for(i = 0; i<MAX_NUMBER_OF_MUTEXES;i++) {
            if(mutex_table[i].calling_process == -1) {
                break;
            }
        }

        if(i!=MAX_NUMBER_OF_MUTEXES){
            kprints("Sanity check ok\n");
            mutex_table[i].count = 1;

            mutex_table[i].calling_process =
                thread_table[cpu_private_data.thread_index].data.owner;

            thread_queue_init(&mutex_table[i].blocked_threads);
            SYSCALL_ARGUMENTS.rax = i;
        }
        break;
    }

    /*
     mutexlock that performs a lock operation on a mutex. The handle to the mutex is
passed in the rdi register. The system call returns, in register rax, ALL_OK if
successful or an error code otherwise.
mutexunlock that performs an unlock operation on a mutex. The handle to the

     */
    case SYSCALL_MUTEXLOCK: {
        SYSCALL_ARGUMENTS.rax = ERROR;

        int mutex_handle = SYSCALL_ARGUMENTS.rdi;

        /* Check if the calling thread's process owns the mutex, and the mutex
           handle is in range of valid handles. */
        if(mutex_table[mutex_handle].calling_process == thread_table[cpu_private_data.thread_index].data.owner &&
           mutex_handle >= 0 && mutex_handle < MAX_NUMBER_OF_MUTEXES ) {
            /* If the mutex is unlocked, snatch it */
            if (mutex_table[mutex_handle].count) {
                mutex_table[mutex_handle].count = 0;
            }
            /* otherwise block the current thread */
            else
            {
                thread_queue_enqueue(&mutex_table[mutex_handle].blocked_threads,
                                     cpu_private_data.thread_index);
                // Do explicit reschedule at this point
                schedule = 1;
            }
            SYSCALL_ARGUMENTS.rax = ALL_OK;
        }
        break;
    }

    case SYSCALL_MUTEXUNLOCK: {
        SYSCALL_ARGUMENTS.rax = ERROR;
        int mutex_handle = SYSCALL_ARGUMENTS.rdi;

        /* Check if the calling thread's process owns the mutex, and the mutex
           handle is in range of valid handles. */
        if(mutex_table[mutex_handle].calling_process == thread_table[cpu_private_data.thread_index].data.owner &&
            mutex_handle >= 0 && mutex_handle < MAX_NUMBER_OF_MUTEXES ) {

            /* if there are threads waiting for the mutex, release the next one*/
            if(!thread_queue_is_empty(&mutex_table[mutex_handle].blocked_threads)) {
                thread_queue_enqueue(&ready_queue,
                                      thread_queue_dequeue(&mutex_table[mutex_handle].blocked_threads));
            }
            /* otherwise release the lock */
            else
            {
                mutex_table[mutex_handle].count = 1;
            }
            SYSCALL_ARGUMENTS.rax = ALL_OK;
        }
        break;
    }

    /*
     * createconditionvariable that creates a new condition variable. The system call
returns, in the rax register, a handle to the condition variable if successful or an
error code otherwise.*/
    case SYSCALL_CREATECONDITIONVARIABLE : {
        SYSCALL_ARGUMENTS.rax = ERROR;

        long i;

        for(i = 0; i<MAX_NUMBER_OF_CONDITION_VARIABLES;i++) {
            if(condition_variable_table[i].owner == -1) {
                break;
            }
        }
        condition_variable_table[i].owner =
            thread_table[cpu_private_data.thread_index].data.owner;
        thread_queue_init(&condition_variable_table[i].blocked_threads);
        SYSCALL_ARGUMENTS.rax = i;
        break;
    }
/*conditionvariablewait that blocks the calling thread until a signal on the
condition variable occurs. The system call takes two arguments. The handle to
the condition variable is passed in the rdi register. The handle of a mutex, that
must be in the taken state, is passed in the rsi register. The mutex is assumed to
be used to ensure mutual exclusion to a monitor. Before the calling thread is
blocked, an unlock operation is performed on the mutex. The system call
returns, in the rax register, ALL_OK if successful or an error code otherwise.
*/
    case SYSCALL_CONDITIONVARIABLEWAIT: {
        SYSCALL_ARGUMENTS.rax = ERROR;
        int condition_variable_handle = SYSCALL_ARGUMENTS.rdi;
        int mutex_handle = SYSCALL_ARGUMENTS.rsi;
        /* mutex must be in the taken state */
        if(!mutex_table[mutex_handle].count) {
            thread_queue_enqueue(&ready_queue,
                                  thread_queue_dequeue(&mutex_table[mutex_handle].blocked_threads));
        }

        /*The mutex is assumed to
        be used to ensure mutual exclusion to a monitor. Before the calling thread is
        blocked, an unlock operation is performed on the mutex. The system call
        returns, in the rax register, ALL_OK if successful or an error code otherwise.*/

        if(condition_variable_table[condition_variable_handle].owner ==
                thread_table[cpu_private_data.thread_index].data.owner) {
            thread_queue_enqueue(&condition_variable_table[condition_variable_handle].blocked_threads,
                                 cpu_private_data.thread_index);

        // Do explicit reschedule at this point
        schedule = 1;
        SYSCALL_ARGUMENTS.rax = ALL_OK;
        }

        break;
    }
    /*conditionvariablesignal which performs a signal operation on the condition
variable. Nothing happens if no threads are waiting on the condition variable. If
threads are waiting then at least one of them are released. For each thread
released, a lock operation is performed on the mutex they passed as argument to
the condtionvariablewait system call. The system call returns, in the rax register,
ALL_OK if successful or an error code otherwise.
*/
    case SYSCALL_CONDITIONVARIABLESIGNAL: {
        int condition_variable_handle = SYSCALL_ARGUMENTS.rdi;
        int thread;
        int mutex = condition_variable_table[condition_variable_handle].mutex_handle;

        while(!thread_queue_is_empty(&condition_variable_table[condition_variable_handle].blocked_threads)) {
            thread = thread_queue_dequeue(&condition_variable_table[condition_variable_handle].blocked_threads);
            thread_queue_enqueue(&mutex_table[mutex].blocked_threads, thread);
        }


        break;
    }