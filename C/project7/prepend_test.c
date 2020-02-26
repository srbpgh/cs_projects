#include "list.h"
#include <stdio.h>
#include <assert.h>

int main(){
  List test1, *test2;
  test2 = NULL;
  init(&test1);  
  assert(prepend(&test1,5));
  assert(prepend(&test1,4));
  assert(prepend(&test1,3));
  assert(prepend(&test1,2));
  assert(prepend(&test1,1));
  assert(prepend(test2,1) == 0);
  print(&test1);
  return 0;
}
