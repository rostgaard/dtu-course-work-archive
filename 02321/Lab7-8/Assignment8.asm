;;; Authors: Morten Hillebo (s072923) and Kim Rostgaard Christensen (s084283)
;;; 		Group 2
	
.ORIG x3000
;Main program calls three subprocedures in an inifinte loop
main	JSR readIO
	JSR isPrime
	JSR resultIO
	BRnzp main	;mainloop

;; readIO
readIO 	ST R7,SaveR7readIO	; provide a way back
	JSR wait
	LDI R0, SWDR		; sample bitpattern in switches
	STI R0, SSEGDR		; output the hex value to SSEG
	AND R1,R1,#0		; Clear r1
	STI R1, LEDDR		; - in order to turn off all LED's
	LD R7,SaveR7readIO
	RET

SaveR7readIO .FILL 0
SWDR	.FILL xfe0a
SSEGDR	.FILL xfe12
LEDDR	.FILL xfe16


;; wait for button release
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

;; resultIO
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

.END
