#include "list.h"
#include <stdio.h>
#include <assert.h>

int main(){
  List test1, *test2;
  init(&test1);
  test2 = NULL;
  assert(append(&test1,1));
  assert(append(&test1,2));
  assert(append(&test1,3));
  assert(append(&test1,4));
  assert(append(&test1,5));
  assert(append(test2,1) == 0);
  print(&test1);
  return 0;
}
