#include "student.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

Student *new_student(const char name[], unsigned int id, float shoe_size){
  Student *s;
  s = malloc(sizeof(Student));
  if(name == NULL){
    s->name = malloc(1);
    s->name = "";
  }
  else{
    s->name = malloc(strlen(name) + 1);
    strcpy(s->name,name);
  }
  s->id = id;
  s->shoe_size = shoe_size;
  return s;
}

unsigned int has_id(Student *const student, unsigned int id){
  if(student == NULL)
    return 0;
  if(student->id == id)
    return 1;
  return 0;
}

unsigned int has_name(Student *const student, const char name[]){
  if(student == NULL || name == NULL || student->name == NULL)
    return 0;
  if(strcmp(student->name,name) == 0)
    return 1;
  return 0;
}

unsigned int get_id(Student *const student){
  if(student == NULL)
    return 0;
  return student->id;
}

float get_shoe_size(Student *const student){
  if(student == NULL)
    return 0.0;
  return student->shoe_size;
}

void change_shoe_size(Student *const student, float new_shoe_size){
  if(student != NULL)
    student->shoe_size = new_shoe_size;
    
}

void change_name(Student *const student, const char new_name[]){
  if(student != NULL){
    if(new_name == NULL){
      student->name = realloc(student->name, 1);
      student->name = "";
      
    }
    else{
      student->name = realloc(student->name, sizeof(new_name));
      strcpy(student->name, new_name);
    }
  }
}

void copy_student(Student *student1, Student *const student2){
  if(student1 != NULL || student2 != NULL){
    student1->id = get_id(student2);
    change_shoe_size(student1,get_shoe_size(student2));
    change_name(student1,student2->name);
  }
}


