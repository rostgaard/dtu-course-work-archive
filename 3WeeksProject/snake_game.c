#include <stdio.h>
#define SCREEN_WIDTH 128 // The number of chars on the screen (48 is offscreen)
#define NUM_COLS 40      // The number of colums in the level (y coordinate)
#define NUM_ROWS 20      // The number of rows in the level (x coordinate)

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

// Various memory mappings
#define vid_mem_base (short *) 0xe000u
#define sw_reg (short *) 0xfe0au
#define btn_reg (short *) 0xfe0eu
#define sseg_reg (short *) 0xfe12u
#define led_reg (short *) 0xfe16u

typedef struct snake_body snake_body_t;
struct tile_t;

/* This is the snake model */
struct snake {
    short x; // Head X Coordinate
    short y; // Head Y Coordinate
    short direction; // Head Direction
    short alive;
    short score;
    short full; //Has the snake eaten anything?
    short length;
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
static unsigned short tick_msb = 0;

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
    set_tile(NUM_COLS + 2, 1, (snake->score) + 0x0030u);
}

/**
 * Randomly place food elements.
 *
 * Well, actually not random, since we do not have a seed we just place them
 * at fixed locations
 * @param lvl
 */
void place_food(tile_t lvl[NUM_ROWS][NUM_COLS]) {
    if (*(lvl[11][3].content_ptr) == EMPTY) {
        *(lvl[11][3].content_ptr) = FOOD;
    } else if (*(lvl[10][15].content_ptr) == EMPTY) {
        *(lvl[10][15].content_ptr) = FOOD;
    }
}

/**
 * This is the main game logic
 * @param snake The snake to be updated
 * @param lvl The current level map
 */
void update_game_state(snake_t *snake, tile_t lvl[NUM_ROWS][NUM_COLS]) {
    short i;
    tile_t *new_tail, *head_tile = &(lvl[snake->y][snake->x]);

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
        place_food(lvl);
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

    // The snake has eaten something, skip tail update
    if (snake->full) {
        snake->full = FALSE;
        snake->length++;
        place_food(lvl);
    }
        // The snake has not eaten anything, update tail
    else {
        // Remove the reference to the next element, making this the new tail
        new_tail->previous_tile_ptr = '\0';
        *(head_tile->content_ptr) = EMPTY;
    }

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
}

/**
 * Main function. Initialization, Input control, game update loop and game state
 * control
 */
void main() {
    snake_t snake;
    tile_t level[NUM_ROWS][NUM_COLS];

    // Initialize
    clear_screen();
    init_level(level);
    initialize_snake(&snake, level);
    place_food(level);

    // Main game loop
    while (1) {
        if (*btn_reg) {
            snake.direction = *btn_reg;
        }

        // This is the actual game
        update_game_state(&snake, level);

        wait();

        //Restart game if the snake is dead
        if (!snake.alive) {
            clear_screen();
            init_level(level);
            place_food(level);
            initialize_snake(&snake, level);
        }
    }
}