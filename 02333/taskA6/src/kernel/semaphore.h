/* 
 * File:   semaphore.h
 * Author: krc
 *
 * Created on April 8, 2010, 2:11 PM
 */

#ifndef _SEMAPHORE_H
#define	_SEMAPHORE_H

struct semaphore
{
    int count ;
    int calling_process;
    //blocked threads
    struct thread_queue blocked_threads;
};


#endif	/* _SEMAPHORE_H */

