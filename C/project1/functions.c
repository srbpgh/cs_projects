#include "functions.h"
#include <stdio.h>
/* Write your implementations of the functions in this source file.
 * Skeleton versions of the functions already appear below.
 */

int sum_of_cubes(int n) {

  int sum = 0;
  
  if(n == 0){
    return 0;
  }

  if(n < 0){
    return -1;
  }
  sum = (n * (n + 1)) / 2;
  sum *= sum;
  return sum;
}

int quadrant(int x, int y) {

  if(x == 0 || y == 0){
    return -1;
  }
  if(x > 0){
    if(y > 0){
      return 1;
    }
    else{
      return 4;
    }
  }
  if(y > 0){
    return 2;
  }
  return 3;
}

int num_occurrences_of_digit(long num, int digit) {

  int count = 0;
  long temp = num;
  if(digit < 0 || digit > 9){
    return -1;
  }

  if(num == 0 && digit == 0){
    return 1;
  }

  if(num < 0){
    num = -num;
  }
  
  while(num != 0){
    /* printf("num is %ld\n", num); */
    temp = num % 10;
    if(temp == digit){
      count++;
    }
    num = num / 10;
  }
  /* printf("count is %d\n", count); */
  return count;
}
