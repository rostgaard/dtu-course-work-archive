;;; Authors: Morten Hillebo (s072923) and Kim Rostgaard Christensen (s084283)
;;; 		Group 2

wait	ST R7,SaveR7wait	; provide a way back
	ST R3, SaveR3

pressed	 LDI R3,btnptr		
	 AND R3,R3,#1		; mask other buttons
	 BRz pressed		; wait for buttonpress

released LDI R3,btnptr		
	 AND R3,R3,#1		; mask other buttons
	 BRp released		; wait for button release

	 LD R3, SaveR3
	 LD R7, SaveR7wait
	 RET

SaveR7wait .FILL 0
SaveR3 .FILL 0
btnptr	.FILL xfe0e