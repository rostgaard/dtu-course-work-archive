#define WORM_HEAD 0
#define WORM_BODY 1
#define WORM_TAIL 2

#define NORTH 0
#define SOUTH 1
#define EAST  2
#define WEST  3


struct worm_body_t {
  int x;
  int y;
  struct *next;
}

typedef struct worm_body worm_body_t;


struct worm {
  int *head; 
  int direction;
  int full;
  int points;
  bool alive = true;
  int y;
};
typedef struct worm worm_t;

void kill_worm(worm_t *worm) {
  worm->alive = false;
}

void move_worm(worm_t *worm) {

}

void change_direction (worm_t *worm, int direction) {
  worm->direction = direction;
}

