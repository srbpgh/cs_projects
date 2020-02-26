	# Shaun Birch 115938328 srbpgh Sam 10am,
	# this program USES RECURSION to print the
	# number of occurances of the digit Y
	# in the number X
	.data
x:	.word 0
y:	.word 0
answer:	.word 0
	.text
main:	li $v0, 5 #scans in the first value
	syscall
	sw $v0, x #stores it in X
	li $v0, 5
	syscall #scans in the second value
	sw $v0, y #stores it in Y
	add $sp, 8 #stores X and Y as parameters
	lw $t0, x 
	lw $t1, y
	sw $t0, 8($sp)
	sw $t1, 4($sp)	
	jal count_digit #function call
	sub $sp, 8
	sw $v0, answer
	lw $a0, answer #prints out the result
	li $v0, 1
	syscall
	li $a0, 10
	li $v0, 11 #prints out a new line
	syscall
	li $v0, 10 #exits program
	syscall
count_digit:	sub $sp, $sp, 16 #prologue
	sw $ra, 16($sp)
	sw $fp, 12($sp)
	add $fp, $sp, 16 #initalizes the local variables
	li $t0, -1
	sw $t0, 8($sp)
	li $t0, 0
	sw $t0, 4($sp)
	lw $t0, 4($fp) # loading digit to t0
	bltz $t0 endif #the first if statement
	li $t1, 9
	bgt $t0, $t1, endif #second part first if statement
	li $t1, 0
	sw $t1, 8($sp)
	lw $t0, 8($fp)
	bge $t0, $t1, endinvert # checking if need to make number positive
	mul $t0, $t0, -1 
	sw $t0, 8($fp)
endinvert:	lw $t0, 8($fp)
	bltz $t0, else # if num >= 0
	li $t1, 9
	bgt $t0, $t1, else # and if num <= 9
	lw $t1, 4($fp) #if these are true this is the last call
	bne $t0, $t1, elseresult #if num == digit
	li $t1, 1 #set result to 1
	sw $t1, 8($sp)
	j endif #go back up the chain
elseresult:	li $t1, 0 #set result to 0
	sw $t1, 8($sp)
	j endif # go back up the chain
else:	lw $t0, 8($fp)
	li $t1, 10
	rem $t0, $t0, $t1 # rightmostdigit = num % 10
	sw $t0, 4($sp)
	sub $sp, $sp, 8 # getting the stack ready for a recursive call
	lw $t0, 8($fp)
	li $t1, 10
	div $t0, $t0, $t1 #setting the parameters
	sw $t0, 8($sp)
	lw $t0, 4($fp)
	sw $t0, 4($sp)
	jal count_digit #recursive call
	add $sp, $sp, 8 #clean up
	sw $v0, 8($sp) # result = return value of call
	lw $t0, 4($sp)
	lw $t1, 4($fp)
	bne $t0, $t1, endif #if rightmostdigit == digit
	lw $t0, 8($sp)
	add $t0, $t0, 1 #result++
	sw $t0, 8($sp)
endif:	lw $v0, 8($sp) #prologue
	lw $ra, 16($sp)
	lw $fp, 12($sp)
	add $sp, $sp, 16
	jr $ra
