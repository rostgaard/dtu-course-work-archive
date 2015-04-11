#include <stdio.h>

int main() {
  int x = 20;
  int y = 10;
  
  while( (x > 10) && (y & 15)) {
    y = y+1;
    x = x-1;
    printf("*");
  }
}
