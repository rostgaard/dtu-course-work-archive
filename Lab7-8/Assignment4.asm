; Reads two digit decimal number from the console and returns the value
; in register R0
;
; Usage list:
;  R0 - Return value
;  R1 - Digit 1
;  R2 - Digit 2
	
.ORIG x3000

main	JSR readS
	JSR resultS
	BRnzp main

;; resultS
resultS	ADD R0,R0,#0
	BRz noPrime
	BRp prime
	RET

prime	LEA R0, isPrimeZ
	PUTS
	RET

noPrime LEA R0, isNotPrimeZ
	PUTS
	RET
isPrimeZ  .STRINGZ "The number is prime"
isNotPrimeZ  .STRINGZ "The number is not prime"

	
;; readS
readS	LEA R0, Prompt
	PUTS

	GETC

;; Put Char
	ST R3, SaveR3
PUTCh	LDI R3,DSR
        BRzp PUTCh	; Loop until Monitor is ready
	STI     R0,DDR
	LD R3, SaveR3
	
; converts the ASCII value in R0 to DEC
	ST R6, SaveR6
	LD R6, ASCII
	ADD R0,R0,R6
	LD R6,SaveR6

; Multiplies the value in R0 with 10 returns in R2
	ADD R0,R0,R0
	ADD R2,R0,R0
	ADD R2,R2,R2
	ADD R2,R2,R0

	GETC
;; Put Char
	ST R3, SaveR3
PUTCh1	LDI R3,DSR
        BRzp PUTCh1	; Loop until Monitor is ready
	STI     R0,DDR
	LD R3, SaveR3

; converts the ASCII value in R0 to DEC
	ST R6, SaveR6
	LD R6, ASCII
	ADD R0,R0,R6
	LD R6,SaveR6
	ADD R1,R0,#0	; Stores the first value in R2
	ADD R0, R1,R2
	RET
	
Prompt .STRINGZ "Input a 2 digit decimal number: "
ASCII	.FILL xFFD0
DSR     .FILL   xFE04
DDR     .FILL   xFE06
SaveR1	.FILL	0
SaveR2	.FILL	0
SaveR3	.FILL	0
SaveR4	.FILL	0
SaveR5	.FILL	0
SaveR6	.FILL	0
SaveR7	.FILL	0

.END
