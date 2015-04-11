/*
 ============================================================================
 Name        : SnakeBodyTest.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

#define SNAKE_MAX 10

int main(void) {

	snake body_list[SNAKE_MAX];

	for(i = 0; i < SNAKE_MAX; i++)//Initialise array
	{
		body_list[i]={{-1,-1}};
	}
	return EXIT_SUCCESS;
}

void AddBody(int body_list[SNAKE_MAX],x,y)
{
	int i;
	struct snake_body_t new_body;
	for(i = 0; i < snake_max_lenth; i++)
	{
		body = body_list[i]
		new_body.x=x;//init snake with coordinates
		new_body.y=y;
		if(body.x == -1)//tjek if current snake body struct is free
		{
			body_list[i]= new_snake;
			i=snake_max_lenth;//noMoreLoop
		}
	}
}

//Still working on this one..
void UpdateSnake(int bodyList[snakeMaxLenth])
{
	int x;
	struct snake_body_t last_body;
	for(x = 0; x < snakeMaxLenth;x++)
	{
		last_body = bodyList[x]
		if(bodyList[x] != 0)
		{
			bodyList[x] =
			bodyList[x]	= 1;//SnakeBody
		}
	}
}

