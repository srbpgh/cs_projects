#include <stdio.h>
#include "safe-fork.h"
#include <sys/types.h>
#include <unistd.h>
#include <errno.h>

int main(void){
  pid_t pid;
  int fd[2];
  int input;
  pipe(fd);
  pid = safe_fork();
  if(pid > 0){
    dup2(fd[0], STDIN_FILENO);
    close(fd[0]);
    close(fd[1]);
    scanf("%d",&input);
    if(input >= 200){
      printf("Long enough!\n");
      return 0;
    }
    else{
      printf("Too short!\n");
      return 1;
    }
  }
  else if(pid == 0){
    dup2(fd[1], STDOUT_FILENO);
    close(fd[0]);
    close(fd[1]);
    execlp("/usr/bin/wc","wc","-w",NULL);
  }
  else{
    printf("fork failed");
    return 2;
  }
  return 0;
  
}
