#include <stdio.h>
#define STOP 0


int main() {
    int counter;
    int startpoint;

    printf("=====Countdown program=====");    
    printf("Enter a positive integer: ");
    
    scanf("%d",&startpoint);

    for(counter = startpoint; counter >= STOP; counter--) {
      printf("counter: %d\n",counter);
	printf("counter + 'A': %d\n",counter + 'A');
	printf("counter, startpoint + counter: %d,%d\n",counter, startpoint + counter);
	printf("counter hex: %x\n",counter);
    }   
}   
