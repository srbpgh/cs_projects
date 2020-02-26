#include "list.h"
#include <stdio.h>

int main(){
  List test1, *test2;
  init(&test1);
  test2 = NULL;
  print(&test1);
  print(test2);
  append(&test1,1);
  print(&test1);
  append(&test1,2);
  print(&test1);
  return 0;
}
