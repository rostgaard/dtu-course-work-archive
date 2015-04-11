#include <stdio.h>
#define SCREEN_WIDTH 128
#define GAME_SCREEN_WIDTH 20
#define GAME_SCREEN_HEIGHT 20

//Macro Direction
#define LEFT 1
#define RIGHT 2
#define UP 3
#define DOWN 4
//Macro Boolean
#define TRUE 1
#define FALSE 0
#define WALL 0x0008u
#define EMPTY 0x0000u
#define SNAKE_HEAD 0x0001u
#define SNAKE_BODY 0x0002u

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
				Array[x][y] = WALL;
			}
			else
			{
				Array[x][y] = EMPTY;
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

void wait() {
  unsigned short lsb = 0;
  unsigned short msb = 0;

  while (msb != 0x0005u) {
    lsb = 0;
    while (lsb != 0xffffu) {
      lsb++;
    }
    msb++;
  }
}

void main() {
	short y = 10;
	short x = 10;
	short TiltleArray[GAME_SCREEN_HEIGHT][GAME_SCREEN_WIDTH];
	short old_tile;
	short direction = RIGHT;

	clear_screen();
	InitialiseArrayWithWall(TiltleArray);
	PrintArray(TiltleArray);	
	
	//Game update loop
	while(1) {

		*sseg_reg = direction;
	
		//delete old tile
		symbol_at(x,y,EMPTY);
		//up
		if(*btn_reg == 0x0001u) {
			direction = UP;
		}
		//down
		if(*btn_reg == 0x0002u) {
			direction = DOWN;
		}
		//left
		if(*btn_reg == 0x0004u) {
			direction = LEFT;
		}
		//right
		if(*btn_reg == 0x0008u) {
			direction = RIGHT;
		}
		if(direction == RIGHT) {
			if(x == GAME_SCREEN_WIDTH-2)
				x=1;
			else
				x++;
		}
		if(direction == LEFT) {
			if(x == 1)
				x = GAME_SCREEN_WIDTH-2;
			else
				x--;
		}
		if(direction == UP) {
			if(y==1)
				y=GAME_SCREEN_HEIGHT-2;
			else
				y--;
		}
		if(direction == DOWN) {
			if(y==GAME_SCREEN_HEIGHT-2)
				y=2;
			else
				y++;
		}


		
		symbol_at(x,y,SNAKE_HEAD);
		wait();
	}
}