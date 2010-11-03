; Reads two digit decimal number from the console and returns the value
; in register R0
;
; Usage list:
;  R0 - Return value
;  R1 - Digit 1
;  R2 - Digit 2
	
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
