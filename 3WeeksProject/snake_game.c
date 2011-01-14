#include <stdio.h>
#define SCREEN_WIDTH 128
#define GAME_SCREEN_WIDTH 20
#define GAME_SCREEN_HEIGHT 20

//Macro Direction
#define RIGHT 0x0001u
#define LEFT 0x0002u
#define UP 0x0004u
#define DOWN 0x0008u
//Macro Boolean
#define TRUE 1
#define FALSE 0
#define WALL 0x0008u
#define FOOD 0x0007u
#define EMPTY 0x0000u
#define SNAKE_HEAD 0x0001u
#define SNAKE_BODY 0x0002u
#define SNAKE_BODY_MAXLEN 20;

//Pre Init Functions

//int RandomPositionY();
//int RandomPositionX();

//Snake Body
struct snake_body {
  int x;
  int y;
//  struct snake_body *next;
};

typedef struct snake_body snake_body_t;

struct snake {
	short x; 	// Stores Head X Coordinate
	short y; 	// Stores Head Y Coordinate
	short direction; 	// Stores Head Direction
	short alive;		// Alive value 1, dead value 0
	short score;
	short full;  //Has the snake eaten anything?
	
	snake_body_t body;
	//int tail_x; 	// Stores Tail X Coordinat
	//int tail_y; 	// Stores Tail Y Coordinat
	//int tail_dir; // Stores Tail Direction
}; 		// Declares a variable of the structure
typedef struct snake snake_t;

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
	short x,y;
	x = 2;
	y = 5;

	for(x = 0; x < GAME_SCREEN_HEIGHT;x++)//Maby "=" is wrong syntax...
	{
		for(y = 0; y < GAME_SCREEN_WIDTH;y++)//Maby "=" is wrong syntax...
		{
			int value = Array[x][y];
			printf("%d",value);
			symbol_at(x,y,value);
		}
		printf("\n");
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

//Snakes start coordinate & InitGame
void initialize_snake(snake_t *snake) {
   //Snake->length = 1;
   snake->score = 0;
   snake->x = 10;
   snake->y = 10;
   snake->direction = RIGHT;
   snake->full = FALSE;
   //Snake.tail_x = Snake.head_x- Snake.length;
   //Snake.tail_y = Snake.head_y;
   //Snake.tail_dir = RIGHT;
   snake->alive = TRUE;
}

void update_hud (snake_t *snake) {
	symbol_at(GAME_SCREEN_HEIGHT+2,1,(snake->score)+0x0030u);
}

void place_food(short level[GAME_SCREEN_HEIGHT][GAME_SCREEN_WIDTH]) {
	if(level[2][5] == EMPTY) {
		level[2][5] = FOOD;
		symbol_at(2,5,level[2][5]);
	}
	else if(level[7][5] == EMPTY) {
		level[7][5] = FOOD;
		symbol_at(7,5,level[7][5]);
	}
}

void main() {
	snake_t snake;
	short TiltleArray[GAME_SCREEN_HEIGHT][GAME_SCREEN_WIDTH];
	short old_tile;

	initialize_snake(&snake);
	clear_screen();
	
	
	InitialiseArrayWithWall(TiltleArray);
	place_food(TiltleArray);
	
	PrintArray(TiltleArray);
	//Game update loop
	while(1) {

		*sseg_reg = snake.direction;
	
		// Delete old tile
		symbol_at(snake.x,snake.y,EMPTY);
		TiltleArray[snake.x][snake.y] = EMPTY;
		
		
		// This sets the new direction from the btn's
		if(*btn_reg) 
			snake.direction = *btn_reg;

		// Calculate the new snake head location
		if(snake.direction == RIGHT) {
			if(snake.x == GAME_SCREEN_WIDTH-2)
				snake.x=1;
			else
				snake.x++;
		}
		else if(snake.direction == LEFT) {
			if(snake.x == 1)
				snake.x = GAME_SCREEN_WIDTH-2;
			else
				snake.x--;
		}
		else if(snake.direction == UP) {
			if(snake.y==1)
				snake.y=GAME_SCREEN_HEIGHT-2;
			else
				snake.y--;
		}
		else if(snake.direction == DOWN) {
			if(snake.y==GAME_SCREEN_HEIGHT-2)
				snake.y=2;
			else
				snake.y++;
		}
		
		if(TiltleArray[snake.x][snake.y] == FOOD) {
			snake.score++;
			place_food(TiltleArray);
			PrintArray(TiltleArray);
		}
		
		// Place the new head
		TiltleArray[snake.x][snake.y] == SNAKE_HEAD;

		// Draw the new snake head
		symbol_at(snake.x,snake.y,SNAKE_HEAD);

		// Update the heads up display
		update_hud(&snake);
		
		// Game delay
		wait();
	}
}
