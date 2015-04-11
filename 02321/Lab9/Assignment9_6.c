#include <stdio.h>
//#include <math.h>

int main () {
  int minval;
  int maxval;
  
  printf("Input minimum value: ");
  scanf("%d",&minval);
  printf("Input maximum value: ");
  scanf("%d",&maxval);
  
  int i;
  for(i = minval; i <= maxval; i++ ) {
    if(isPrime(i)) {
      printf("%d\n",i);
    }
  }
}

int isPrime (int number) {
  int found;
  int n;
  found = 0;
  n = 2; // Starting number
  while(n < number) {
    if(number % n) {
      n++;      
    }
    else 
      return 0;
  }

  return 1;
}
