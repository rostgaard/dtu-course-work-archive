/*! \file 
    \brief Holds declarations for the synchronization sub-system. */

#ifndef _SYNC_H_
#define _SYNC_H_

#include "threadqueue.h"

#define MAX_NUMBER_OF_PORTS     (256)

#define TAKEN 0
#define FREE 1

/*! Describes a port. */
struct port
{
 int           owner;  /*!< This is an index into process_table. The
                           index corresponds to the process that owns
                           this thread. */
 unsigned long id;     /*!< The identity number of the port. */

 struct thread_queue sender_queue; /*!< The queue of threads which are blocked on a send operation */
 
 int receiver; /*!< The identity of a thread which is blocked on a receive opereation. Set to -1 if no thread is receiving. */
};

extern struct port
port_table[MAX_NUMBER_OF_PORTS];
/*!< Array holding information all ports. */

/*! Initializes the port table. */
extern void
initialize_ports(void);

/*! Allocates and initializes one port from the port table.
    \return an index into the port_table if a port could be allocated or -1 
    if no ports are available. */
extern int
allocate_port(const unsigned long id
               /*!< Identity number of the port after allocation. */, 
              const int new_owner
               /*!< Id of the process becoming the new owner of the port after allocation. */);

/*! Find a port with identity if owned by process owner. 
    \return the index into port_table of the port if a matching port is 
    found. Returns -1 otherwise. */
extern int
find_port(const unsigned long id
           /*! Identity number of the port that we want to find. */, 
          const int owner
           /*! Id of the owning process of the port that we want to find. */);

/*! Initializes the thread synchronization sub-system. Used in task 6. */
extern void
initialize_thread_synchronization(void);

/* Put any declarations you need to add to implement tasks B5, A5, B6 or A6 
   here. */


/* Semaphore and mutex handles */

struct semaphore
{
    int count ;
    int calling_process;
    //blocked threads
    struct thread_queue blocked_threads;
};


/* The mutex is similar to the semaphore, although is uses a binary count. See
 * system call for details */
struct mutex
{
    int state;
    int calling_process;
    int holding_thread;
    //blocked threads
    struct thread_queue blocked_threads;
};


/* The condition variable needs to have a list of threads waiting on
 the condition, the mutex inquestioned and for security reasons; an owner */
struct condition_variable
{
    int owner;
    int mutex_handle;
    struct thread_queue blocked_threads;
};


#define DEBUG 1;

#endif