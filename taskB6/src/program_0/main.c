/*! \file
 *      \brief This example illustrates the use of condition variables
 *
 */

#include <scwrapper.h>

long mutex_handle;
long condition;

void thread(void) {
    int count = 0;
    while (1) {
        prints("Thread 1: Trying to lock mutex\n");
        if (ALL_OK != mutex_lock(mutex_handle)) {
            prints("mutex_lock failed!\n");
            break;
        } else {
            prints("Thread 1: locked mutex\n");
        }
        if (count == 2) {
            conditionvariablesignal(condition);
            count=0;
        }
        if (ALL_OK != mutex_unlock(mutex_handle)) {
            prints("mutex_unlock failed!\n");
            break;
        } else {
            prints("Thread 1: unlocked mutex\n");
        }

        count++;
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


    while (1) {
        if (ALL_OK != mutex_lock(mutex_handle)) {
            prints("mutex_lock failed!\n");
            break;
        } else {
            prints("Main: Locking mutex\n");
        }

        if (ALL_OK != conditionvariablewait(condition,mutex_handle)) {
            prints("Main: conditionvariablewait failed!\n");
            break;
        } else {
            prints("Main: wait condition appeared, trying to get lock!\n");
        }
        if (ALL_OK != mutex_unlock(mutex_handle)) {
            prints("mutex_unlock failed!\n");
            break;
        } else {
            prints("Main: releasing mutex\n");
        }

    }
}
