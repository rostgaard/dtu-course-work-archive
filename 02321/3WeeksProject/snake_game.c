#include <stdio.h>
#define SCREEN_WIDTH 128 // The number of chars on the screen (48 is offscreen)
#define NUM_COLS 50      // The number of colums in the level (y coordinate)
#define NUM_ROWS 25      // The number of rows in the level (x coordinate)

// The different directions
#define NONE 0x0000u
#define RIGHT 0x0001u
#define LEFT 0x0002u
#define UP 0x0004u
#define DOWN 0x0008u

// Boolean macros
#define TRUE 1
#define FALSE 0

// Tile types
#define WALL 0x0005u
#define FOOD 0x0004u
#define EMPTY 0x0000u
#define SNAKE_HEAD 0x0006u
#define SNAKE_BODY 0x0002u
#define SNAKE_HEAD_DEAD 0x0003u

// Frequency of game update
#define BASE_FREQ 0x7fffu
#define FOOD_GEN_FREQ 0x000fu

// Various memory mappings
#define vid_mem_base (short *) 0xe000u
#define sw_reg (short *) 0xfe0au
#define btn_reg (short *) 0xfe0eu
#define sseg_reg (short *) 0xfe12u
#define led_reg (short *) 0xfe16u
#define uart_status_reg (short *) 0xfe00u
#define uart_data_reg (short *) 0xfe02u

typedef struct snake_body snake_body_t;
struct tile_t;

/* This is the snake model */
struct snake {
    short x; // Head X Coordinate
    short y; // Head Y Coordinate
    short direction; // Head Direction
    short alive;
    short score;
    short full; //Has the snake eaten anything this round?
    short length;
	short level;
};

typedef struct snake snake_t;

/* Model of a single tile*/
struct tile {
    struct tile *previous_tile_ptr; // Linked tile
    short *content_ptr; // Video memory pointer
};
typedef struct tile tile_t;


// Globals. Should really be local to wait() , but is here due to compiller
static unsigned short tick_lsb = 0;
static unsigned short tick_seed = 123;
static unsigned short food_gen = 0;
static unsigned short game_speed = BASE_FREQ;
static unsigned short high_score = 0;

/**
 * Empties the entire screen - obviously
 */
void clear_screen() {
    short row = 0;
    short colum = 0;
    while (row < 30) {
        colum = 0;
        while (colum < 80) {
            *(vid_mem_base + (SCREEN_WIDTH * row) + colum) = EMPTY;
            colum++;
        }
        row++;
    }
}

void update_high_score(short score) {
	short hex_to_dec_score = 0;
	
	high_score = score;
	
	if(high_score > 99) {
		short rem;
		rem = (high_score%100);
		hex_to_dec_score += (high_score/100)<<8;
		printf("high_score/100 = %d\n",hex_to_dec_score);
		hex_to_dec_score += (rem/10)<<4;
	}
	else if(high_score > 9)
		hex_to_dec_score += (high_score/10)<<4;
	hex_to_dec_score += high_score%10;
	
	*sseg_reg = hex_to_dec_score;
	
}

/**
 * Sets the value of a specific tile
 * @param x The colum index
 * @param y The row index
 * @param c Rom index - what symbol to place
 */
void set_tile(short x, short y, short c) {
    *(vid_mem_base + (SCREEN_WIDTH * y) + x) = c;
}

/**
 * Sends the hexadecial score to STDOUT. No protocol is specified
 * @param snake The snake with the score to send
 */
void send_score(snake_t *snake) {
    printf("%x\n", snake->score);
}

#ifdef USE_DELAY
/**
 * Wait primitive that occupies the CPU for a fixed number of cycles.
 * It should be converted to assembler for better timing control
 */
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
#endif

/**
 * Function for doing the snake initialization. Used upon new game start
 * @param snake The snake to initialize
 * @param lvl The current level map
 */
void initialize_snake(snake_t *snake, tile_t lvl[NUM_ROWS][NUM_COLS]) {
    short i;
    tile_t *last_tile;
    snake->score = 0;
    snake->x = 5;
    snake->y = 5;
    snake->direction = RIGHT;
    snake->full = FALSE;
    snake->length = 3;
    snake->alive = TRUE;
	snake->level = 1;

    // Draw the snake head
    *((lvl[snake->y][snake->x]).content_ptr) = SNAKE_HEAD;

    last_tile = &(lvl[snake->y][snake->x]);

    // Generate initial snake, and link it
    for (i = 1; i < snake->length; i++) {
        (lvl[(snake->y)][(snake->x) - i]).previous_tile_ptr = last_tile;
        last_tile = &(lvl[(snake->y)][(snake->x) - i]);
    }
}

/**
 * Updates the score display
 * @param snake The snake
 */
void update_hud(snake_t *snake) {

	set_tile(NUM_COLS + 3, 1, 'S');
	set_tile(NUM_COLS + 4, 1, 'c');
	set_tile(NUM_COLS + 5, 1, 'o');
	set_tile(NUM_COLS + 6, 1, 'r');
	set_tile(NUM_COLS + 7, 1, 'e');
	set_tile(NUM_COLS + 8, 1, ':');
	set_tile(NUM_COLS + 9, 1, ' ');
    // The score
	
	if(snake->score > 9) {
		set_tile(NUM_COLS + 10, 1, ((snake->score)/10) + 0x0030u);
		set_tile(NUM_COLS + 11, 1, ((snake->score)%10) + 0x0030u);
	}
	else
		set_tile(NUM_COLS + 10, 1, ((snake->score) + 0x0030u));
	
	set_tile(NUM_COLS + 2, 3, 'L');
	set_tile(NUM_COLS + 3, 3, 'e');
	set_tile(NUM_COLS + 4, 3, 'n');
	set_tile(NUM_COLS + 5, 3, 'g');
	set_tile(NUM_COLS + 6, 3, 't');
	set_tile(NUM_COLS + 7, 3, 'h');
	set_tile(NUM_COLS + 8, 3, ':');
	set_tile(NUM_COLS + 9, 3, ' ');
	
	if(snake->length > 9) {
		set_tile(NUM_COLS + 10, 3, ((snake->length)/10) + 0x0030u);
		set_tile(NUM_COLS + 11, 3, ((snake->length)%10) + 0x0030u);
	} else
		set_tile(NUM_COLS + 10, 3, (snake->length) + 0x0030u);


	set_tile(NUM_COLS + 3, 5, 'L');
	set_tile(NUM_COLS + 4, 5, 'e');
	set_tile(NUM_COLS + 5, 5, 'v');
	set_tile(NUM_COLS + 6, 5, 'e');
	set_tile(NUM_COLS + 7, 5, 'l');
	set_tile(NUM_COLS + 8, 5, ':');
	set_tile(NUM_COLS + 9, 5, ' ');
	
	if(snake->level > 9) {
		set_tile(NUM_COLS + 10, 5, ((snake->level)/10) + 0x0030u);
		set_tile(NUM_COLS + 11, 5, ((snake->level)%10) + 0x0030u);
	} else
		set_tile(NUM_COLS + 10, 5, (snake->level) + 0x0030u);		
		
}

/**
 * Randomly place food elements.
 *
 * Well, actually not random, since we do not have a seed we just place them
 * at fixed locations
 * @param lvl
 */
void place_food(tile_t lvl[NUM_ROWS][NUM_COLS]) {
	unsigned short row, colum;

	do {
		row = (rand(NUM_ROWS-2))+1;
		colum = (rand(NUM_COLS-2))+1;
		}
	while(*(lvl[row][colum].content_ptr) != EMPTY);
	
	*(lvl[row][colum].content_ptr) = FOOD;
}

int remove_sign(a)
{
	return a& (0x7FFF);
}


unsigned short rand(short limit)
{
	unsigned short i;
	tick_seed = tick_seed * 0x000du + 0x0007u;
	i = (remove_sign(tick_seed/0x003fu)) % limit;
//	printf("rand(): after limit: %d, tick_seed : %d i: %d; mod: %d\n", limit,tick_seed, i, i % limit);
	return i;
}

/**
 * This is the main game logic
 * @param snake The snake to be updated
 * @param lvl The current level map
 */
void update_game_state(snake_t *snake, tile_t lvl[NUM_ROWS][NUM_COLS]) {
    short i;
    tile_t *new_tail, *head_tile = &(lvl[snake->y][snake->x]);

	// Snake level, minimum 1
	snake->level = (snake->length / 10)+1;
	
	//Update game speed
	game_speed = BASE_FREQ - (snake->length*0x0100u);
	
	//new food generation
	food_gen++;
	if(food_gen == FOOD_GEN_FREQ) {
		food_gen = 0;
		place_food(lvl);
	}
		
	
    // Set the new coordinate of the snake
    if (snake->direction == RIGHT) {
        snake->x++;
    } else if (snake->direction == LEFT) {
        snake->x--;
    } else if (snake->direction == UP) {
        snake->y--;
    } else if (snake->direction == DOWN) {
        snake->y++;
    }

    // Game logic, kills worm if it does an illegal move
    if (*((lvl[snake->y][snake->x]).content_ptr) == WALL) {
        snake->alive = FALSE;
    } else if (*((lvl[snake->y][snake->x]).content_ptr) == SNAKE_BODY) {
        snake->alive = FALSE;
    } else if (*((lvl[snake->y][snake->x]).content_ptr) == FOOD) {
        snake->length++;
        snake->score++;
    }


    // Link the previous and the current element together
    lvl[snake->y][snake->x].previous_tile_ptr = head_tile;

    // Find the tail
    for (i = 0; i < snake->length; i++) {
        // Last tile reached
        if (head_tile->previous_tile_ptr != '\0') {
            *(head_tile->content_ptr) = SNAKE_BODY;
            head_tile = head_tile->previous_tile_ptr;

            // In case nothing is eaten in the game loop, we need this
            if (head_tile->previous_tile_ptr->previous_tile_ptr == '\0')
                new_tail = head_tile->previous_tile_ptr->previous_tile_ptr;
        }
    }

	/*
    // The snake has eaten something, skip tail update
    if (snake->full) {
        snake->full = FALSE;
    }*/
        // The snake has not eaten anything, update tail
  //  else {
        // Remove the reference to the next element, making this the new tail
        new_tail->previous_tile_ptr = '\0';
        *(head_tile->content_ptr) = EMPTY;
   // }

    // Graphic update
    if (!snake->alive) {
        *((lvl[snake->y][snake->x]).content_ptr) = SNAKE_HEAD_DEAD;
        // Send the score
        send_score(snake);
    } else {
        *((lvl[snake->y][snake->x]).content_ptr) = SNAKE_HEAD + snake->direction;
    }
    update_hud(snake);

}

/**
 * Debug method for outputting the level model to STDOUT
 * @param lvl The current level map
 */
void dump_level(tile_t lvl[NUM_ROWS][NUM_COLS]) {
    short row, col;
    for (row = 0; row < NUM_ROWS; row++) {
        for (col = 0; col < NUM_COLS; col++) {
            printf("%d ", *(lvl[row][col].content_ptr));
        }
        printf("\n");
    }
}

/**
 * Initializes the level map. Puts wall around the level and links the video
 * memory to the level map
 * @param lvl The current level map
 */
void init_level(tile_t lvl[NUM_ROWS][NUM_COLS]) {
    int row, col;
    for (row = 0; row < NUM_ROWS; row++) {
        for (col = 0; col < NUM_COLS; col++) {
            //initialize video memory pointer
            lvl[row][col].content_ptr = (vid_mem_base + (SCREEN_WIDTH * row) + col);
            // Outer walls
            if (col == NUM_COLS - 1 || col == 0 || row == NUM_ROWS - 1 || row == 0) {
                *(lvl[row][col].content_ptr) = WALL;
            } else {
                *(lvl[row][col].content_ptr) = EMPTY;
            }
        }
        lvl[row][col].previous_tile_ptr = '\0';
    }
	//draw_snake_logo();
}

short tick () {
  tick_lsb++;
 // if(tick_lsb == 0xffffu) {
  if(tick_lsb == game_speed) {
      tick_lsb = 0;
	  return 1;
  }
  return 0;
}

draw_snake_logo() {
	set_tile(5, NUM_ROWS+2, 'S');
	set_tile(6, NUM_ROWS+2, 'n');
	set_tile(7, NUM_ROWS+2, 'a');
	set_tile(8, NUM_ROWS+2, 'k');
	set_tile(9, NUM_ROWS+2, 'e');
//	set_tile(NUM_ROWS + 3, 10, 'S');
//	set_tile(NUM_ROWS + 3, 11, 'S');
}


/**
 * Main function. Initialization, Input control, game update loop and game state
 * control
 */
void main() {
    snake_t snake;
    tile_t level[NUM_ROWS][NUM_COLS];
	short keypressed = FALSE;
	volatile short new_direction;
    // Initialize
    clear_screen();
    init_level(level);
    initialize_snake(&snake, level);
    place_food(level);
	
    // Main game loop
    while (1) {

        if (*btn_reg ) {
			
			if(*btn_reg == LEFT) {
				snake.direction = LEFT;
			}
			if(*btn_reg == RIGHT ) {
				snake.direction = RIGHT;
			}
			if(*btn_reg == UP) {
				snake.direction = UP;
			}
			if(*btn_reg == DOWN) {
				snake.direction = DOWN;
			}
        }

        if (*uart_status_reg) {
			new_direction = *uart_data_reg;
			if(new_direction == 'a' || new_direction == 0x4b) {
				snake.direction = LEFT;
			}
			if(new_direction == 'd' || new_direction == 0x4d) {
				snake.direction = RIGHT;
			}
			if(new_direction == 'w' || new_direction == 0x48) {
				snake.direction = UP;
			}
			if(new_direction == 's' || new_direction == 0x50) {
				snake.direction = DOWN;
			}
        }
        // This is the actual game
		if(tick()) {
			update_game_state(&snake, level);
		}

        //wait();

        //Restart game if the snake is dead
        if (!snake.alive) {
			if(snake.score > high_score)
				update_high_score(snake.score);
            clear_screen();
            init_level(level);
            place_food(level);
            initialize_snake(&snake, level);
        }
    }
}
