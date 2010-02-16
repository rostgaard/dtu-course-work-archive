/*! \file scheduler.c
   Implement the non-preemptive scheduler here. 
 */

#include "threadqueue.h"


if(schedule == 1){
    register int  new_thread_index, old_thread_index;

    // Remove the head from the queue
    new_thread_index = thread_queue_dequeue(&ready_queue);
    // Save the current running thread
    old_thread_index = cpu_private_data.thread_index;

    // Check if the running thread was blocked
    if(thread_table[old_thread_index].data.list_data == 0){
/*
        kprints("Thread id: ");
        kprinthex(old_thread_index);
        kprints(" is now in ready queue\n");
*/
        // Add thread to queue if not blocked
        thread_queue_enqueue(&ready_queue, old_thread_index);
    }

    
    
/*
    kprints("Thread id: ");
    kprinthex(new_thread_index);
    kprints(" is now running\n");
*/

    // Let the new thread run.
    cpu_private_data.thread_index = new_thread_index;

}
