/*
 ============================================================================
Fields
0:Empty
1:Snake
2:Wall
3:Food
 ============================================================================
 */
#include <stdio.h>
#include <stdlib.h>

//Macros Tile Area
#define Highty 20
#define Widthx 40
//Macro Direction
#define LEFT 1
#define RIGHT 2
#define UP 3
#define DOWN 4
//Macro Boolean
#define TRUE 1
#define FALSE 0

//Pre Init Functions
void initgamedata();
void PrintArray(int MainArray[Highty][Widthx]);
void InitialiseArray(int MainArray[Highty][Widthx]);
int RandomPositionY();
int RandomPositionX();

int main(void)
{
	int TiltleArray[Highty][Widthx];
	InitialiseArrayWithWall(TiltleArray);
	placeFood(TiltleArray);
	PrintArray(TiltleArray);
	//printf("\n");
	getch();
	return 1;
}

//Game Data
int score; //Keeps the count of game score
int gameDelay;//Lower the game delay faster is the game speed.

//Timers
int HwTimer = 4132;

//The Snake Head & Tail
struct Snake_Data
{
int length;		// The Snake lenth
int head_x; 	// Stores Head X Coordinate
int head_y; 	// Stores Head Y Coordinate
int head_dir; 	// Stores Head Direction
int alive;		// Alive value 1, dead value 0
//int tail_x; 	// Stores Tail X Coordinat
//int tail_y; 	// Stores Tail Y Coordinat
//int tail_dir; // Stores Tail Direction
} Snake; 		// Declares a variable of the structure
typedef struct Snake Snake_Data;

//Snake Body
struct Snake_Body_Data
{
	int x;
	int y;
	struct Snake_Body *nextElement;
};
typedef struct Snake_Body Snake_Body_Data;

//Update Snake direction
/*
void update_snake(Snake_Data *Snake)
{
	Snake_Data last_coordinates = Snake->head;
	switch(snake->direction)
	{
	  case UP:
		Snake->head_y->y--;
	    break;
	  case DOWN:
		Snake->head_y->y++;
	    break;
	  case RIGHT:
		Snake->head_x->x++;
	    break;
	  case LEFT:
		Snake->head_x->x--;
	    break;
	  }
}
*/

PaintSnake(Snake *snake)
{
	//symbol_at(x,y,char)
}

void change_direction (Snake_Data *snake, int direction)
{
	//Snake->direction = direction;
}

void kill_snake(Snake_Data *Snake)
{
	//Snake->alive = FALSE;
}

//Snakes start coordinate & InitGame
void initgamedata()
{
   Snake.length = 1;
   Snake.head_x = 5;
   Snake.head_y = 5;
   Snake.head_dir = RIGHT;
   //Snake.tail_x = Snake.head_x- Snake.length;
   //Snake.tail_y = Snake.head_y;
   //Snake.tail_dir = RIGHT;
   Snake.alive = TRUE;
   score = 0;
   gameDelay = 1000; // Should be change according to game speed need.
}

void PrintArray(int Array[Highty][Widthx])
{
	int x,y;
	for(x = 0; x <= Highty;x++)//Maby "=" is wrong syntax...
	{
		for(y = 0; y <= Widthx;y++)//Maby "=" is wrong syntax...
		{
			int value = Array[x][y];
			printf("%d",value);
		}
	printf("\n");
	}
}

void InitialiseArrayWithWall(int Array[Highty][Widthx])
{
	int x,y;
	for(x = 0; x <= Highty;x++)//Maby "=" is wrong syntax...
	{
		for(y = 0; y <= Widthx;y++)//Maby "=" is wrong syntax...
		{
			if(x==0||x==Highty||y==0||y==Widthx)
			{
				Array[x][y] = 2;
			}
			else
			{
				Array[x][y] = 0;
			}
		}
	}
}

void placeFood(int Array[Highty][Widthx])
{
	int x,y,x_temp,done;
	int y_temp = Widthx, tileValue = Highty;
	do {
		y = RandomPositionY();
		x = RandomPositionX();
		tileValue = Array[x][y];
	      if (tileValue == 0)
	      {
	    	  Array[x][y] = 3;
	    	  done = 1;
	      }
	  } while ( done = 0 );
}

int RandomPositionY()
{
	//int a = HwTimer; // seed value from HardWareTimer
	//a = (a * 32719 + 3) % 32749;
	//printf("RandomY %d\n",a);
	//return ((a % Widthx) + 1);

    //static long a = 100001;
    //a = (a * 125) % 2796203;
    //printf("RandomY %d\n",((a % HightY) + 1));
    //return ((a % Widthx) + 1);
	return(8);
}

int RandomPositionX()
{
	//int a = HwTimer;  // could be made the seed value
	//a = (a * 32719 + 3) % 32749;
	//printf("RandomX %d\n",a);
	//return ((a % Highty) + 1);

	//unsigned int m_z, m_w;
    //m_z = 36969 * (m_z & 65535) + (m_z >> 16);
    //m_w = 18000 * (m_w & 65535) + (m_w >> 16);
	//printf("seed:%d\n",(m_z << 16) + (m_w & 65535));
    //return((m_z << 16) + (m_w & 65535));

    //a = (a + 1.0) * 2.328306435454494e-10;
    //a = (((a * 214013L + 2531011L) >> 16) & 32767);
    //printf("RandomX %d\n",((a % Highty) + 1));
    //return ((a % Highty) + 1);

    return(8);

}


