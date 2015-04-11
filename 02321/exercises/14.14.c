int main() {
  int number = 10; // playing it very safe starting with 10
  int found = 0;

  /* This assumes that we eventually find a number within integer 
     range. Alternatively add an && number != integer_last */
  while(!found) {
    number++;
    int i = 1;
    while(i < 10) {
      if(is_evenly_divisable(number,i)) {
	if(i == 9)
	  found = 1;
	i++;
      }
      /*  If the number is not evenly divisable by any number in 
	  range, it must be skipped all together  */
      else
	break; 
    }
  }
  printf("Smallest number divisable by all numbers 1-9 is %d\n",
	 number);
}

/* Function for determining divisability of two numbers. returns 1 on
   success. 0 otherwise.*/
int is_evenly_divisable(int A, int B) {
  return (!(A % B));
}


