; Reads two digit decimal number from the console and returns the value
; in register R0
;
; Usage list:
;  R0 - Return value
;  R1 - Digit 1
;  R2 - Digit 2
	
.ORIG x3000
readS	LEA R0, Prompt
	PUTS

	GETC
;Put char
loop1	LDI R3,DSR
        BRzp loop1	; Loop until Monitor is ready
	STI     R0,DDR

; converts the ASCII value in R0 to DEC
	LD R6, ASCII
	ADD R0,R0,R6

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
	ADD R0,R0,R6

	ADD R1,R0,#0	; Stores the first value in R2

	ADD R0, R1,R2

	HALT
Prompt .STRINGZ "Input a 2 digit decimal number: "
ASCII	.FILL xFFD0
DSR     .FILL   xFE04
DDR     .FILL   xFE06
.END
