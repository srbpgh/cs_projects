/*Shaun Birch 115938328 9/16/2019 */
#include <stdio.h>

/* this program takes an input that can either be typed or a */
/* file and replaces all of it's tabs with the correct number */
/* of spaces */

int main (void) {
  int char_count = 0;
  char cur = '0';
  int i = 0;
  char line[999];

  /*this loop runs while there is still input and will terminate*/
  /* when the input runs out */
  while (!feof(stdin)) {
      scanf("%c", &cur);

      /*replaces any tab with the appropriate number of spaces */
      if (cur == '\t') {
        for (i = 0; i < (8 - char_count % 8); i++) {
	  line[char_count + i] = ' ';
        }
        char_count += i;
      }
      /* when the line ends, it will output the stored characters */
      /* as a completed line with tabs substituted */
      else if (cur == '\n') {
        for (i = 0; i < char_count; i++) {
	  printf("%c", line[i]);
        }
	if (!feof(stdin)) {
	  printf("\n");
        }
      char_count = 0;
      }
      /*stores the line characters that are not tabs in an array of */
      /* characters */
      else {
        line[char_count] = cur;
        char_count++;
      }
  }
  
  return 0;
}
