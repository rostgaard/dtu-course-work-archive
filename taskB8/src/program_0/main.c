/*! \file
 *      \brief
 *  \author Kim Rostgaard Christensen
 */

#include <scwrapper.h>
#define MAIN_DEBUG_LINE  (MAX_ROWS-1)

/*! This is the sensor process, it takes a simulated input ( a keyboard press )
    and passes it to the Light control process via IPC
 */
void
main(int argc, char* argv[]) {
    long send_port;

    if (ALL_OK != createprocess(1)) {
        printat(MAIN_DEBUG_LINE, 0, 
		"Sensor control: createprocess of Light control failed.\n");
        return;
    }

    // Locating the send port of the light control process
    send_port = findport(0, 1);
    if (send_port < 0) {
        printat(MAIN_DEBUG_LINE, 0, 
		"Sensor control: findport() failed (Light control) ");
        return;
    }

    printat(0, 0, "Welcome to VRTTCS (very realtime traffic control system)");
    printat(MAIN_DEBUG_LINE, 0, "Sensor control: ");

    while (1) {
        register long scan_code = getscancode();
        if (0x1c == scan_code) {
            struct message msg;
            printat(MAIN_DEBUG_LINE, 0, "Sensor control: Got sensor input");
            if (ALL_OK != send(send_port, &msg)) {
                printat(MAIN_DEBUG_LINE, 0, 
			"Sensor control: send() failed");
                return;
            } else {
                /* We let the debug message display some time*/
                pause(500);
                printat(MAIN_DEBUG_LINE, 0, "Sensor control:                                 ");
            }
        }
    }
}
