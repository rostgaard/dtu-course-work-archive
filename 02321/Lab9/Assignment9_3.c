#include <stdio.h>

int main () {
  int cur_char = 65;
  
  printf("No loops: ");
  printf("%c",cur_char++);
  printf("%c",cur_char++);
  printf("%c",cur_char++);
  printf("%c",cur_char++);
  printf("%c",cur_char++);
  printf("%c",cur_char++);
  printf("%c",cur_char++);
  printf("%c",cur_char++);
  printf("%c",cur_char++);
  printf("%c\n",cur_char++);

  cur_char = 65;
  printf("While loop: ");

  while(cur_char < 75) {
    printf("%c",cur_char++);    
  }
  
  cur_char = 65;
  printf("\nFor loop: ");
  int i;
  for (i = 0; i < 10; i++) {
    printf("%c",cur_char++);        
  }


  cur_char = 65;
  printf("\nDo-while loop: ");
  
  do
    printf("%c",cur_char++);
  while(cur_char < 75);
  
  printf("\n");


}
