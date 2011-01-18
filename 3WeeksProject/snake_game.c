#include <stdio.h>
// The number of chars on the screen (48 offscreen)
#define SCREEN_WIDTH 128
#define NUM_COLS 20
#define NUM_ROWS 10

//Macro Direction
#define NONE 0x0000u
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


typedef struct snake_body snake_body_t;

struct snake {
    short x; // Stores Head X Coordinate
    short y; // Stores Head Y Coordinate
    short direction; // Stores Head Direction
    short alive; // Alive value 1, dead value 0
    short score;
    short full; //Has the snake eaten anything?
    short length;
}; // Declares a variable of the structure

typedef struct snake snake_t;

struct tile_t;

struct tile {
    short content;
    //struct tile *next;
    short previous_tile;
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

void initialize_snake(snake_t *snake) {
    //Snake->length = 1;
    snake->score = 0;
    snake->x = 5;
    snake->y = 5;
    snake->direction = RIGHT;
    snake->full = FALSE;
    snake->length = 3;
    snake->alive = TRUE;
}

void update_hud(snake_t *snake) {
    symbol_at(NUM_ROWS + 2, 1, (snake->score) + 0x0030u);
}

void place_food(tile_t lvl[NUM_ROWS][NUM_COLS]) {
    if (lvl[2][5].content == EMPTY) {
        lvl[2][5].content = FOOD;
        symbol_at(2, 5, lvl[2][5].content);
    } else if (lvl[7][5].content == EMPTY) {
        lvl[7][5].content = FOOD;
        symbol_at(7, 5, lvl[7][5].content);
    }
}

/**
 *
 * @param snake The snake to be updated
 */
void update_snake(snake_t *snake, tile_t lvl[NUM_ROWS][NUM_COLS]) {
    tile_t *head_tile = &(lvl[snake->y][snake->x]);

    if (snake->direction == RIGHT) {
        if (snake->x == NUM_COLS - 2)
            snake->x = 1;
        else
            snake->x++;

        lvl[snake->y][snake->x].previous_tile = LEFT;

    } else if (snake->direction == LEFT) {
        if (snake->x == 1)
            snake->x = NUM_COLS - 2;
        else
            snake->x--;

        lvl[snake->y][snake->x].previous_tile = RIGHT;

    } else if (snake->direction == UP) {
        if (snake->y == 1)
            snake->y = NUM_ROWS - 2;
        else
            snake->y--;

        lvl[snake->y][snake->x].previous_tile = DOWN;

    } else if (snake->direction == DOWN) {
        if (snake->y == NUM_ROWS - 2)
            snake->y = 2;
        else
            snake->y++;

        lvl[snake->y][snake->x].previous_tile = UP;
    }
    // Find the tail
    int i;
    for (i = 1; i <= snake->length; i++) {
        // Last tile reached
        if (head_tile->previous_tile != NONE) {
            head_tile->content = SNAKE_BODY;
            if (head_tile->previous_tile == UP) {
                head_tile = &(lvl[snake->y + i][snake->x]);
            }
            if (head_tile->previous_tile == DOWN) {
                head_tile = &(lvl[snake->y - i][snake->x]);
            }
            if (head_tile->previous_tile == LEFT) {
                head_tile = &(lvl[snake->y][snake->x - i]);
            }
            if (head_tile->previous_tile == RIGHT) {
                head_tile = &(lvl[snake->y + i][snake->x + i]);
            }
        }
    }
    if (snake->full) {
        snake->full = FALSE;
        snake->length++;
    } else
        head_tile->content = EMPTY;



    lvl[snake->y][snake->x].content = SNAKE_HEAD;



}

void dump_level(tile_t lvl[NUM_ROWS][NUM_COLS]) {
    int row, col;
    for (row = 0; row < NUM_ROWS; row++) {
        for (col = 0; col < NUM_COLS; col++) {
            printf("%d ", lvl[row][col].content);
        }
        printf("\n");
    }
}

void init_level(tile_t lvl[NUM_ROWS][NUM_COLS]) {
    int row, col;
    for (row = 0; row < NUM_ROWS; row++) {
        for (col = 0; col < NUM_COLS; col++) {
            // Outer walls
            if (col == NUM_COLS - 1 || col == 0 || row == NUM_ROWS - 1 || row == 0)
                lvl[row][col].content = WALL;
            else
                lvl[row][col].content = EMPTY;
        }
        lvl[row][col].previous_tile = NONE;
    }
}

void main() {
    snake_t snake;
    tile_t level[NUM_ROWS][NUM_COLS];
    int rng = 0;


    init_level(level);

    initialize_snake(&snake);

    while (1) {
        rng++;
        if (!(rng % 3)) {
            rng = 0;
            snake.full = TRUE;
        }


        //If nothing is eaten, move the old tail
        update_snake(&snake, level);

        dump_level(level);
        sleep(1);

    }


}

/*
void main() {
    snake_t snake;
    tile_t tile_array[NUM_ROWS][NUM_COLS];
    short old_x;
    short old_y;

    initialize_snake(&snake);
    clear_screen();


    InitialiseArrayWithWall(tile_array);
    place_food(tile_array);

    PrintArray(tile_array);

    //Game update loop
    while (1) {
 *sseg_reg = snake.direction;

        old_x = snake.x;
        old_y = snake.y;

        // This sets the new direction from the btn's
        if (*btn_reg)
            snake.direction = *btn_reg;

        // Calculate the new snake head location
        update_snake(&snake);

        //Game logic
        if (tile_array[snake.x][snake.y].content == FOOD) {
            snake.score++;
            place_food(tile_array); // Generate a new food
            if (tile_array[snake.x][snake.y].next == '\0') {
                tile_array[snake.x][snake.y].next = &tile_array[old_x][old_y];
                tile_array[old_x][old_y].content = SNAKE_BODY;
            }
        } else {
            while (tile_array[snake.x][snake.y].next != '\0') {
                tile_array[old_x][old_y].content = SNAKE_BODY;
            }
            // Delete old tile
            tile_array[old_x][old_y].content = EMPTY;
        }

        // Place the new head and draw it
        tile_array[snake.x][snake.y].content = SNAKE_HEAD;
        symbol_at(snake.x, snake.y, SNAKE_HEAD);

        // Update the heads up display
        update_hud(&snake);
        // Game delay
        //wait();
        sleep(2);
    }
}
 */