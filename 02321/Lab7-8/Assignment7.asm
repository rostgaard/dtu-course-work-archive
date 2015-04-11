;;; Authors: Morten Hillebo (s072923) and Kim Rostgaard Christensen (s084283)
;;; 		Group 2

resultIO	ST R7,SaveR7resultIO	; provide a way back
	ADD R0,R0,#0
	BRz zero
	
	; Now we assume that R0 is positive
	AND R0, R0, #0		; clear R0
	ADD R0, R0, #2		; store bitpattern 00000010 in R0
	BRnzp return

zero	ADD R0,R0,#1		; we know R0 is zero, so we just add 1
	
return	STI R0, LEDDR		; output R0 bitpattern to LED's
	LD R7,SaveR7resultIO
	RET

SaveR7resultIO .FILL 0



