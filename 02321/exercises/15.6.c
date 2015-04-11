#include <stdio.h>

int Mystery(int a, int b, int c);
void varState(int i, int j, int k);

int main() {
  int i;
  int j;
  int k;
  int t;

  int sum = 0;

  for (i = 100; i > 0; i--) {
      for(j = 1; j < i; j++) {
	for(k=j; k < 100; k++) {
	  t=Mystery(i,j,k);
	  sum = sum +t;
	  if(t == 0) {
	    varState(i,j,k);
	  }
	}
      }
  }
  printf("Sum is %d\n",sum);
}

void varState(int i, int j, int k) {
  printf("a:%d, b:%d, c:%d\n",i,j,k);
}



int Mystery(int a, int b, int c) {
  static max = 1000;
  int out;
  
  out = 3*a*a + 7*a - 5*b*b + 4*b + 5*c;
  
  return out;
}
