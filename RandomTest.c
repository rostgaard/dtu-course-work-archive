#include <stdio.h>

short next = 12323;

int main(void) {

	//srand(12323);
	rand();
	printf("Random value: %d",next);
	return 0;
}

short rand(void)
{
	next = next * 11034 + 135;
	return (short)(next/636)%328;
}

void srand(short seed)
{
	next = seed;
}
