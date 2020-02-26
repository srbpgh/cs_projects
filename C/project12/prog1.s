	#Shaun Birch srbpgh 115938328 Sam 10am
	# This program prints the sum of the cubes of the first n integers
	.data
n:	.word 0
ans:	.word -1
	.text
main:	li $v0, 5 #reads in an integer
	syscall
	sw $v0, n
	bge $v0, 0, then # if n >= 0
	jal endif
then:	add $t0, $v0, 1 #this block is the cube operation
	mul $t0, $t0, $v0
	div $t0, $t0, 2
	add $t1, $v0, 1
	mul $t1, $v0, $t1
	div $t1, $t1, 2
	mul $t0, $t0, $t1
	sw $t0, ans #stores the result in answer
endif:	li $v0, 1
	lw, $a0, ans #prints the result or -1
	syscall
	li $v0, 11
	li, $a0, 10 #prints the new line character
	syscall
	li $v0, 10
	syscall #exits
