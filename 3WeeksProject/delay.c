#include <stdio.h>

void main() {
  unsigned short lsb = 0;
  unsigned short msb = 0;


  while (1) {
  while (msb != 0x05f5u) {
    lsb = 0;
    while (lsb != 0xffffu) {
      lsb++;

    }
    msb++;
  }
  printf("%x%x \n",msb,lsb);

  msb=0;
  }
}

  
