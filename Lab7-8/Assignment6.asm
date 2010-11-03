.ORIG x3000
readIO 				
	JSR wait
	LDI R0, SWDR
	STI R0, SSEGDR
	AND R1,R1,#0
	STI R1, LEDDR
	BRnzp readIO 

SWDR	.FILL xfe0a
SSEGDR	.FILL xfe12
LEDDR	.FILL xfe16


;; wait for button release
wait	ST R7,SaveR7wait
	ST R3, SaveR3
pressed	 LDI R3,btnptr
	 AND R3,R3,#1
	 BRz pressed
released LDI R3,btnptr
	 ADD R3,R3,#0
	 BRp released

	 LD R3, SaveR3
	 LD R7, SaveR7wait
	 RET

SaveR7wait .FILL 0
SaveR3 .FILL 0
btnptr	.FILL xfe0e
;btnptr	.FILL x300F
.END