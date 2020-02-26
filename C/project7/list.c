#include "list.h"
#include <stdio.h>
#include <stdlib.h>

/*initializes the list as an empty list */
void init(List *const list) {
  Node *head = NULL;
  if (list != NULL) {
    list->head = head;
  }
}

/* adds the passed in value to the end of the list */
int append(List *const list, int new_value) {
  Node *cur;
  Node *new_node;
  if (list == NULL)
    return 0;
  new_node = malloc(sizeof(Node));
  new_node->data = new_value;
  cur = list->head;
  if (cur == NULL) {
    list->head = &(*new_node);
    return 1;
  }
  while (cur->next != NULL)
    cur = cur->next;
  cur->next = &(*new_node);
  return 1;
}

/*adds the passed in value to the beginning of the list*/
int prepend(List *const list, int new_value) {
  Node *new_node;
  if (list == NULL)
    return 0;
  new_node = malloc(sizeof(Node));
  new_node->next = list->head;
  new_node->data = new_value;
  list->head = new_node;
  return 1;
}

/*returns the size of the list */
int size(List *const list) {
  Node *cur;
  int count = 1;
  if (list == NULL)
    return 0;
  cur = list->head;
  if (cur == NULL)
    return 0;
  while (cur->next != NULL) {
    cur = cur->next;
    count++;
  }
  return count;
  
}

/*searches for the element with the data passed into */
/* the function then returns its index */
int find(List *const list, int value) {
  Node *cur;
  int count = 0;
  if (list == NULL)
    return 0;
  cur = list->head;
  if (cur == NULL)
    return -1;
  while (cur != NULL) {
    if (cur->data == value)
      return count;
    count++;
    cur = cur->next;
  }
  return -1;
}

/*deletes the element at the index passed in */
int delete(List *const list, unsigned int position) {
  Node *cur, *prev;
  int count = 0;
  if (list == NULL)
    return 0;
  cur = list->head;
  if (cur == NULL)
    return 0;
  if (position == 0) { 
    list->head = cur->next;
    free(cur);
    return 1;
  }
  prev = list->head;
  cur = list->head;
  while (count < position && cur != NULL) {
    prev = cur;
    cur = cur->next;
    count++;
  }
  if (cur == NULL)
    return 0;
  
  prev->next = prev->next->next;
  free(cur);
  return 1;
}

/*prints out the data of the elements, seperated by a space */
/*ending with a return character */
void print(List *const list) {
  Node *cur;
  if (list != NULL){
    cur = list->head;
    if (cur != NULL){
      while (cur != NULL){
	printf("%d", cur->data);
	if (cur->next != NULL)
	  printf(" ");
	cur = cur->next;
      }
      printf("\n");
    }
  }
}
