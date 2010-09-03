#include <stdio.h>

int main () {
  char input;
  
  while(input != 'q') {
    input = getchar();
    printf("%c",input);
    printf("%c",(input+0x20));
  }
}
