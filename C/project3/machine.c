#include "machine.h"
#include <stdio.h>
#define OPCODE 4026531840
#define ADDRESS 268434944
/* Shaun Birch 115938328 srbpgh */
/* I pledge on my honor that i didn't cheat in any way on this */
/* project */

/* prints out the instruction passed in as long as it is valid */
int print_instruction(Word instruction){

  Operation opcode = 0;
  if (!is_valid_instruction(instruction)){
    return 0;
  }
  opcode = (instruction & OPCODE ) >> 28; /* get the opcode */
  if (opcode == 0)
    printf("plus");
  else if (opcode == 1)
    printf("diff");
  else if (opcode == 2)
    printf("mult");
  else if (opcode == 3)
    printf("quot");
  else if (opcode == 4)
    printf("mod");
  else if (opcode == 5)
    printf("and");
  else if (opcode == 6)
    printf("or");
  else if (opcode == 7)
    printf("not");
  else if (opcode == 8)
    printf("br");
  else if (opcode == 9)
    printf("read");
  else if (opcode == 10)
    printf("wrt");
  else if (opcode == 11)
    printf("move");
  else if (opcode == 12)
    printf("lw");
  else if (opcode == 13)
    printf("sw");
  else if (opcode == 14)
    printf("li");
  
  /*prints the registers if they are needed */
  printf(" R%d", (instruction & 448) >> 6);
  if (opcode <= 7 || opcode == 11){
    printf(" R%d", (instruction & 56) >> 3);
    if (opcode <= 6){
      printf(" R%d", instruction & 7);
    }
  }
  /*prints the memory address if it is needed */
  if (opcode == 8 || opcode == 12 || opcode == 13){
    printf(" %05d", (instruction & ADDRESS) >> 9); 
  }
  else if (opcode == 14){
    printf(" %d", (instruction & ADDRESS) >> 9);
  }
  
  return 1;
}


/* prints out the passed in program in machine code */
int disassemble(const Word program[], unsigned int instr_num,
		unsigned int data_num){
  unsigned int i = 0, counter = 0;
  
  if (program == NULL || instr_num == 0 || instr_num + data_num > NUM_WORDS){
    return 0;
  }
  /*prints out the lines of the program if they are valid */
  for (i = 0; i < instr_num; i++){
    if (!is_valid_instruction(program[i])){
      return 0;
    }

    printf("%04x: ",counter); /* prints the memory address of the line */
    print_instruction(program[i]);
    printf("\n");
    counter += 4;
  }
  data_num += i;
  /*prints the data accompying the program */
  for (i = i; i < data_num; i++){
    printf("%04x: ", counter);
    printf("%08x\n", program[i]);
    counter += 4;
    
  }
  
  return 1;
}

/* returns 1 if the passed in word is a valid instruction and 0 if it isnt */
int is_valid_instruction(Word word){

  unsigned int tester = 0;
  unsigned int operator = word & OPCODE;
  operator = operator >> 28;
  if (operator == 15) /* tests that the opcode is valid */
    return 0;
  if (operator == 8 || operator >= 12){
    
    tester = word & ADDRESS; /* gets the memory address and tests it*/
    tester = tester >> 9;
    if (tester > 12287 || tester % 4 != 0) 
      return 0;
  }
  
  if (operator == 8 || operator == 10 || operator == 13){
    return 1;
  }
  /*tests that R0 and R6 are not being written to if necessecary */
  tester = word & 448;
  tester = tester >> 6; 
  if (tester == 0 || tester == 6)
    return 0;
  return 1;
  
}


/* takes a passed in program and moves it to a different place in memory */
int relocate(const Word program[], unsigned int instr_num,
	     unsigned int data_num, int offset, Word relocated_program[],
	     unsigned short *const instrs_changed){
  
  unsigned int i = 0;
  Word temp;
  /* returns 0 if anything passed in is invalid */
  if (program == NULL || relocated_program == NULL || offset % 4 != 0 ||
      instr_num == 0 || instr_num + data_num > NUM_WORDS ||
      instrs_changed == NULL)
    return 0;
  /* moves every instructions memory address by the value of offset */
  for (i = 0; i < instr_num; i++){
    if (!is_valid_instruction(program[i]))
      return 0;
    temp = (program[i] & OPCODE) >> 28; /*gets opcode */
    if (temp == 8 || temp > 11){
      temp = (program[i] & ADDRESS) >> 9; /* gets the memory address */
      temp += offset;
      if (temp < 0 || temp > 12287)
	return 0; /* making sure the memory address doesnt go out of bounds */
      relocated_program[i] = program[i];      
      relocated_program[i] += offset << 9;
      (*instrs_changed)++; 
    }
    else {
      /* simply copies the line over if it doesn't use a memory address */
      relocated_program[i] = program[i]; 
    }
    
  }
  /*copies the data over */
  for (i = i; i < instr_num + data_num; i++)
    relocated_program[i] = program[i];
  
  return 1;
}
