.ORIG x3000
resultS	ADD R0,R0,#0
	BRz noPrime
	BRp prime
	BRn STOP

prime	LEA R0, isPrimeZ
	PUTS
	BRnzp STOP

noPrime LEA R0, isNotPrimeZ
	PUTS
	BRnzp STOP

isPrimeZ  .STRINGZ "The number is prime"
isNotPrimeZ  .STRINGZ "The number is not prime"

STOP	.FILL 0
.END