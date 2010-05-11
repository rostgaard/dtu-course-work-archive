/*! \file
 *      \brief Light control is done from this file
 *  \author Kim Rostgaard Christensen
 */

#include <scwrapper.h>

#define MAIN_DEBUG_LINE  (MAX_ROWS-4)
#define CAR_DEBUG_LINE  (MAX_ROWS-6)
#define POLICY_DEBUG_LINE  (MAX_ROWS-5)
#define TIMER_DEBUG_LINE (MAX_ROWS-3)
#define IPC_DEBUG_LINE   (MAX_ROWS-2)

#define GREEN 1
#define RED 0
/*!<  For simplicity of concept, yellow is not included */

#define MINIMUM_TICKS 2000
/*!< The minimum number of ticks that must pass before a light switch */

#define SWITCH_DELAY_TICKS 1000
/*!< The delay between swithing lights on individual roads */


#define MAXIMUM_TICKS 5000


// Prototypes
void IPC_handler_thread(void);
void timer_thread(void);
void release_policy_thread();
void set_primary_state();
void set_secondary_state();


/*! This is actually a binary semaphore. Its purpose is to give mutual exclusion
    from IPC and timer threads */
long local_mutex;


long car_has_arrived = 0;
long release_lock = 0;

/*! The state of the lights is stored in a struct */
struct lights_state_t {
    int pri_car_light;
    int pri_ped_light;
    int sec_car_light;
    int sec_ped_light;

} lights_state;

/*! The main method initializes the light control system and starts the other
    threads.
 */
void
main(int argc, char* argv[]) {

    /* We create the semaphore before fireing up the threads, thus making
       sure that none of threads try to access it before, suffering under
       the fact that they dont exist */
    local_mutex = createsemaphore(1);
    if (local_mutex < 0) {
        printat(MAIN_DEBUG_LINE, 0, "Light Control: createsemaphore failed!\n");
        return;
    }

    register long ipc_thread_stack;
    register long timer_thread_stack;
    register long release_policy_thread_stack;

    ipc_thread_stack = alloc(4096, 0);
    timer_thread_stack = alloc(4096, 0);
    release_policy_thread_stack = alloc(4096, 0);


    if (0 >= ipc_thread_stack || 0 >= timer_thread_stack ||
            0 >= release_policy_thread_stack) {
        printat(MAIN_DEBUG_LINE, 0,
                "Light Control: Could not allocate the thread's stack!\n");
        return;
    }

    if (ALL_OK != createthread(IPC_handler_thread, ipc_thread_stack + 4096) ||
            ALL_OK != createthread(timer_thread, timer_thread_stack + 4096) ||
            ALL_OK != createthread(release_policy_thread,
            release_policy_thread_stack + 4096)) {
        printat(MAIN_DEBUG_LINE, 0, "Light Control: createthreads failed!\n");
        return;
    }
    printat(MAIN_DEBUG_LINE, 0, "Light Control: Online\n");
    while (1) {
        printat(MAIN_DEBUG_LINE, 0, "Light Control: Online\n");

    }
    printat(MAIN_DEBUG_LINE, 0, "Light Control: Offline\n");


    return 1;
}

/*! This is the thread in charge of handling incoming IPC requests. When a
    message is received, the thread tries to claim the mutex
 */
void IPC_handler_thread(void) {
    long recv_port;
    long my_pid;
    char pidchar;
    struct message msg;
    unsigned long sender, type;

    my_pid = getpid();
    if (my_pid < 0) {
        printat(IPC_DEBUG_LINE, 0, "Light Control IPC: Can not get my PID!");
        return;
    }

    /* Get the port index for our own port 0. */
    recv_port = findport(0, my_pid);
    if (recv_port < 0) {
        printat(IPC_DEBUG_LINE, 0,
                "Light Control IPC: findport_process_failed");
        return;
    }

    printat(IPC_DEBUG_LINE, 0, "Light Control IPC:");
    while (1) {
        if ((ALL_OK != receive(recv_port, &msg, &sender, &type)) ||
                (SYSCALL_MSG_SHORT != type) ||
                (0 != sender)) {
            printat(IPC_DEBUG_LINE, 0, "Light Control IPC: recv failed");
            debugger();
        } else {
            printat(IPC_DEBUG_LINE, 0, "Light Control IPC: requesting lock");

            /* Enter critical region */
            if (ALL_OK != semaphoredown(local_mutex)) {
                printat(IPC_DEBUG_LINE, 0,
                        "Light Control IPC: semaphoredown failed!\n");
                break;
            } else {
                printat(IPC_DEBUG_LINE, 0, "Light Control IPC: has lock       ");
                set_secondary_state();
                /* This delay asserts a minium hold time*/
                pause(MINIMUM_TICKS);

            }
            if (ALL_OK != semaphoreup(local_mutex)) {
                printat(IPC_DEBUG_LINE, 0,
                        "Light Control IPC: semaphoreup failed!\n");
                break;
            } else {
                printat(IPC_DEBUG_LINE, 0, "Light Control IPC: freed lock     ");

            }
            /* Leave critical region */

        }
    }

    /* This one is important, as threads do not have an exit procedure */
    terminate();

}

/*! This threads is the greedy one that tries to hold the lock and revert our
    status quo, leaving the road with precedence with the green light
 */
void timer_thread() {

    long timer_haslock = 0;

    while (1) {

        /* Enter critical region */
        if (!timer_haslock) {
            printat(TIMER_DEBUG_LINE, 0, "Light Control Timer: requesting lock");

            if (ALL_OK != semaphoredown(local_mutex)) {
                printat(TIMER_DEBUG_LINE, 0,
                        "Light Control Timer: semaphoredown failed!\n");
            } else {
                printat(TIMER_DEBUG_LINE, 0, "Light Control Timer: has lock       ");
                timer_haslock = 1;
                set_primary_state();
                /* This delay asserts a minium hold time*/
                pause(MINIMUM_TICKS);


            }
        }

        /* We only want to leave the region if the timer has expired*/
        if (release_lock) {
            if (ALL_OK != semaphoreup(local_mutex)) {
                printat(TIMER_DEBUG_LINE, 0,
                        "Light Control Timer: semaphoreup failed!\n");
            } else {
                printat(TIMER_DEBUG_LINE, 0, "Light Control Timer: freed lock     ");
                timer_haslock = 0;
                release_lock = 0;
            }
            /* Leave critical region */
        }

    }
    terminate();
}

void release_policy_thread() {
    long time_passed = 0;

    while (1) {
        pause(MINIMUM_TICKS);
        time_passed = time_passed + MINIMUM_TICKS;
        car_has_arrived = rnd()&1;



        /* If the timeout has expired, release the lock */

        if (time_passed >= MAXIMUM_TICKS) {
            release_lock = 1;
            time_passed = 0;
        } else {
            if (car_has_arrived) {
                release_lock = 0;
            } else {
                release_lock = 1;
            }
        }


        if (release_lock)
            printat(POLICY_DEBUG_LINE, 0, "Light Control Policy: releaselock = 1");
        else
            printat(POLICY_DEBUG_LINE, 0, "Light Control Policy: releaselock = 0");

        if (car_has_arrived)
            printat(CAR_DEBUG_LINE, 0, "Light Control: car arrived   ");
        else
            printat(CAR_DEBUG_LINE, 0, "Light Control: no car arrived");

        /*
        if (car_has_arrived && !(time_passed >= MAXIMUM_TICKS)) {
            printat(POLICY_DEBUG_LINE, 0, "Light Control Policy: releaselock = 0");
            release_lock = 0;
            car_has_arrived = 0;
        } else if (time_passed >= MAXIMUM_TICKS || !car_has_arrived) {
            printat(POLICY_DEBUG_LINE, 0, "Light Control Policy: releaselock = 1");
            release_lock = 1;
            time_passed = 0;
        }*/
    }
    terminate();
}

rnd(void) {
    const unsigned long seed = 101;
    static unsigned long memory;

    if ((memory == 0) ||
            (memory == 1) ||
            (memory == -1)) {
        memory = seed;
    }

    memory = (9973 * (~memory))+((memory) % 701);
    return memory;
}

/*! Abstraction method for setting the light to primary state */
void set_primary_state() {
    lights_state.sec_car_light = RED;
    lights_state.sec_ped_light = RED;
    update_display();
    pause(SWITCH_DELAY_TICKS);
    lights_state.pri_car_light = GREEN;
    lights_state.pri_ped_light = GREEN;
    update_display();
}

/*! Abstraction method for setting the light to secondary state */
void set_secondary_state() {
    lights_state.pri_car_light = RED;
    lights_state.pri_ped_light = RED;
    update_display();
    pause(SWITCH_DELAY_TICKS);
    lights_state.sec_car_light = GREEN;
    lights_state.sec_ped_light = GREEN;
    update_display();

}

/*! Abstraction method for updating the display. This implementation draws a
    nice little ASCII traffic intersection and uses chars to symbolize light
    colors using uppercase for car light, and lowercase for pedestrian.
 */
void update_display() {
    int x_offset, y_offset;
    y_offset = MAX_ROWS - 15;
    x_offset = MAX_COLS - 20;

    printat(y_offset + 0, x_offset + 5, "|     |");
    printat(y_offset + 1, x_offset + 5, "|     |");
    printat(y_offset + 2, x_offset + 5, "|     |");
    printat(y_offset + 3, x_offset + 5, "|     |");
    printat(y_offset + 4, x_offset, "-----       ------");

    printat(y_offset + 8, x_offset, "-----       ------");
    printat(y_offset + 9, x_offset + 5, "|     |");
    printat(y_offset + 10, x_offset + 5, "|     |");
    printat(y_offset + 11, x_offset + 5, "|     |");
    printat(y_offset + 12, x_offset + 5, "|     |");


    if (lights_state.pri_car_light == RED) {
        printat(y_offset + 6, x_offset + 5, "R\0");
        printat(y_offset + 6, x_offset + 11, "R\0");
    } else {
        printat(y_offset + 6, x_offset + 5, "G\0");
        printat(y_offset + 6, x_offset + 11, "G\0");
    }

    if (lights_state.pri_ped_light == RED) {
        printat(y_offset + 6, x_offset + 4, "r\0");
        printat(y_offset + 6, x_offset + 12, "r\0");
    } else {
        printat(y_offset + 6, x_offset + 4, "g\0");
        printat(y_offset + 6, x_offset + 12, "g\0");
    }

    if (lights_state.sec_car_light == RED) {
        printat(y_offset + 3, x_offset + 8, "R\0");
        printat(y_offset + 9, x_offset + 8, "R\0");
    } else {
        printat(y_offset + 3, x_offset + 8, "G\0");
        printat(y_offset + 9, x_offset + 8, "G\0");
    }

    if (lights_state.sec_ped_light == RED) {
        printat(y_offset + 2, x_offset + 8, "r\0");
        printat(y_offset + 10, x_offset + 8, "r\0");
    } else {
        printat(y_offset + 2, x_offset + 8, "g\0");
        printat(y_offset + 10, x_offset + 8, "g\0");
    }
}
