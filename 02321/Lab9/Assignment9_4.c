#include <stdio.h>

#define LAST_CHAR 127  /* The last printable char*/
#define CHAR_LIMIT 10

int main () {
  int cur_char = 32; /* First printable character*/
  int char_limit_offset = (cur_char % CHAR_LIMIT)-1; /* Normalize offset*/
  while (cur_char < LAST_CHAR) {
    printf("%d: %c",cur_char,cur_char);
    if((cur_char - char_limit_offset) % CHAR_LIMIT)
      printf("\t");
    else
      printf("\n"); 
    cur_char++;
  }
  printf("\n"); 
}
