; isPrime
isPrime	ST R7,SaveR7primes	; provide a way back
	LEA R2, primes 		; prime_ptr
	NOT R0,R0
	ADD R0,R0,#1		; Two's compliment of input number
again	LDR R1,R2,#0
	ADD R1,R1,R0
	BRp notok		; only larger numbers than input numbers remain, this is not a prime
	BRz ok			; the number matches the current one in the array
	ADD R2,R2,#1		; else, increment prime_ptr and try next pointer
	BRnzp again

notok	AND R0,R0,#0		; store 0 in R0
	LD R7,SaveR7primes
	RET

ok	AND R0,R0,#0		; reset R0
	ADD R0,R0,#1		; store 1
	LD R7,SaveR7primes
	RET

primes	.FILL #2	; Array of primes
	.FILL #3 
	.FILL #5 
	.FILL #7 
	.FILL #11 
	.FILL #13 
	.FILL #17 
	.FILL #19
	.FILL #23
	.FILL #29
	.FILL #31
	.FILL #37
	.FILL #41
	.FILL #43
	.FILL #47
	.FILL #53
	.FILL #59
	.FILL #61
	.FILL #67
	.FILL #71
	.FILL #73
	.FILL #79
	.FILL #83
	.FILL #89
	.FILL #97
	.FILL #101

SaveR7primes	.FILL 0