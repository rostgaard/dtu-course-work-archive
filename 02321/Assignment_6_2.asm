.ORIG x3000

START   LD      R2,Newline
L1      LDI     R3,DSR
        BRzp    L1          ; Loop until Monitor is ready
        STI     R2,DDR      ; Move cursor to new clean line
;
        LEA     R1,Prompt   ; Starting address of prompt string
Loop    LDR     R0,R1,#0    ; Write the input prompt
        BRz     Input       ; End of prompt string
L2      LDI     R3,DSR
        BRzp    L2          ; Loop until Monitor is ready
        STI     R0,DDR      ; Write next prompt character
        ADD     R1,R1,#1    ; Increment Prompt pointer
        BRnzp   Loop        ; Get next prompt character
;
Input   LDI     R3,KBSR
        BRzp    Input       ; Poll until a character is typed
        LDI     R0,KBDR     ; Load input character into R0

	LD      R2,Newline ; Put newline
NL      LDI     R3,DSR
        BRzp    NL          ; Loop until Monitor is ready
        STI     R2,DDR      ; Move cursor to new clean line
;

        LEA     R1,Number   ; Starting address of number string
LoopN   LDR     R4,R1,#0    ; Write the string
        BRz     L3          ; End of string
L2N      LDI     R3,DSR
        BRzp    L2N          ; Loop until Monitor is ready
        STI     R4,DDR      ; Write next character
        ADD     R1,R1,#1    ; Increment char pointer
        BRnzp   LoopN       ; Get next character

L3      LDI     R3,DSR
        BRzp    L3          ; Loop until Monitor is ready
        STI     R0,DDR      ; Echo input character


; Check if number is even
	AND R0,R0,#1
	BRz	iseven
		
isodd
        LEA     R1,Odd   ; Starting address of odd string
LoopO   LDR     R4,R1,#0    ; Write the string
        BRz     START       ; End of even string
L2O     LDI     R3,DSR
        BRzp    L2O          ; Loop until Monitor is ready
        STI     R4,DDR      ; Write next character
        ADD     R1,R1,#1    ; Increment char pointer
        BRnzp   LoopO       ; Get next character
	BRnzp	START
iseven
        LEA     R1,Even   ; Starting address of odd string
LoopE   LDR     R4,R1,#0    ; Write string
        BRz     START       ; End of odd string
L2E     LDI     R3,DSR
        BRzp    L2E          ; Loop until Monitor is ready
        STI     R4,DDR      ; Write next character
        ADD     R1,R1,#1    ; Increment char pointer
        BRnzp   LoopE       ; Get next character
	BRnzp	START
;

DSR     .FILL   xFE04
DDR     .FILL   xFE06
KBSR    .FILL   xFE00
KBDR    .FILL   xFE02
Newline .FILL   x000A       ; ASCII code for newline 
Prompt  .STRINGZ "Input a number:"
Number  .STRINGZ "Number "
Even  .STRINGZ " is even"
Odd  .STRINGZ " is odd"
.END
