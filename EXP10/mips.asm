.data
num1: .word 10
num2: .word 5
.text
main:
# load num1 into $a0
lw $a0, num1
# load num2 into $a1
lw $a1, num2
# add num1 and num2
add $v0, $a0, $a1
# print result
li $v0, 1 # syscall code for print_int
syscall
# exit program
li $v0, 10 # syscall code for exit
syscall