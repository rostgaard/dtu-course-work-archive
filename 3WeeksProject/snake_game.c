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

#define vid_mem_base (short *) 0xe000u
#define sw_reg (short *) 0xfe0au
#define btn_reg (short *) 0xfe0eu
#define sseg_reg (short *) 0xfe12u
#define led_reg (short *) 0xfe16u


//Pre Init Functions

//int RandomPositionY();
//int RandomPositionX();


	
typedef struct snake_body snake_body_t;

struct snake {
	short x; 	// Stores Head X Coordinate
	short y; 	// Stores Head Y Coordinate
	short direction; 	// Stores Head Direction
	short alive;		// Alive value 1, dead value 0
	short score;
	short full;  //Has the snake eaten anything?
	short length;
	//int tail_x; 	// Stores Tail X Coordinat
	//int tail_y; 	// Stores Tail Y Coordinat
	//int tail_dir; // Stores Tail Direction
}; 		// Declares a variable of the structure

typedef struct snake snake_t;

struct tile_t;

struct tile {
	short content;
	struct tile *next;
	short *vid_mem_ptr;
};

typedef struct tile tile_t;

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

void InitialiseArrayWithWall(tile_t tile_array[GAME_SCREEN_HEIGHT][GAME_SCREEN_WIDTH])
{
	int x,y;
	for(x = 0; x < GAME_SCREEN_HEIGHT;x++)//Maby "=" is wrong syntax...
	{
		for(y = 0; y < GAME_SCREEN_WIDTH;y++)//Maby "=" is wrong syntax...
		{
			tile_array[x][y].next = '\0';
			tile_array[x][y].vid_mem_ptr = (vid_mem_base+(SCREEN_WIDTH*y)+x);
			if(x==0||x==GAME_SCREEN_HEIGHT-1||y==0||y==GAME_SCREEN_WIDTH-1)
			{
				tile_array[x][y].content = WALL;
			}
			else
			{
				tile_array[x][y].content  = EMPTY;
			}
			
		}
	}
}

void PrintArray(tile_t Array[GAME_SCREEN_HEIGHT][GAME_SCREEN_WIDTH])
{
	short x,y;
	x = 2;
	y = 5;

	for(x = 0; x < GAME_SCREEN_HEIGHT;x++)//Maby "=" is wrong syntax...
	{
		for(y = 0; y < GAME_SCREEN_WIDTH;y++)//Maby "=" is wrong syntax...
		{
			int value = Array[x][y].content ;
			symbol_at(x,y,value);
			printf("%d",value);
		}
		printf("\n");
	}
}
// Wait primitive that occupies the CPU for a fixed number of cycles - should be converted to assembler for better timing control
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
   snake->length = 3;
   //Snake.tail_x = Snake.head_x- Snake.length;
   //Snake.tail_y = Snake.head_y;
   //Snake.tail_dir = RIGHT;
   snake->alive = TRUE;
}

void update_hud (snake_t *snake) {
	symbol_at(GAME_SCREEN_HEIGHT+2,1,(snake->score)+0x0030u);
}

void place_food(tile_t level[GAME_SCREEN_HEIGHT][GAME_SCREEN_WIDTH]) {
	if(level[2][5].content == EMPTY) {
		level[2][5].content = FOOD;
		symbol_at(2,5,level[2][5].content);
	}
	else if(level[7][5].content == EMPTY) {
		level[7][5].content = FOOD;
		symbol_at(7,5,level[7][5].content);
	}
}

void update_snake(snake_t *snake) {
		if(snake->direction == RIGHT) {
			if(snake->x == GAME_SCREEN_WIDTH-2)
				snake->x=1;
			else
				snake->x++;
		}
		else if(snake->direction == LEFT) {
			if(snake->x == 1)
				snake->x = GAME_SCREEN_WIDTH-2;
			else
				snake->x--;
		}
		else if(snake->direction == UP) {
			if(snake->y==1)
				snake->y=GAME_SCREEN_HEIGHT-2;
			else
				snake->y--;
		}
		else if(snake->direction == DOWN) {
			if(snake->y==GAME_SCREEN_HEIGHT-2)
				snake->y=2;
			else
				snake->y++;
		}
}

void main() {
	snake_t snake;
	tile_t tile_array[GAME_SCREEN_HEIGHT][GAME_SCREEN_WIDTH];
	short old_x;
	short old_y;
	
	initialize_snake(&snake);
	clear_screen();
	
	
	InitialiseArrayWithWall(tile_array);
	place_food(tile_array);
	
	PrintArray(tile_array);
	
	//Game update loop
	while(1) {
		*sseg_reg = snake.direction;
	
		old_x = snake.x;
		old_y = snake.y;
	
		// This sets the new direction from the btn's
		if(*btn_reg) 
			snake.direction = *btn_reg;

		// Calculate the new snake head location
		update_snake(&snake);

		//Game logic
		if(tile_array[snake.x][snake.y].content == FOOD) {
			snake.score++;
			place_food(tile_array); // Generate a new food
			if(tile_array[snake.x][snake.y].next == '\0') {
				tile_array[snake.x][snake.y].next = &tile_array[old_x][old_y];
				tile_array[old_x][old_y].content = SNAKE_BODY;
			}
		} else {
			while(tile_array[snake.x][snake.y].next != '\0') {
				tile_array[old_x][old_y].content = SNAKE_BODY;				
			}
			// Delete old tile
			tile_array[old_x][old_y].content = EMPTY;
		}

		// Place the new head and draw it
		tile_array[snake.x][snake.y].content = SNAKE_HEAD;
		symbol_at(snake.x,snake.y,SNAKE_HEAD);		
		
		// Update the heads up display
		update_hud(&snake);
		// Game delay
		wait();
	}
}
