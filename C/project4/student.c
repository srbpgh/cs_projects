#include "student.h"
#include <stdio.h>
#include <string.h>

/*Shaun Birch srbpgh 115938328 */

Student new_student(const char name[], unsigned int id, float shoe_size){
  int i = 0;
  char new_name[NAME_LEN];
  Student s = {"", 0, 0};
  s.shoe_size = shoe_size;
  s.id = id;
  if(name == NULL)
    return s;
  if(strlen(name) > 39){
    for(i = 0; i < 39; i ++){
      new_name[i] = name[i];
    }
    new_name[39] = '\0';
    strcpy(s.name,new_name);
    return s;
  }
  else{
    strcpy(new_name,name);
  }
  strcpy(s.name,new_name);
  return s;
}

void init_student(Student *const student, const char name[], unsigned int id,
		  float shoe_size){
  if(student != NULL){
    if(name == NULL){
      student->name[0] ='\0';
    }
    else{
      strcpy(student->name,name);
    }
    student->id = id;
    student->shoe_size = shoe_size;
  }
}

unsigned int has_id(Student student, unsigned int id){
  return student.id == id;
}

unsigned int has_name(Student student, const char name[]){
  if(name == NULL)
    return 0;
  if (strcmp(student.name, name) == 0)
    return 1;
  return 0;
}

unsigned int get_id(Student student){
  return student.id;
}

float get_shoe_size(Student student){
  return student.shoe_size;
}

Student change_shoe_size(Student student, float new_shoe_size){
  student.shoe_size = new_shoe_size;
  return student;
}

void change_name(Student *const student, const char new_name[]){
  int i = 0;
  
  if(student != NULL){
    if(new_name == NULL){
      student->name[0] = '\0';
    }
    else{
      if(strlen(new_name) < 40){
	strcpy(student->name,new_name);
      } else{
	for(i = 0; i < 39; i ++){
	  student->name[i] = new_name[i];
	}
	student->name[39] = '\0';
      }
    }
  }
    
}

