#include <stdio.h>
#include "safe-fork.h"
#include "split.h"
#include <string.h>
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

/* mimics the xargs unix command. */
/* takes a command and will execute it with all the */
/* args passed in as well as any arguments passed in through */
/* the standard input */

int main(int argc, char *argv[]) {
  char **commands;
  char *command;
  char *base_commands;
  char c = ' ';
  char input[999] = "";
  pid_t pid;
  int i = 0;
  int length;
  int status;
  /* if there are no commands, it will call echo on it's input */
  if (argc == 1) {
    strcat(input, "echo ");
    while (!feof(stdin)) {
      scanf("%c",&c);
      if (c == '\n')
	c = ' ';
      strncat(input, &c,1);
    }
    commands = split(input);
    pid = safe_fork();
    if (pid == 0) {
      execvp("echo", commands);
      exit(1);
    }
    else{
      wait(&status);
      if (status != 0 && status != -1)
      	exit(1);
      i = 0;
      while (commands[i] != NULL) {
	free(commands[i]);
	i++;
      }
      free(commands);
    }
  }
  /* if there is a -i command, xargs will run once for each */
  /* line of command arguments passed into it through it's input */
  else if (argc > 1 && strcmp(argv[1], "-i") == 0) {
    if (argc == 2) {
      command = malloc(sizeof(char) * strlen("echo") + 1);
      if (command == NULL)
	exit(1);
      strcpy(command,"echo");
      base_commands = malloc(sizeof(char) * strlen("echo ") + 1);
      if (base_commands == NULL)
	exit(1);
      strcpy(base_commands,"echo ");
    }
    else {
      command = malloc(sizeof(argv[2]) * strlen(argv[2]));
      if (command == NULL)
	exit(1);
      strcpy(command,argv[2]);
      for (i = 2; i < argc; i ++) {
	length += strlen(argv[i]);
      }
      base_commands = malloc(sizeof(char) * length);
      if (base_commands == NULL)
	exit(1);
      for (i = 2; i < argc; i ++) {
	strcat(base_commands, argv[i]);
	strcat(base_commands, " ");
      }
    }
    strcat(input, base_commands);
    while (!feof(stdin)) {
      while (!feof(stdin) && c != '\n') {
	scanf("%c",&c);
	if (c != '\n')
	  strncat(input, &c,1);
      }
      if (!feof(stdin)) {
	commands = split(input);
	pid = safe_fork();
	if (pid == 0) {
	  execvp(command, commands);
	  exit(1);
	}
	else if (pid == -1) {
	  exit(1);
	}
	else {
	  wait(&status);
	  if (status != 0 && status != -1)
	    exit(1);
	  i = 0;
	  while (commands[i] != NULL) {
	    free(commands[i]);
	    i++;
	  }
	  free(commands);
	  strcpy(input, base_commands);
	  c = 'a';
	}
      }
    }
    free(command);
    free(base_commands);
  }
  /* the standard case runs once with all of the */
  /* arguments passed into it through it's standard input */
  else {
    for (i = 1; i < argc; i ++) {
      strcat(input, argv[i]);
      strcat(input, " ");
    }
    while (!feof(stdin)) {
      scanf("%c", &c);
      if (c == '\n')
	c = ' ';
      strncat(input, &c,1);
    }
    commands = split(input);
    pid = safe_fork();
    if (pid == 0) {
      execvp(argv[1], commands);
      exit(1);
      
    }
    else {
      wait(&status);
      if (status != 0 && status != -1)
	exit(1);
      i = 0;
      while (commands[i] != NULL) {
	free(commands[i]);
	i++;
      }
      free(commands);
    }
  }
  return 0;
}

