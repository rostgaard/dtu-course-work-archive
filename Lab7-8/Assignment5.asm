.orig	x3000

wait	 ST R3, SaveR3
pressed	 LDI R3,btnptr
	 AND R3,R3,#1
	 BRz pressed
released LDI R3,btnptr
	 ADD R3,R3,#0
	 BRp released
	 LD R3, SaveR3
	 RET

SaveR3 .FILL 0
btnptr	.fill xfe0e
