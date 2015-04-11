.ORIG x0500
Main	


; Test display
   AND R0, R0, #0
   ADD R0, R0, #1
   LD R3, DISPLAY_BASE
   STR R0, R3, x00
   STR R0, R3, x01
   ADD R0, R0, #1
   STR R0, R3, x02
   STR R0, R3, x03
   ADD R0, R0, #1
   STR R0, R3, x04
   ADD R0, R0, #1
   STR R0, R3, x05

   LEA R0, Prompt    
   PUTS		; output prompt
   LD R2, IO_BASE	; 
   LD R3, MEM_BASE	; 

   LEA R0, Test1
   PUTS		; output prompt

   AND R0, R0, #0
   STR R0, R3, x00     ; write #1 to MEM_BASE offset 0
   LDR R1, R3, x00     ; Load it again to r1

   BRnp Error
   LEA R0, Testok
   PUTS		; output prompt

   LEA R0, Test2
   PUTS		; output prompt

   ADD R1, R1, #1	; increment R1
   STR R1, R3, x00	; store it back
   LDR R0, R3, x00      ; And read it again to R0

   BRnz Error
   LEA R0, Testok
   PUTS		; output prompt

   LEA R0, IOTest 
   PUTS		; output prompt





   IOAgain:  
	LDR R0, R2, x0a              ; Read Switches
	STR R0, R2, x12              ; Write 7Seg
	LDR R0, R2, x0e              ; Read PushBtns
	STR R0, R2, x16              ; Write Leds
   BR IOAgain
        

Error	LEA R0, Testfail 
	PUTS		; output prompt
	BRnzp IOAgain

IO_BASE	.FILL xFE00
DISPLAY_BASE	.FILL xE000
MEM_BASE .FILL x5000

Prompt .STRINGZ "LC3 test program: \n"
Testfail .STRINGZ "BIST failed: \n"
Test1 .STRINGZ "Writing 0 to x5000: "
Testok .STRINGZ " - ok\n"
Test2 .STRINGZ "Writing 1 to x5000:"
IOTest .STRINGZ "Memory tests ok - Entering IO test mode\n"

.END