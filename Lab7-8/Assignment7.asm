;; resultIO
resultIO	ST R7,SaveR7resultIO
	ADD R0,R0,#1
	STI R0, LEDDR
	BRnzp return

zero	ADD R0,R0,#1
	STI R0, LEDDR
	
return	LD R7,SaveR7resultIO
	RET

SaveR7resultIO .FILL 0



