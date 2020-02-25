#include <stdio.h>
#include <assert.h>
#include "student.h"

/* CMSC 216, Fall 2019, Project #4
 * Public test 6 (public06.c)
 *
 * Tests that has_name() and has_id() return 0 when called with the
 * incorrect name and ID for a student.
 *
 * (c) Larry Herman, 2019.  You are allowed to use this code yourself, but
 * not to provide it to anyone else.
 */

int main(void) {
  Student s= new_student("Alex", 11144, 6.5);

  assert(!has_name(s, "Alexander"));
  assert(!has_name(s, "Al"));
  assert(!has_name(s, "ALEX"));
  assert(!has_name(s, "alex"));
  assert(!has_id(s, 11114));

  printf("Success!\n");

  return 0;
}
