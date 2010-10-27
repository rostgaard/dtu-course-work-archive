.ORIG 	x3000
IsPrime	LEA R2, primes 	; prime_ptr
	NOT R0,R0
	ADD R0,R0,#1
again	LDR R1,R2,#0
	ADD R1,R1,R0
	BRp notok
	BRz ok
	ADD R2,R2,#1	; increment prime_ptr
	BRnzp again

notok	AND R0,R0,#0
	RET

ok	AND R0,R0,#0
	ADD R0,R0,#1
	RET


primes	.FILL #2
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
.END