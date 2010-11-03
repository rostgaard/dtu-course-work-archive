; Reads two digit decimal number from the console and returns the value
; in register R0
;
; Usage list:
;  R0 - Return value
;  R1 - Digit 1
;  R2 - Digit 2
	
.ORIG x3000
main	JSR readS	; Call subroutine readS
	JSR isPrime	; Call subroutine isPrime
	JSR resultS	; Call subroutine readS
	BRnzp main	; Infinite main loop

;; readS
readS	ST R7,SaveR7readS	; provide a way back
	LEA R0, Prompt    
	PUTS		; output prompt

	GETC		; wait for character

	; output character to screen
loop1	LDI R3,DSR
        BRzp loop1	; Loop until Monitor is ready
	STI     R0,DDR

	; converts the ASCII value in R0 to DEC
	LD R4, ASCII
	ADD R0,R0,R4

	; Multiplies the value in R0 with 10 returns it in R2
	; 10*R0 = 2* (2*R0 + 2*R0) + R0
	ADD R0,R0,R0
	ADD R2,R0,R0
	ADD R2,R2,R2
	ADD R2,R2,R0

	GETC		; wait for character

loop2	LDI R3,DSR
        BRzp loop2	; Loop until Monitor is ready
	STI     R0,DDR
	
	; converts the ASCII value in R0 to DEC
	ADD R0, R0, R4
	ADD R1, R0, #0
	ADD R0, R1, R2	;adds the two integers

	LD R7,SaveR7readS
	RET

SaveR7readS
Prompt .STRINGZ " \nInput a 2 digit decimal number: "
ASCII	.FILL xFFD0
DSR     .FILL   xFE04
DDR     .FILL   xFE06

;; resultS
resultS	ST R7,SaveR7results	; provide a way back
	ADD R0,R0,#0		; Raise zero flag, on zero
	BRz zero
	BRp pos
	BRnzp retres

	; This is a prime
pos	LEA R0, isPrimeZ
	PUTS
	BRnzp retres

	; This is not a prime
zero LEA R0, isNotPrimeZ
	PUTS
retres	LD R7,SaveR7results
	RET

SaveR7results	.FILL 0
isPrimeZ  .STRINGZ "\nThe number is prime"
isNotPrimeZ  .STRINGZ "\nThe number is not prime"


; isPrime
isPrime	ST R7,SaveR7primes	; provide a way back
	LEA R2, primes 		; prime_ptr
	NOT R0,R0
	ADD R0,R0,#1		; Two's compliment of input number
again	LDR R1,R2,#0
	ADD R1,R1,R0
	BRp notok		; only larger numbers than input numbers remain, this is not a prime
	BRz ok			; the number matches the current one in the array
	ADD R2,R2,#1		; else, increment prime_ptr and try next pointer
	BRnzp again

notok	AND R0,R0,#0		; store 0 in R0
	LD R7,SaveR7primes
	RET

ok	AND R0,R0,#0		; reset R0
	ADD R0,R0,#1		; store 1
	LD R7,SaveR7primes
	RET

primes	.FILL #2	; Array of primes
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

SaveR7primes	.FILL 0


.END
