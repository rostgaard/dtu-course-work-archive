#include <stdio.h>

void main() {
  unsigned short lsb = 0;
  unsigned short msb = 0;
  int counter = 0;

  while (1) {
  while (msb != 0x0005u) {

    lsb = 0;
    while (lsb != 0xffffu) {
      counter++;
      lsb++;
    }
    msb++;
  }
  printf("%x%x counter:%d\n",msb,lsb, counter);

  msb=0;
  }
}

  


short delay () {
  lsb++;
  if(lsb == 0xffffu) {
      msb++;
      lsb = 0;
  }
  if(msb == 0x0005u ) {
    msb = 0;
    return 1;
  }
  return 0;
}

void main() {
  int counter = 0;
  
  while(1) {
  if(delay())
    printf("%x%x counter:%d\n",msb,lsb,counter);
  counter++;
  }
