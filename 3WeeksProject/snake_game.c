#include <stdio.h>
// The number of chars on the screen (48 offscreen)
#define SCREEN_WIDTH 128
#define NUM_COLS 40
#define NUM_ROWS 20

//Macro Direction
#define NONE 0x0000u
#define RIGHT 0x0001u
#define LEFT 0x0002u
#define UP 0x0004u
#define DOWN 0x0008u

//Macro Boolean
#define TRUE 1
#define FALSE 0
#define WALL 0x0005u
#define FOOD 0x0004u
#define EMPTY 0x0000u
#define SNAKE_HEAD 0x0006u
#define SNAKE_BODY 0x0002u
#define SNAKE_HEAD_DEAD 0x0003u

#define vid_mem_base (short *) 0xe000u
#define sw_reg (short *) 0xfe0au
#define btn_reg (short *) 0xfe0eu
#define sseg_reg (short *) 0xfe12u
#define led_reg (short *) 0xfe16u
    static unsigned short tick_lsb = 0;
    static unsigned short tick_msb = 0;

typedef struct snake_body snake_body_t;
struct tile_t;

struct snake {
	struct tile *location;
    short x; // Stores Head X Coordinate
    short y; // Stores Head Y Coordinate
    short direction; // Stores Head Direction
    short alive; // Alive value 1, dead value 0
    short score;
    short full; //Has the snake eaten anything?
    short length;
}; // Declares a variable of the structure

typedef struct snake snake_t;


struct tile {
    //short content;
    //struct tile *next;
    short previous_tile;
	struct tile *previous_tile_ptr;
    short *vid_mem_ptr;
};

typedef struct tile tile_t;

void clear_screen() {
    short row = 0;
    short colum = 0;
    while (row < 30) {
        colum = 0;
        while (colum < 80) {
            *(vid_mem_base + (SCREEN_WIDTH * row) + colum) = 0;
            colum++;
        }
        row++;
    }
}

void symbol_at(short x, short y, short c) {
    *(vid_mem_base + (SCREEN_WIDTH * y) + x) = c;
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

void initialize_snake(snake_t *snake, tile_t lvl[NUM_ROWS][NUM_COLS]) {
	short i;
	tile_t *last_tile;
    //Snake->length = 1;
    snake->score = 0;
    snake->x = 5;
    snake->y = 5;
    snake->direction = RIGHT;
    snake->full = FALSE;
    snake->length = 3;
    snake->alive = TRUE;
	
	// Draw the snake head
	*((lvl[snake->y][snake->x]).vid_mem_ptr) = SNAKE_HEAD;
	
	last_tile = &(lvl[snake->y][snake->x]);
	
	//Generate initial snake
	for(i=1; i < snake->length;i++) {
 		(lvl[(snake->y)][(snake->x)-i]).previous_tile_ptr = last_tile;
		last_tile = &(lvl[(snake->y)][(snake->x)-i]);
	}
}

void update_hud(snake_t *snake) {
    symbol_at(NUM_COLS + 2, 1, (snake->score) + 0x0030u);
}

void place_food(tile_t lvl[NUM_ROWS][NUM_COLS]) {
    if (*(lvl[11][3].vid_mem_ptr) == EMPTY) {
        *(lvl[11][3].vid_mem_ptr) = FOOD;
    } else if (*(lvl[10][15].vid_mem_ptr) == EMPTY) {
        *(lvl[10][15].vid_mem_ptr)= FOOD;
    }
}

/**
 *
 * @param snake The snake to be updated
 */
void update_snake(snake_t *snake, tile_t lvl[NUM_ROWS][NUM_COLS]) {
    short i;
	tile_t *new_tail, *head_tile = &(lvl[snake->y][snake->x]);
	
	if (snake->direction == RIGHT) {
        snake->x++;
    } else if (snake->direction == LEFT) {
        snake->x--;
    } else if (snake->direction == UP) {
            snake->y--;
    } else if (snake->direction == DOWN) {
            snake->y++;
    }
	
	//Game logic
	if(*((lvl[snake->y][snake->x]).vid_mem_ptr) == WALL) {
		snake->alive = FALSE; 
	} else if (*((lvl[snake->y][snake->x]).vid_mem_ptr) == SNAKE_BODY) {
		snake->alive = FALSE; 
	} else if(*((lvl[snake->y][snake->x]).vid_mem_ptr) == FOOD) {
		snake->length++;
		snake->score++;
		place_food(lvl);
	}
	

		// Link the previous and the current element together
	lvl[snake->y][snake->x].previous_tile_ptr = head_tile;
    
	// Find the tail
    for (i = 0; i < snake->length; i++) {
        // Last tile reached
        if (head_tile->previous_tile_ptr != '\0') {
            *(head_tile->vid_mem_ptr) = SNAKE_BODY;
            head_tile = head_tile->previous_tile_ptr;

			//This is the new tail, in case nothing is eaten in the game loop
			if(head_tile->previous_tile_ptr->previous_tile_ptr == '\0')
				new_tail = head_tile->previous_tile_ptr->previous_tile_ptr;
        }
    }
    if (snake->full) {
        snake->full = FALSE;
        snake->length++;
		place_food(lvl);
    } else {
		// Remove the reference to the next element, making this the new tail
		new_tail->previous_tile_ptr = '\0';
        *(head_tile->vid_mem_ptr) = EMPTY;
	}
		if(!snake->alive) {
			*((lvl[snake->y][snake->x]).vid_mem_ptr) = SNAKE_HEAD_DEAD;
		} else {
			*((lvl[snake->y][snake->x]).vid_mem_ptr) = SNAKE_HEAD+snake->direction;
		}
	
}

void dump_level(tile_t lvl[NUM_ROWS][NUM_COLS]) {
    short row, col;
    for (row = 0; row < NUM_ROWS; row++) {
        for (col = 0; col < NUM_COLS; col++) {
            printf("%d ", *(lvl[row][col].vid_mem_ptr));
        }
        printf("\n");
    }
}

void init_level(tile_t lvl[NUM_ROWS][NUM_COLS]) {
    int row, col;
    for (row = 0; row < NUM_ROWS; row++) {
        for (col = 0; col < NUM_COLS; col++) {
			//initialize video memory pointer
			lvl[row][col].vid_mem_ptr = (vid_mem_base + (SCREEN_WIDTH * row) + col);
            // Outer walls
            if (col == NUM_COLS - 1 || col == 0 || row == NUM_ROWS - 1 || row == 0) {
                //lvl[row][col].content = WALL;
				*(lvl[row][col].vid_mem_ptr) = WALL;
			}
            else {
				*(lvl[row][col].vid_mem_ptr) = EMPTY;
                //lvl[row][col].content = EMPTY;
			}
        }
        lvl[row][col].previous_tile_ptr = '\0';
    }
}


void main() {

    snake_t snake;
    tile_t level[NUM_ROWS][NUM_COLS];
	printf("%d",vid_mem_base);
	
    printf("clearscren\n");
	clear_screen();
	printf("init level\n");
	init_level(level);
	
	printf("init snake\n");
    initialize_snake(&snake, level);
	
	printf("place food\n");
	place_food(level);
	
    while (1) {
		   if (*btn_reg) {
		    // Stupidity detector
			if( 
			!(snake.direction == RIGHT && *btn_reg == LEFT) ||
			!(snake.direction == LEFT && *btn_reg == RIGHT) ||
			!(snake.direction == UP && *btn_reg == DOWN) ||
			!(snake.direction == DOWN && *btn_reg == UP)
			) {
            snake.direction = *btn_reg;}
			}
	
        update_snake(&snake, level);

        //dump_level(level);
		update_hud(&snake);
        wait();
		
		//Restart game if the snake is dead
		if(!snake.alive) {

			clear_screen();
			init_level(level);
			place_food(level);
			initialize_snake(&snake, level);
		}
    }
}