/***************************************************************************
 **
 **
 **    Master inlude file
 **
 **    Used with ARM IAR C/C++ Compiler
 **
 **    (c) Copyright IAR Systems 2007
 **
 **    $Revision: 24636 $
 **
 ***************************************************************************/

#ifndef __INCLUDES_H
#define __INCLUDES_H

#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>
#include <intrinsics.h>
#include <assert.h>
#include <nxp/iolpc2478.h>

#include "arm_comm.h"
#include "board.h"
#include "cursor_arrow.h"
#include "frequency.h"
#include "gpio.h"
#include "dac.h"

#include "photo.h"
#include "button_lightgrey.h"
#include "Black_bg.h"

//#include "iar_logo.h"
//#include "cursor_arrow.h"

#include "sys.h"
#include "sdram_32M_16bit_drv.h"
#include "drv_glcd.h"
//#include "drv_touch_scr.h"

#include "clock-arch.h"

#include "uip-timer.h"
#include "uip-conf.h"
#include "uipopt.h"
#include "uip_arp.h"
#include "uip.h"
#include "tapdev.h"
#include "httpd.h"

#endif  // __INCLUDES_H

