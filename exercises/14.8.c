int main() {
  int x = 1;
  int y = 2;

  Swap(x,y);

  printf("x = %d  y = %d", x,y);
}

void Swap(int y, int x) {
  int temp;

  temp = x;
  x = y;
  y = temp;
}


