#include <stdio.h>
#define SCREEN_WIDTH 128
#define GAME_SCREEN_WIDTH 20
#define GAME_SCREEN_HEIGHT 40

//Macro Direction
#define LEFT 1
#define RIGHT 2
#define UP 3
#define DOWN 4
//Macro Boolean
#define TRUE 1
#define FALSE 0

//Pre Init Functions

//int RandomPositionY();
//int RandomPositionX();


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

void InitialiseArrayWithWall(short Array[GAME_SCREEN_HEIGHT][GAME_SCREEN_WIDTH])
{
	int x,y;
	for(x = 0; x < GAME_SCREEN_HEIGHT;x++)//Maby "=" is wrong syntax...
	{
		for(y = 0; y < GAME_SCREEN_WIDTH;y++)//Maby "=" is wrong syntax...
		{
			if(x==0||x==GAME_SCREEN_HEIGHT-1||y==0||y==GAME_SCREEN_WIDTH-1)
			{
				Array[x][y] = 2;
			}
			else
			{
				Array[x][y] = 0;
			}
		}
	}
}

void PrintArray(short Array[GAME_SCREEN_HEIGHT][GAME_SCREEN_WIDTH])
{
	int x,y;
	for(x = 0; x < GAME_SCREEN_HEIGHT;x++)//Maby "=" is wrong syntax...
	{
		for(y = 0; y < GAME_SCREEN_WIDTH;y++)//Maby "=" is wrong syntax...
		{
			int value = Array[x][y];
			printf("%d",value);
			symbol_at(x,y,value);
		}
	}
}

void main() {
	short y = 10;
	short x = 10;
	short TiltleArray[GAME_SCREEN_HEIGHT][GAME_SCREEN_WIDTH];
	short c;
	short old_tile;

	clear_screen();
	InitialiseArrayWithWall(TiltleArray);
	PrintArray(TiltleArray);	
	while(1) {

		*sseg_reg = c;
	
		scanf("%c",&c);
		//delete old tile
		symbol_at(x,y,0);
		//up
		if(c == 'w') {
			y--;
		}
		//down
		if(c == 's') {
			y++;
		}
		//left
		if(c == 'a') {
			x--;
		}
		//right
		if(c == 'd') {
			x++;
		}
		symbol_at(x,y,3);
	}
}
