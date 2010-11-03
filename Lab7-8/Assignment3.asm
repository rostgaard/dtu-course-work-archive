;; resultS
resultS	ST R7,SaveR7results	; provide a way back
	ADD R0,R0,#0		; Raise zero flag, on zero
	BRz zero
	BRp pos
	BRnzp retres

	; This is a prime
pos	LEA R0, isPrimeZ
	PUTS
	BRnzp retres

	; This is not a prime
zero LEA R0, isNotPrimeZ
	PUTS
retres	LD R7,SaveR7results
	RET

SaveR7results	.FILL 0
isPrimeZ  .STRINGZ "\nThe number is prime"
isNotPrimeZ  .STRINGZ "\nThe number is not prime"