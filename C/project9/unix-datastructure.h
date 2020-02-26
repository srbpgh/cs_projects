#if !defined(UNIX_DATASTRUCTURE_H)
#define UNIX_DATASTRUCTURE_h

typedef struct node {
  struct node *parent;
  struct node *next;
  struct node *contents;
  char *name;
}Node;

typedef struct {
  Node *root;
  Node *cur;
}Unix;

#endif
