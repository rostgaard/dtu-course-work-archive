#include <stdio.h>

int main() {
  int x;
  x=0;
  
  if(x = 0) 
    printf("x equals 0\n");
  else
    printf("x does not equal 0 (%d)\n",(x=0));
}
