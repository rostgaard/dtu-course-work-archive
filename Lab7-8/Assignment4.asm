; Reads two digit decimal number from the console and returns the value
; in register R0
;
; Usage list:
;  R0 - Return value
;  R1 - Digit 1
;  R2 - Digit 2
	
.ORIG x3000
main	JSR readS
	JSR isPrime
	JSR resultS
	BRnzp main

;; readS
readS	ST R7,SaveR7
	LEA R0, Prompt
	PUTS

	GETC
;Put char
loop1	LDI R3,DSR
        BRzp loop1	; Loop until Monitor is ready
	STI     R0,DDR

; converts the ASCII value in R0 to DEC
	LD R4, ASCII
	ADD R0,R0,R4

; Multiplies the value in R0 with 10 returns in R2
	ADD R0,R0,R0
	ADD R2,R0,R0
	ADD R2,R2,R2
	ADD R2,R2,R0

	GETC

loop2	LDI R3,DSR
        BRzp loop2	; Loop until Monitor is ready
	STI     R0,DDR
	
; converts the ASCII value in R0 to DEC
	ADD R0,R0,R4

	ADD R1,R0,#0	; Stores the first value in R2

	ADD R0, R1,R2

	LD R7,SaveR7
	RET

Prompt .STRINGZ "\nInput a 2 digit decimal number: "
ASCII	.FILL xFFD0
DSR     .FILL   xFE04
DDR     .FILL   xFE06

;; resultS
resultS	ST R7,SaveR7
	ADD R0,R0,#0
	BRz noPrime
	BRp prime
	LD R7,SaveR7
	RET

prime	LEA R0, isPrimeZ
	PUTS
	LD R7,SaveR7
	RET

noPrime LEA R0, isNotPrimeZ
	PUTS
	LD R7,SaveR7
	RET

isPrimeZ  .STRINGZ "\nThe number is prime"
isNotPrimeZ  .STRINGZ "\nThe number is not prime"

; isPrime
isPrime	ST R7,SaveR7
	LEA R2, primes 	; prime_ptr
	NOT R0,R0
	ADD R0,R0,#1
again	LDR R1,R2,#0
	ADD R1,R1,R0
	BRp notok
	BRz ok
	ADD R2,R2,#1	; increment prime_ptr
	BRnzp again

notok	AND R0,R0,#0
	LD R7,SaveR7
	RET

ok	AND R0,R0,#0
	ADD R0,R0,#1
	LD R7,SaveR7
	RET


primes	.FILL #2
	.FILL #3 
	.FILL #5 
	.FILL #7 
	.FILL #11 
	.FILL #13 
	.FILL #17 
	.FILL #19
	.FILL #23
	.FILL #29
	.FILL #31
	.FILL #37
	.FILL #41
	.FILL #43
	.FILL #47
	.FILL #53
	.FILL #59
	.FILL #61
	.FILL #67
	.FILL #71
	.FILL #73
	.FILL #79
	.FILL #83
	.FILL #89
	.FILL #97
	.FILL #101


SaveR7	.FILL 0

.END
