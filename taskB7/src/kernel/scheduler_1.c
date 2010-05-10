/*! \file scheduler.c
   Implement the non-preemptive scheduler here.
 */

#include "threadqueue.h"
/*
Check if we want to force a reschedule.
Forcing a reschedule means that the previously
running thread must not continue to execute and
must not be put into the ready queue.
*/
if(schedule != 1){
    // We dont want to force a reschedule so the currently running thread is put into the ready queue
    thread_queue_enqueue(&ready_queue, cpu_private_data.thread_index);


}
// Dequeue the head thread and let it execute
cpu_private_data.thread_index = thread_queue_dequeue(&ready_queue);
