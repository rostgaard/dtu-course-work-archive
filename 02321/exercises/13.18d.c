#include <stdio.h>

int main () {
  int x = 0;
  int i;

  while (x > 10 ) {
    for(i = 0; i < x; i = x + 1)
      printf("*");
    x = x + 1;
  }
}
