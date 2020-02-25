#include <stdio.h>
#include <assert.h>
#include "functions.h"

/* CMSC 216, Fall 2019, Project #1
 * Public test 8 (public8.c)
 *
 * Tests calling num_occurrences_of_digit() with num = 0 but digit != 0.
 *
 * (c) Larry Herman, 2018.  You are allowed to use this code yourself, but
 * not to provide it to anyone else.
 */

int main() {
  assert(num_occurrences_of_digit(0, 6) == 0);
  printf("\nTest passed!\n");

  return 0;
}
