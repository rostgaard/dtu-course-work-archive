readIO 	ST R7,SaveR7readIO	; provide a way back
	JSR wait
	LDI R0, SWDR		; sample bitpattern in switches
	STI R0, SSEGDR		; output the hex value to SSEG
	AND R1,R1,#0		; Clear r1
	STI R1, LEDDR		; - in order to turn off all LED's
	LD R7,SaveR7readIO
	RET

SaveR7readIO .FILL 0
SWDR	.FILL xfe0a
SSEGDR	.FILL xfe12
LEDDR	.FILL xfe16