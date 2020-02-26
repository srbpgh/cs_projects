#include <stdio.h>
#include <ctype.h>
#include <pthread.h>
#include <string.h>
#include <stdlib.h>
/* (c) Larry Herman, 2019.  You are allowed to use this code yourself, but
 * not to provide it to anyone else.
 */

/* Counts the number of words, lines, and characters in the files whose
 * names are given as command-line arguments.  If there are no command-line
 * arguments then the line, word, and character counts will just be 0.
 * Mimics the effects of the UNIX "wc" utility, although does not have
 * exactly the same behavior in all cases.
 */
typedef struct {
  char *filename;
  int *data;
}Input;

void *read_file(void *arg){
  Input *file;
  int lines, words, chars;
  char ch, next_ch;
  FILE *fp;
  file = (Input*) arg;
  fp = fopen(file->filename, "r");
  lines = words = chars = 0;
  if (fp != NULL) {
    lines= words= chars= 0;

    /* read each file one character at a time, until EOF */
    ch= fgetc(fp);
    while (!feof(fp)) {
      next_ch= fgetc(fp);  /* look ahead and get the next character */
      ungetc(next_ch, fp);  /* unread the next character (see Chapter 15) */

      /* update the counts as needed every time a character is read */

      /* a newline means the line count increases */
      if (ch == '\n')
	lines++;

      /* if the current character is not whitespace but the next character
	 is, or if the current character is not whitespace and it is the
	 last character in the input, the word count increases */
      if (!isspace(ch) && (isspace(next_ch) || feof(fp)))
	words++;

      /* increasing the character count is a no-brainer */
      chars++;

      ch= fgetc(fp);
    }
  }
  file->data[0] = lines;
  file->data[1] = words;
  file->data[2] = chars;
  return NULL;
}

int main(int argc, char *argv[]) {
  int arg_num= 1, total_lines= 0, total_words= 0, total_chars= 0, i = 0, count;
  pthread_t *tids;
  Input *files;
  count = argc - 1; /* argc is 1 too big */
  tids = malloc(sizeof(pthread_t) * count);
  files = malloc(sizeof(Input) * count);
  for(arg_num = 0; arg_num < count; arg_num++){
    files[arg_num].filename = malloc(sizeof(char) * strlen(argv[arg_num + 1]) + 1);
    strcpy(files[arg_num].filename, argv[arg_num + 1]);
    files[arg_num].data = malloc(sizeof(int) * 3);
    files[arg_num].data[0] = files[arg_num].data[1] = files[arg_num].data[2] = 0;
    pthread_create(&tids[arg_num], NULL, read_file, &files[arg_num]);
  }
  
  while (i < count) {
    pthread_join(tids[i], NULL);
    total_lines += files[i].data[0];
    total_words += files[i].data[1];
    total_chars += files[i].data[2];
    free(files[i].data);
    free(files[i].filename);
    i++;
  }
  free(tids);
  free(files);
  printf("%4d %4d %4d\n", total_lines, total_words, total_chars);

  return 0;
}
