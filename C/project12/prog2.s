	#Shaun Birch srbpgh 115938328 Sam 10am
	# This program prints the number of occruances
	# of the digit Y in the number X
	.data
x:	.word 0
y:	.word 0
result:	.word 0
	.text
main:	li $v0, 5 #scans in the values
	syscall
	sw $v0, x
	li $v0, 5
	syscall
	sw $v0, y
	add $sp, 8 #stores the parameters
	lw $t0, x 
	lw $t1, y
	sw $t0, 8($sp)
	sw $t1, 4($sp)
	
	jal count_digit #function call
	sub $sp, 8
	sw $v0, result #gets return value of function
	lw $a0, result #prints out result
	li $v0, 1
	syscall
	li $a0, 10 #prints out new line
	li $v0, 11
	syscall
	li $v0, 10
	syscall
count_digit:	sub $sp, $sp, 16 #prologue
	sw $ra, 16($sp)
	sw $fp, 12($sp)
	add $fp, $sp, 16
	li $t0, -1 #initalizes local variables
	sw $t0, 8($sp)
	li $t0, 0
	sw $t0, 4($sp)
	lw $t0, 4($fp) # loading digit to t0
	bltz $t0 endif # if digit >= 0
	li $t1, 9
	bgt $t0, $t1, endif # and if digit <= 9
	li $t1, 0
	sw $t1, 8($sp) #sets count to 0
	lw $t0, 8($fp)
	bge $t0, $t1, endinvert # checking if need to make num pos
	mul $t0, $t0, -1
	sw $t0, 8($fp)
endinvert:	lw $t0, 8($fp)
	bnez $t0, else #checks if both param is 0
	lw $t0, 4($fp)
	bnez $t0, else
	li $t0, 1
	sw $t0, 8($sp) #sets count to 1
	j endif
else:	lw $t0, 8($fp) #if both arent 0, then start a loop
	blez $t0, endif #while loop start
	rem $t0, $t0, 10
	sw $t0, 4($sp) #get rightmost digit
	lw $t1, 4($fp)
	bne $t0, $t1, endincrement #if rightdigit is digit
	lw $t0, 8($sp)
	add $t0, $t0, 1 #count++
	sw $t0, 8($sp)
endincrement:	lw $t0, 8($fp) 
	div $t0 $t0, 10 #cut rightdigit from num
	sw $t0, 8($fp)
	j else #go back to start of loop
endif:	lw $v0, 8($sp) #store return value
	lw $ra, 16($sp) #prologue
	lw $fp, 12($sp)
	add $sp, $sp, 16
	jr $ra












	
