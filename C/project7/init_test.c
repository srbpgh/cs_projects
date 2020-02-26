#include "list.h"
#include <stdio.h>

int main(){
  List test1, *test2;
  init(&test1);
  printf("success1");
  test2 = NULL;
  init(test2);
  printf("success2");
  return 0;
}
