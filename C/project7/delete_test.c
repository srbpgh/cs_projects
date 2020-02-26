#include "list.h"
#include <stdio.h>
#include <assert.h>

int main(){
  List test1,*test2;
  test2 = NULL;
  init(&test1);
  assert(delete(test2,0) == 0);
  assert(delete(&test1,0) == 0);
  append(&test1,1);
  append(&test1,2);
  assert(delete(&test1,1) == 1);
  print(&test1);
  assert(delete(&test1,0) == 1);
  print(&test1);
  append(&test1,1);
  append(&test1,2);
  append(&test1,3);
  append(&test1,4);
  append(&test1,5);
  append(&test1,6);
  assert(delete(&test1,5) == 1);
  print(&test1);
  assert(delete(&test1,4) == 1);
  print(&test1);
  assert(delete(&test1,0) == 1);
  print(&test1);
  assert(delete(&test1,2)&1);
  print(&test1);
  return 0;
}
