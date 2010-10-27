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
			; Output the char

	GETC
	JSR PUTCh
	JSR toDEC	; converts the ASCII value in R0 to DEC
	ADD R1,R0,#0	; Stores the first value in R2
			; Output the char

	JSR toDEC
;	JSR toASCII
;	JSR PUTCh

	HALT
Prompt .STRINGZ "Input a 2 digit decimal number: "


toDEC	LD R6, ASCII
	ADD R0,R0,R6
	RET
ASCII	.FILL xFFD0

; Multiplies the value in R0 with 10 returns in R2
mult10	ADD R0,R0,R0
	ADD R2,R0,R0
	ADD R2,R2,R2
	ADD R2,R2,R0
	RET

; Converts the values in R1 and R2 to one integer, using R2 as MSD
toINT	ADD R0, R1,R2
	RET

; Converts the value in R0 to ASCII value
toASCII	LD R3,DECIMAL
	ADD R0,R0,R3
	RET
DECIMAL	.FILL x0020

PUTCh	LDI R3,DSR
        BRzp PUTCh	; Loop until Monitor is ready
	STI     R0,DDR
	RET

DSR     .FILL   xFE04
DDR     .FILL   xFE06

.END