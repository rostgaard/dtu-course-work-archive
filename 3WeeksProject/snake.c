#define SNAKE_HEAD 0
#define SNAKE_BODY 1
#define SNAKE_TAIL 2

#define NORTH 0
#define SOUTH 1
#define EAST  2
#define WEST  3


struct snake_body {
  int x;
  int y;
  struct *next;
}

typedef struct snake_body_t snake_body;


struct snake {
  int *head; 
  int direction;
  int full;
  int points;
  bool alive = true;
  int y;
};
typedef struct snake snake_t;

void kill_worm(snake_t *snake) {
  snake->alive = false;
}

void update_snake(snake_t *snake, level_t lvl) {
  // update the head of the snake
  snake_body_t last_coordinates = snake->head;

  switch(snake->direction) {
  case NORTH:
    worm->head->y--; 
    break;
  case SOUTH:
    worm->head->y++; 
    break;
  case EAST:
    worm->head->x++; 
    break;
  case WEST:
    worm->head->x--; 
    break;
  }

  //maybe a do while would be more effecient here
  while(last_coordinates->next != null) {
    
  }
  
}

void change_direction (worm_t *worm, int direction) {
  worm->direction = direction;
}

