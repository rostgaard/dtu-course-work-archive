#include <stdio.h>

int main() {
    char counter;
    char startpoint;

    printf("=====Countdown program, char edition=====\n");    
    printf("Enter a character: ");
    
    scanf("%c",&startpoint);

    for(counter = startpoint; counter >= '!' ; counter--) {
      printf("char #%d: %c\n",counter,counter);
    }   
}   
