/* Shaun Birch 115938328 9/16/2019 */
#include <stdio.h>

/* this program checks to see if any of the lines in the */
/* input are longer than 80 lines.  it outputs all of */
/* the lines and marks them with an X at the beginning */
/* of any lines that are longer than 80 characters */

int main(void){
  int char_count = 0;
  char cur = 0;
  char char_array[999];
  char marker = ' ';
  int i = 0;

  /* this loop runs until the all of the input has been */
  /* processed */
  while (!feof(stdin)) {
    scanf("%c", &cur);

    /* if the input is a not a new line, the character is */
    /* stored in an array and is counted */
    if (cur != '\n') {
      char_array[char_count] = cur;
      char_count++;
    }
    else {
      /* makes sure this is not the blank line at the end */
      /* of the file */
      if (!feof(stdin)) {
        if (char_count < 80) {
  	  marker = ' ';
        }
        else {
  	  marker = 'X';
        }
	/* outputs the line in the correct format, marking it */
	/* as to whether or not it is too long */
        printf("%c ", marker);
        printf("%3d: ", char_count);
        for (i = 0; i < char_count; i ++) {
	  printf("%c",char_array[i]);
        }
	/* resets the line and count to prepare for the next line */
        printf("\n");
        char_count = 0;
      
      }
    }
  }
  
  return 0;
}

