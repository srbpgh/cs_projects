#include <stdio.h>
#include <stdlib.h>
#include "unix.h"
#include <string.h>

/* Shaun Birch 115938328 srbpgh */


/* deletes and deallocates all contents of a file system*/
void rmfs(Unix *filesystem) {
  if (filesystem != NULL) {
    cd(filesystem,"/");
    while (filesystem->root->contents != NULL) {
      rm(filesystem,filesystem->cur->contents->name);
    }
    free(filesystem->root->name);
    free(filesystem->root);
  }
}

/* removes the contents of the specified directory */
/* if given a file, does nothing */
int remove_contents(Node* head) {
  Node *cur = head->contents;
  if (head->contents == head->parent)
    return 1;
  while (cur != NULL) {
    remove_contents(cur);
    free(cur->name);
    head->contents = cur->next;
    free(cur);
    cur = head->contents;
  }
  return 1;
}


/* this function initalizes a unix filesystem so that it can be used */
void mkfs(Unix *filesystem) {
  if (filesystem) {
    /* filesystem = malloc(sizeof(Unix)); */
    filesystem->root = malloc(sizeof(Node));
    if (filesystem->root == NULL)
      printf("memory allocation failed, please exit the program.");
    else {
      filesystem->cur = filesystem->root;
      filesystem->root->parent = filesystem->root;
      filesystem->root->next = NULL;
      filesystem->root->name = malloc(sizeof(char) * 2);
      if(filesystem->root->name == NULL)
	printf("memory allocation failed, pleast exit the program.");
      else {
	strcpy(filesystem->root->name,"/");
	filesystem->root->contents = NULL;
      }
    }
  }
}

/* this helper function prints the names of the rest of the nodes */
/* stored in the current directory when the current directories */
/* contents field is passed in.*/
static void print_contents(Node *head) {
  Node *cur = head;
  while (cur != NULL) {
    if (cur->contents == cur->parent)
      printf("%s\n",cur->name);
    else
      printf("%s/\n",cur->name);
    cur = cur->next;
  }
}

/* this helper function returns 1 if there is a node containing */
/* the name arg in the passed in directory and 0 otherwise */
static int does_name_exist(Unix *filesystem, const char arg[]) {
  Node *cur;
  
  if (filesystem->cur == NULL)
    return 0;
  cur = filesystem->cur->contents;
  if (filesystem->cur->contents == NULL)
    return 0;
  if (!strcmp(arg,cur->name))
    return 1;
  while (cur->next != NULL) {
    cur = cur->next;
    if (!strcmp(arg,cur->name))
      return 1;
  }
  return 0;
}

/* removes and deallocates the file/directory of the specified name from the current */
/* directory.  if it is a directory, it removes all the contents and the directory */
int rm(Unix *filesystem, const char arg[]) {
  Node *cur, *prev;
  if (filesystem == NULL || arg == NULL)
    return 0;
  if (!strcmp(arg,".") || !strcmp(arg,"..") || !strcmp(arg,"/") \
      || !strcmp(arg,"") || strstr(arg,"/"))
    return 0;
  if (!does_name_exist(filesystem,arg))
    return 0;
  prev = filesystem->cur;
  cur = prev->contents;
  if (!strcmp(cur->name,arg)) {
    prev->contents = cur->next;
    remove_contents(cur);
    free(cur->name);
    free(cur);
  } else {
    prev = cur;
    cur = cur->next;
    while (strcmp(cur->name,arg)) {
      prev = cur;
      cur = cur->next;
    }
    prev->next = cur->next;
    remove_contents(cur);
    free(cur->name);
    free(cur);
  }
  return 1;
  
}

/* this function creates a file with the name arg in the current */
/* directory */
int touch(Unix *filesystem, const char arg[]){
  Node *cur;
  Node *prev;
  Node *temp;
  if (!filesystem || !arg)
    return 0;
  /*make sure its not illegal */
  if (!arg || !strcmp(arg, "") || (strstr(arg,"/") && strcmp(arg,"/")))
    return 0;
  if (!strcmp(arg,"/") || !strcmp(arg,".") || !strcmp(arg,".."))
    return 1;
  if (does_name_exist(filesystem,arg))
    return 1;
  /*find where it should be put */
  cur = filesystem->cur->contents;
  temp = malloc(sizeof(Node));
  if (temp == NULL)
    return 0;
  temp->name = malloc((strlen(arg) + 1)* sizeof(char));
  if (temp->name == NULL)
    return 0;
  strcpy(temp->name,arg);
  temp->parent = filesystem->cur;
  temp->contents = filesystem->cur;  

  if (cur == NULL || strcmp(arg,cur->name) < 0){
    temp->next = cur;
    filesystem->cur->contents = temp;
    return 1;
  }
  prev = cur;
  cur = cur->next;
  while (cur != NULL){
    if (strcmp(arg,cur->name) < 0){
      prev->next = temp;
      temp->next = cur;
      return 1;
    }
    prev = cur;
    cur = cur->next;
  }
  prev->next = temp;
  temp->next = NULL;
  return 1;
}

/* this function creates an empty directory with the name arg */
/* in the current directory */

int mkdir(Unix *filesystem, const char arg[]) {
  Node *cur, *prev, *temp;
  if (!filesystem || !arg)
    return 0;
  if (!arg || !strcmp(arg, "") || !strcmp(arg,"..") || !strcmp(arg,".") \
      || strstr(arg,"/"))
    return 0;
  if (does_name_exist(filesystem,arg))
    return 0;
  cur = filesystem->cur->contents;
  temp = malloc(sizeof(Node));
  if (temp == NULL)
    return 0;
  temp->name = malloc((strlen(arg) + 1) * sizeof(char));
  if (temp->name == NULL)
    return 0;
  strcpy(temp->name,arg);
  temp->parent = filesystem->cur;
  temp->contents = NULL;
  if (cur == NULL || strcmp(arg,cur->name) < 0) {
    temp->next = cur;
    filesystem->cur->contents = temp;
    return 1;
  }
  prev = cur;
  cur = cur->next;
  while (cur != NULL) {
    if (strcmp(arg,cur->name) < 0) {
      prev->next = temp;
      temp->next = cur;
      return 1;
    }
    prev = cur;
    cur = cur->next;
  }
  prev->next = temp;
  temp->next = NULL;
  return 1;
}

/* this function changes the current directory based */
/* on what is passed in through arg */
int cd(Unix *filesystem, const char arg[]) {
  Node *cur;
  if (!filesystem || !arg)
    return 0;
  if (!strcmp(arg,".") || !strcmp(arg,""))
    return 1;
  if (!strcmp(arg,"..")) {
    filesystem->cur = filesystem->cur->parent;
    return 1;
  }
  if (!strcmp(arg,"/")) {
    filesystem->cur = filesystem->root;
    return 1;
  }
  if (!does_name_exist(filesystem,arg))
    return 0;
  cur = filesystem->cur->contents;
  while (strcmp(cur->name,arg)) {
    cur = cur->next;
  }
  /*In my data structure for node, a node's parent cannot have the */
  /*same memory address as one of it's contents.  So to signify a */
  /* file i point the Node's contents pointer at it's parent */
  if (cur->contents == cur->parent)
    return 0;
  filesystem->cur = cur;
  return 1;
}

/* this function lists the contents of a directory or file name */
/* specified by arg */
int ls(Unix *filesystem, const char arg[]) {
  Node *cur;
  if (!filesystem || !arg)
    return 0;
  if (!strcmp(arg, ".") || !strcmp(arg, "")) {
    cur = filesystem->cur->contents;
    print_contents(cur);
    return 1;
  }
  if (!strcmp(arg, "..")) {
    cur = filesystem->cur->parent->contents;
    print_contents(cur);
    return 1;
  }
  if (!strcmp(arg,"/")) {
    cur = filesystem->root->contents;
    print_contents(cur);
    return 1;
  }
  if (!does_name_exist(filesystem,arg))
    return 0;
  cur = filesystem->cur->contents;
  while (strcmp(arg,cur->name) != 0) {
    cur = cur->next;
  }
  if (cur->parent == cur->contents) {
    printf("%s\n",cur->name);
    return 1;
  }
  print_contents(cur->contents);
  return 1;
}

void print_path(Node *cur) {
  if (strcmp(cur->name,"/")) {
    print_path(cur->parent);
    printf("/%s",cur->name);
  }
}

/* this function prints the current working directory's name */
void pwd(Unix *filesystem) {
  if (filesystem) {
    if (strcmp(filesystem->cur->name,"/")) {
      print_path(filesystem->cur);
      printf("\n");
    }
    else
      printf("/\n");
  }
}


