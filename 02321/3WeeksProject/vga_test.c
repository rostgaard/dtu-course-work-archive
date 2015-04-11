#include <stdio.h>
#define SCREEN_WIDTH 128

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

short get_symbol_at(short x, short y) {
	return *(vid_mem_base+(SCREEN_WIDTH*y)+x);
}


void main () {

	short disp = 0;
	clear_screen();
	symbol_at(2, 2, *sw_reg);

	while(1) {
		//symbol_at(2, 2, *sw_reg);
		scanf("%c");
		*(vid_mem_base) = *sw_reg;
		
		
		//scanf("%c");
		*led_reg = *(vid_mem_base);
		printf("%d",*(vid_mem_base));
		//printf("%x",disp);
	}
}