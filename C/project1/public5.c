#include <stdio.h>
#include <assert.h>
#include "functions.h"

/* CMSC 216, Fall 2019, Project #1
 * Public test 5 (public5.c)
 *
 * Tests calling num_occurrences_of_digit() when there are multiple
 * occurrences of digit in num.
 *
 * (c) Larry Herman, 2018.  You are allowed to use this code yourself, but
 * not to provide it to anyone else.
 */

int main() {
  assert(num_occurrences_of_digit(82838485678, 8) == 5);
  printf("\nTest passed!\n");

  return 0;
}
