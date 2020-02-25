#include <stdio.h>
#include <assert.h>
#include "functions.h"

/* CMSC 216, Fall 2019, Project #1
 * Public test 7 (public7.c)
 *
 * Tests calling num_occurrences_of_digit() when all of the digits of num
 * are equal to digit.
 *
 * (c) Larry Herman, 2018.  You are allowed to use this code yourself, but
 * not to provide it to anyone else.
 */

int main() {
  assert(num_occurrences_of_digit(555555555555, 5) == 12);
  printf("\nTest passed!\n");

  return 0;
}
