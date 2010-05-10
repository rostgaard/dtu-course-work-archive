#include "kernel.h"

struct screen * const
        screen_pointer = (struct screen*) 0xB8000;

/* Global Row / Column */
int current_row = 0;
int current_col = 0;

/*! Clears the screen. */
void clear_screen(void) {
    register int row, column;

    for (row = 0; row < MAX_ROWS; row++) {
        for (column = 0; column < MAX_COLS; column++) {
            screen_pointer->positions[row][column].character = ' ';
        }
    }
}

/* Modify these function in task B7. */

void
kprints(const char* string) {
    /* Loop until we have found the null character. */

    //register int row=0, column=0;				// Rows and Columns to write on

    while (1) {

        register const char current_char = *string++; // Current Character from String Pointer

        if (current_char) {

            if (current_char == '\n' || current_col == MAX_COLS) {
                // New Line
                current_row++;
                current_col = 0;
            }
            if (current_row == MAX_ROWS) {
                current_row = 0;
            }

            if (current_char != '\n') {
                //screen_pointer->positions[current_row][current_col].character = current_char;
                print_char(current_char, current_col, current_row);
                current_col++;
            }


        } else {
            return;
        }
    }
}

void
kprinthex(const register long value) {
    const static char hex_helper[16] = "0123456789abcdef";
    register int i;

    /* empty print buffer, explicitly defining termination character. */

    //TODO Try to find a better solution, this approach eliminates a lot of cursor
    //     position calculations though.
    char tmp_string[17] = "                \0";


    /* Print each character of the hexadecimal number. This is a very inefficient
       way of printing hexadecimal numbers. It is, however, very compact in terms
       of the number of source code lines. */
    for (i = 15; i >= 0; i--) {
        tmp_string[15 - i] = hex_helper[(value >> (i * 4))&15];
    }
    kprints(tmp_string);
}

void print_char(char c, int x, int y) {
    screen_pointer->positions[y][x].character = c;
}