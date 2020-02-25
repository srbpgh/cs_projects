#include <stdio.h>
#include <assert.h>
#include "functions.h"

/* CMSC 216, Fall 2019, Project #1
 * Public test 3 (public3.c)
 *
 * Tests calling quadrant() with quadrant 1.
 *
 * (c) Larry Herman, 2018.  You are allowed to use this code yourself, but
 * not to provide it to anyone else.
 */

int main() {
  assert(quadrant(123, 456) == 1);
  printf("\nTest passed!\n");

  return 0;
}
