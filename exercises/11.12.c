#include <stdio.h>
#define LETTER '1'
#define ZERO 0
#define NUMBER 123

int main() {
  printf("char value of 'a': %c\n",'a');
  printf("hex value of 12288: x%x\n",12288);
  printf("$%d.%c%d\n",NUMBER,LETTER,ZERO);
  
}
