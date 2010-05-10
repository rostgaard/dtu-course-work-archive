/* 
 * File:   semaphore.h
 * Author: krc
 *
 * Created on April 8, 2010, 2:11 PM
 */

#ifndef _SEMAPHORE_H
#define	_SEMAPHORE_H

#define MAX_NUMBER_OF_SEMAPHORES (256)
/*!< Size of the semaphore_table. */



struct semaphore
{
    int count ;
    int calling_process;
    //blocked threads
    struct thread_queue blocked_threads;
};

struct semaphore
semaphore_table[MAX_NUMBER_OF_SEMAPHORES];


#endif	/* _SEMAPHORE_H */

