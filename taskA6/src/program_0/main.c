/*! \file
 *      \brief This example illustrates the use of condition variables
 *
 */

#include <scwrapper.h>

long mutex_handle;
long condition;

void thread(void) {

    while (1) {


        prints("Thread 1: Trying to lock mutex\n");
        if (ALL_OK != mutex_lock(mutex_handle)) {
            prints("Thread 1: mutex_lock failed!\n");
            break;
        } else {
            prints("Thread 1: Locking mutex\n");
        }

        if (ALL_OK != conditionvariablewait(condition,mutex_handle)) {
            prints("Thread 1: conditionvariablewait failed!\n");
            break;
        } else {
            prints("Thread 1: wait condition appeared, trying to get lock!\n");
        }
        if (ALL_OK != mutex_unlock(mutex_handle)) {
            prints("Thread 1: mutex_unlock failed!\n");
            break;
        } else {
            prints("Thread 1: releasing mutex\n");
        }

    }
    terminate();
}

void
main(int argc, char* argv[]) {
    register long counter = 0;
    register long thread_stack;


    mutex_handle = createmutex();
    if (mutex_handle < 0) {
        prints("createmutex failed!\n");
        return;
    }


    condition = createconditionvariable();
    if (condition < 0) {
        prints("createconditionvariable failed!\n");
        return;
    }



    thread_stack = alloc(4096, 0);

    if (0 >= thread_stack) {
        prints("Could not allocate the thread's stack!\n");
        return;
    }

    if (ALL_OK != createthread(thread, thread_stack + 4096)) {
        prints("createthread failed!\n");
        return;
    }

    int count = 0;
    while (1) {
        prints("Main: Trying to lock mutex\n");
        if (ALL_OK != mutex_lock(mutex_handle)) {
            prints("Main: mutex_lock failed!\n");
            break;
        } else {
            prints("Main: locked mutex\n");
        }

        prints("Main: Trying to unlock mutex\n");
        if (ALL_OK != mutex_unlock(mutex_handle)) {
            prints("Main: mutex_unlock failed!\n");
            break;
        } else {
            prints("Main: unlocked mutex\n");
        }

        if (count == 2) {
            conditionvariablesignal(condition);
            count=0;
            prints("Main: signal condition\n");
        }

        count++;
    }
}
