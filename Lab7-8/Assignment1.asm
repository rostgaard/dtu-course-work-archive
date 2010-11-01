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
	JSR PUTCh
	
	JSR toDEC	; converts the ASCII value in R0 to DEC
	JSR mult10

	GETC
	JSR PUTCh
	JSR toDEC	; converts the ASCII value in R0 to DEC
	ADD R1,R0,#0	; Stores the first value in R2

	ADD R0, R1,R2

	HALT
Prompt .STRINGZ "Input a 2 digit decimal number: "


toDEC	ST R6, SaveR6
	LD R6, ASCII
	ADD R0,R0,R6
	LD R6,SaveR6
	RET
ASCII	.FILL xFFD0

; Multiplies the value in R0 with 10 returns in R2
mult10	ADD R0,R0,R0
	ADD R2,R0,R0
	ADD R2,R2,R2
	ADD R2,R2,R0
	RET


PUTCh	ST R3, SaveR3
	LDI R3,DSR
        BRzp PUTCh	; Loop until Monitor is ready
	STI     R0,DDR
	LD R3, SaveR3
	RET

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
