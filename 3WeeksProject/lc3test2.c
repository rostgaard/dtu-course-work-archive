#include <stdio.h>
#define SCREEN_WIDTH 128
#define GAME_SCREEN_WIDTH 80
#define GAME_SCREEN_HEIGHT 30

short *vid_mem_base = (short *) 0xe000u;
short *sw_reg = (short *) 0xfe0au;
short *btn_reg = (short *) 0xfe0eu;
short *sseg_reg = (short *) 0xfe12u;
short *led_reg = (short *) 0xfe16u;

void clear_screen() {
	short row = 0;
	short colum = 0;	
	while(row < 30) {
	    colum = 0;	
		while(colum < 80) {
			*(vid_mem_base+(SCREEN_WIDTH*row)+colum) = 0;
			colum++;
		}
		row++;
	}
}

void symbol_at(short x, short y, short c) {
	*(vid_mem_base+(SCREEN_WIDTH*y)+x) = c;
}

void main() {
	short x,tick = 0;
	int y = 0;
	short c;

	short row = 0;
	short colum = 0;	
	short i = 0;
	short offset;
	clear_screen();
while(1) {	
	//scanf("%c",&c);
	*led_reg = c;
	

	if(!(tick% 0xFFFFu)){
		symbol_at(i,i,1);
		tick=0;
		i++;
		if(i == 100) {
			i=0;
		}
	}

	tick++;
	}
}
