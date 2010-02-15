/*! \file scheduler.c
   Implement the non-preemptive scheduler here. 
 */

#include "threadqueue.h"


if(schedule == 1){
    register int  new_thread_index, old_thread_index;
    new_thread_index = thread_queue_dequeue(&ready_queue);
    old_thread_index = cpu_private_data.thread_index;

    kprints("Thread id: ");
    kprinthex(old_thread_index);
    kprints(" is now in ready queue\n");
    
    kprints("Thread id: ");
    kprinthex(new_thread_index);
    kprints(" is now running\n");


/*
    thread_queue_enqueue(&ready_queue, old_thread_index);
*/
    cpu_private_data.thread_index = new_thread_index;

}
