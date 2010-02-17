/*! \file pscheduler.c
   Implement the preemptive scheduler here. 
 */

#include "kernel.h"
#include "threadqueue.h"
// Increment the current thread time slice counter
cpu_private_data.ticks_left_of_time_slice++;

// Check if the time slice is up
if(cpu_private_data.ticks_left_of_time_slice >= 20){
    // Enqueue the current thread
    thread_queue_enqueue(&ready_queue, cpu_private_data.thread_index);
    // Dequeue the next thread and run it
    cpu_private_data.thread_index = thread_queue_dequeue(&ready_queue);
    // Reset the time slice
    cpu_private_data.ticks_left_of_time_slice = 0;
}
