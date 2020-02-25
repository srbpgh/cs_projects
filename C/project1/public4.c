#include <stdio.h>
#include <assert.h>
#include "functions.h"

/* CMSC 216, Fall 2019, Project #1
 * Public test 4 (public4.c)
 *
 * Tests calling quadrant() with quadrant 2.
 *
 * (c) Larry Herman, 2018.  You are allowed to use this code yourself, but
 * not to provide it to anyone else.
 */

int main() {
  assert(quadrant(-456, 789) == 2);
  printf("\nTest passed!\n");

  return 0;
}
