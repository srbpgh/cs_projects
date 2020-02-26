/* (c) Larry Herman, 2019.  You are allowed to use this code yourself, but
   not to provide it to anyone else. */

typedef struct node {
  int data;
  struct node *next;
}Node;

typedef struct list {
  Node *head;
}List;

void init(List *const list);
int append(List *const list, int new_value);
int prepend(List *const list, int new_value);
int size(List *const list);
int find(List *const list, int value);
int delete(List *const list, unsigned int position);
void print(List *const list);
