#include "kernel.h"

struct screen* const
screen_pointer = (struct screen*) 0xB8000;

/*! Clears the screen. */
void clear_screen(void)
{
 register int row, column;

 for(row=0; row<MAX_ROWS; row++)
 {
  for(column=0; column<MAX_COLS; column++)
  {
   screen_pointer->positions[row][column].character=' ';
  }
 }
}

/* Modify these function in task B7. */

void
kprints(const char* string)
{
 /* Loop until we have found the null character. */
 while(1)
 {
  register const char curr = *string++;

  if (curr)
  {
   outb(0xe9, curr);
  }
  else
  {
   return;
  }
 }
}

void
kprinthex(const register long value)
{
 const static char hex_helper[16]="0123456789abcdef";
 register int      i;

 /* Print each character of the hexadecimal number. This is a very inefficient
    way of printing hexadecimal numbers. It is, however, very compact in terms
    of the number of source code lines. */
 for(i=15; i>=0; i--)
 {
  outb(0xe9, hex_helper[(value>>(i*4))&15]);
 }
}
