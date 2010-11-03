.orig	x3000

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