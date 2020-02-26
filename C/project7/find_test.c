#include "list.h"
#include <stdio.h>
#include <assert.h>

int main(){
  List test1,*test2;
  test2 = NULL;
  assert(find(test2,1) == 0);
  printf("passed null test");
  init(&test1);
  assert(find(&test1,1) == -1);
  append(&test1,1);
  assert(find(&test1,1) == 0);
  append(&test1,2);
  append(&test1,3);
  append(&test1,4);
  append(&test1,5);
  append(&test1,6);
  assert(find(&test1,4) == 3);
  printf("found middle object");
  assert(find(&test1,6) == 5);
  printf("found last object");
  assert(find(&test1,15341) == -1);
  return 0;
}
