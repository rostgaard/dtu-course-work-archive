#include <stdio.h>

short next = 123;

short rand(short limit)
{
	short i;
	next = next * 13 + 7;
	i = (next/63) % limit;
	//printf("after limit: %d, next : %d i: %d; mod: %d\n", limit, next, i, i % limit);
	return i;
}


int main(void) 
{
	short limit = 80;
	short result = rand(limit);
	printf("Random value: %d", result);
	return 0;
}
