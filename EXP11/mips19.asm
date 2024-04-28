.text
.globl main

main:
    # Print string msg1
    li $v0, 4           # syscall code for printing string = 4
    la $a0, msg1        # load address of msg1
    syscall

    # Get input A from user and save
    li $v0, 5           # syscall code for reading integer = 5
    syscall
    move $t0, $v0       # save input A in $t0

    # Print string msg2
    li $v0, 4           # syscall code for printing string = 4
    la $a0, msg2        # load address of msg2
    syscall

    # Get input B from user and save
    li $v0, 5           # syscall code for reading integer = 5
    syscall
    move $t1, $v0       # save input B in $t1

    # Math: A = A + B
    add $t0, $t0, $t1   # A = A + B

    # Print string msg3
    li $v0, 4           # syscall code for printing string = 4
    la $a0, msg3        # load address of msg3
    syscall

    # Print sum
    move $a0, $t0       # load sum (A) into $a0
    li $v0, 1           # syscall code for printing integer = 1
    syscall

    # Print newline
    li $v0, 4           # syscall code for printing string = 4
    la $a0, newline     # load address of newline
    syscall

    # Exit
    li $v0, 10          # syscall code for exit = 10
    syscall

.data
msg1: .asciiz "Enter A: "
msg2: .asciiz "Enter B: "
msg3: .asciiz "A + B = "
newline: .asciiz "\n"
