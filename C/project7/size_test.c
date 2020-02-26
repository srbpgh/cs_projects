#include "list.h"
#include <stdio.h>
#include <assert.h>

int main(){
  List test1,*test2;
  init(&test1);
  test2 = NULL;
  assert(size(&test1) == 0);
  printf("tested size 0");
  append(&test1,1);
  assert(size(&test1) == 1);
  printf("tested size 1");
  prepend(&test1,0);
  assert(size(&test1) == 2);
  printf("tested prepend");
  assert(size(test2) == 0);
  printf("tested null");
  delete(&test1,0);
  assert(size(&test1) == 1);
  printf("tested after deleting");
  return 0;
}
