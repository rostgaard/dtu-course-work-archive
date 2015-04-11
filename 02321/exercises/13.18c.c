#include <stdio.h>

int main() {
  int x;
  
  for(x = 0; x < 10 ; x = x + 1) {
    if(x % 2)
      printf("*%d",x);
  }
}
