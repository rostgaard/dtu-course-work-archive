;##############################################################################
;# This is first version of LC3 OS code. It contains:
;#   0x0000-0x00ff  Trap vector table
;#   0x0100-0x01ff  Interrupt vector table
;#   0x0200-0x03ff  System code (boot + traps)
;#           Note:  This part of memory can be made read-only!
;#          0x0200  Simple boot code to program device over serial port after reset.
;#   0x0400-0x04ff  ReadWrite section of System reserved memory.
;#
;#   0x0500-......  Empty user application which prints programming instructions
;# 
;# The PC should start from "0x0500"
;# 
;#
;# The idea and the initial code is based on lc3os.asm from lc3sim
;#  by "Steven S. Lumetta."
;#
;##############################################################################

	.ORIG x0000
; the TRAP vector table

	.FILL BAD_TRAP	; x00
	.FILL BAD_TRAP	; x01
	.FILL BAD_TRAP	; x02
	.FILL BAD_TRAP	; x03
	.FILL BAD_TRAP	; x04
	.FILL BAD_TRAP	; x05
	.FILL BAD_TRAP	; x06
	.FILL BAD_TRAP	; x07
	.FILL BAD_TRAP	; x08
	.FILL BAD_TRAP	; x09
	.FILL BAD_TRAP	; x0A
	.FILL BAD_TRAP	; x0B
	.FILL BAD_TRAP	; x0C
	.FILL BAD_TRAP	; x0D
	.FILL BAD_TRAP	; x0E
	.FILL BAD_TRAP	; x0F
	.FILL BAD_TRAP	; x10
	.FILL BAD_TRAP	; x11
	.FILL TRAP_SSEG	; x12
	.FILL BAD_TRAP	; x13
	.FILL BAD_TRAP	; x14
	.FILL BAD_TRAP	; x15
	.FILL BAD_TRAP	; x16
	.FILL BAD_TRAP	; x17
	.FILL BAD_TRAP	; x18
	.FILL BAD_TRAP	; x19
	.FILL BAD_TRAP	; x1A
	.FILL BAD_TRAP	; x1B
	.FILL BAD_TRAP	; x1C
	.FILL BAD_TRAP	; x1D
	.FILL BAD_TRAP	; x1E
	.FILL BAD_TRAP	; x1F
	.FILL TRAP_GETC	; x20
	.FILL TRAP_OUT	; x21
	.FILL TRAP_PUTS	; x22
	.FILL TRAP_IN	; x23
	.FILL TRAP_PUTSP ; x24
	.FILL TRAP_HALT	; x25
	.FILL BAD_TRAP	; x26
	.FILL BAD_TRAP	; x27
	.FILL BAD_TRAP	; x28
	.FILL BAD_TRAP	; x29
	.FILL BAD_TRAP	; x2A
	.FILL BAD_TRAP	; x2B
	.FILL BAD_TRAP	; x2C
	.FILL BAD_TRAP	; x2D
	.FILL BAD_TRAP	; x2E
	.FILL BAD_TRAP	; x2F
	.FILL BAD_TRAP	; x30
	.FILL BAD_TRAP	; x31
	.FILL BAD_TRAP	; x32
	.FILL BAD_TRAP	; x33
	.FILL BAD_TRAP	; x34
	.FILL BAD_TRAP	; x35
	.FILL BAD_TRAP	; x36
	.FILL BAD_TRAP	; x37
	.FILL BAD_TRAP	; x38
	.FILL BAD_TRAP	; x39
	.FILL BAD_TRAP	; x3A
	.FILL BAD_TRAP	; x3B
	.FILL BAD_TRAP	; x3C
	.FILL BAD_TRAP	; x3D
	.FILL BAD_TRAP	; x3E
	.FILL BAD_TRAP	; x3F
	.FILL BAD_TRAP	; x40
	.FILL BAD_TRAP	; x41
	.FILL BAD_TRAP	; x42
	.FILL BAD_TRAP	; x43
	.FILL BAD_TRAP	; x44
	.FILL BAD_TRAP	; x45
	.FILL BAD_TRAP	; x46
	.FILL BAD_TRAP	; x47
	.FILL BAD_TRAP	; x48
	.FILL BAD_TRAP	; x49
	.FILL BAD_TRAP	; x4A
	.FILL BAD_TRAP	; x4B
	.FILL BAD_TRAP	; x4C
	.FILL BAD_TRAP	; x4D
	.FILL BAD_TRAP	; x4E
	.FILL BAD_TRAP	; x4F
	.FILL BAD_TRAP	; x50
	.FILL BAD_TRAP	; x51
	.FILL BAD_TRAP	; x52
	.FILL BAD_TRAP	; x53
	.FILL BAD_TRAP	; x54
	.FILL BAD_TRAP	; x55
	.FILL BAD_TRAP	; x56
	.FILL BAD_TRAP	; x57
	.FILL BAD_TRAP	; x58
	.FILL BAD_TRAP	; x59
	.FILL BAD_TRAP	; x5A
	.FILL BAD_TRAP	; x5B
	.FILL BAD_TRAP	; x5C
	.FILL BAD_TRAP	; x5D
	.FILL BAD_TRAP	; x5E
	.FILL BAD_TRAP	; x5F
	.FILL BAD_TRAP	; x60
	.FILL BAD_TRAP	; x61
	.FILL BAD_TRAP	; x62
	.FILL BAD_TRAP	; x63
	.FILL BAD_TRAP	; x64
	.FILL BAD_TRAP	; x65
	.FILL BAD_TRAP	; x66
	.FILL BAD_TRAP	; x67
	.FILL BAD_TRAP	; x68
	.FILL BAD_TRAP	; x69
	.FILL BAD_TRAP	; x6A
	.FILL BAD_TRAP	; x6B
	.FILL BAD_TRAP	; x6C
	.FILL BAD_TRAP	; x6D
	.FILL BAD_TRAP	; x6E
	.FILL BAD_TRAP	; x6F
	.FILL BAD_TRAP	; x70
	.FILL BAD_TRAP	; x71
	.FILL BAD_TRAP	; x72
	.FILL BAD_TRAP	; x73
	.FILL BAD_TRAP	; x74
	.FILL BAD_TRAP	; x75
	.FILL BAD_TRAP	; x76
	.FILL BAD_TRAP	; x77
	.FILL BAD_TRAP	; x78
	.FILL BAD_TRAP	; x79
	.FILL BAD_TRAP	; x7A
	.FILL BAD_TRAP	; x7B
	.FILL BAD_TRAP	; x7C
	.FILL BAD_TRAP	; x7D
	.FILL BAD_TRAP	; x7E
	.FILL BAD_TRAP	; x7F
	.FILL BAD_TRAP	; x80
	.FILL BAD_TRAP	; x81
	.FILL BAD_TRAP	; x82
	.FILL BAD_TRAP	; x83
	.FILL BAD_TRAP	; x84
	.FILL BAD_TRAP	; x85
	.FILL BAD_TRAP	; x86
	.FILL BAD_TRAP	; x87
	.FILL BAD_TRAP	; x88
	.FILL BAD_TRAP	; x89
	.FILL BAD_TRAP	; x8A
	.FILL BAD_TRAP	; x8B
	.FILL BAD_TRAP	; x8C
	.FILL BAD_TRAP	; x8D
	.FILL BAD_TRAP	; x8E
	.FILL BAD_TRAP	; x8F
	.FILL BAD_TRAP	; x90
	.FILL BAD_TRAP	; x91
	.FILL BAD_TRAP	; x92
	.FILL BAD_TRAP	; x93
	.FILL BAD_TRAP	; x94
	.FILL BAD_TRAP	; x95
	.FILL BAD_TRAP	; x96
	.FILL BAD_TRAP	; x97
	.FILL BAD_TRAP	; x98
	.FILL BAD_TRAP	; x99
	.FILL BAD_TRAP	; x9A
	.FILL BAD_TRAP	; x9B
	.FILL BAD_TRAP	; x9C
	.FILL BAD_TRAP	; x9D
	.FILL BAD_TRAP	; x9E
	.FILL BAD_TRAP	; x9F
	.FILL BAD_TRAP	; xA0
	.FILL BAD_TRAP	; xA1
	.FILL BAD_TRAP	; xA2
	.FILL BAD_TRAP	; xA3
	.FILL BAD_TRAP	; xA4
	.FILL BAD_TRAP	; xA5
	.FILL BAD_TRAP	; xA6
	.FILL BAD_TRAP	; xA7
	.FILL BAD_TRAP	; xA8
	.FILL BAD_TRAP	; xA9
	.FILL BAD_TRAP	; xAA
	.FILL BAD_TRAP	; xAB
	.FILL BAD_TRAP	; xAC
	.FILL BAD_TRAP	; xAD
	.FILL BAD_TRAP	; xAE
	.FILL BAD_TRAP	; xAF
	.FILL BAD_TRAP	; xB0
	.FILL BAD_TRAP	; xB1
	.FILL BAD_TRAP	; xB2
	.FILL BAD_TRAP	; xB3
	.FILL BAD_TRAP	; xB4
	.FILL BAD_TRAP	; xB5
	.FILL BAD_TRAP	; xB6
	.FILL BAD_TRAP	; xB7
	.FILL BAD_TRAP	; xB8
	.FILL BAD_TRAP	; xB9
	.FILL BAD_TRAP	; xBA
	.FILL BAD_TRAP	; xBB
	.FILL BAD_TRAP	; xBC
	.FILL BAD_TRAP	; xBD
	.FILL BAD_TRAP	; xBE
	.FILL BAD_TRAP	; xBF
	.FILL BAD_TRAP	; xC0
	.FILL BAD_TRAP	; xC1
	.FILL BAD_TRAP	; xC2
	.FILL BAD_TRAP	; xC3
	.FILL BAD_TRAP	; xC4
	.FILL BAD_TRAP	; xC5
	.FILL BAD_TRAP	; xC6
	.FILL BAD_TRAP	; xC7
	.FILL BAD_TRAP	; xC8
	.FILL BAD_TRAP	; xC9
	.FILL BAD_TRAP	; xCA
	.FILL BAD_TRAP	; xCB
	.FILL BAD_TRAP	; xCC
	.FILL BAD_TRAP	; xCD
	.FILL BAD_TRAP	; xCE
	.FILL BAD_TRAP	; xCF
	.FILL BAD_TRAP	; xD0
	.FILL BAD_TRAP	; xD1
	.FILL BAD_TRAP	; xD2
	.FILL BAD_TRAP	; xD3
	.FILL BAD_TRAP	; xD4
	.FILL BAD_TRAP	; xD5
	.FILL BAD_TRAP	; xD6
	.FILL BAD_TRAP	; xD7
	.FILL BAD_TRAP	; xD8
	.FILL BAD_TRAP	; xD9
	.FILL BAD_TRAP	; xDA
	.FILL BAD_TRAP	; xDB
	.FILL BAD_TRAP	; xDC
	.FILL BAD_TRAP	; xDD
	.FILL BAD_TRAP	; xDE
	.FILL BAD_TRAP	; xDF
	.FILL BAD_TRAP	; xE0
	.FILL BAD_TRAP	; xE1
	.FILL BAD_TRAP	; xE2
	.FILL BAD_TRAP	; xE3
	.FILL BAD_TRAP	; xE4
	.FILL BAD_TRAP	; xE5
	.FILL BAD_TRAP	; xE6
	.FILL BAD_TRAP	; xE7
	.FILL BAD_TRAP	; xE8
	.FILL BAD_TRAP	; xE9
	.FILL BAD_TRAP	; xEA
	.FILL BAD_TRAP	; xEB
	.FILL BAD_TRAP	; xEC
	.FILL BAD_TRAP	; xED
	.FILL BAD_TRAP	; xEE
	.FILL BAD_TRAP	; xEF
	.FILL BAD_TRAP	; xF0
	.FILL BAD_TRAP	; xF1
	.FILL BAD_TRAP	; xF2
	.FILL BAD_TRAP	; xF3
	.FILL BAD_TRAP	; xF4
	.FILL BAD_TRAP	; xF5
	.FILL BAD_TRAP	; xF6
	.FILL BAD_TRAP	; xF7
	.FILL BAD_TRAP	; xF8
	.FILL BAD_TRAP	; xF9
	.FILL BAD_TRAP	; xFA
	.FILL BAD_TRAP	; xFB
	.FILL BAD_TRAP	; xFC
	.FILL BAD_TRAP	; xFD
	.FILL BAD_TRAP	; xFE
	.FILL BAD_TRAP	; xFF

; the interrupt vector table
	.FILL INT_PRIV	; x00
	.FILL INT_ILL	; x01
	.FILL BAD_INT	; x02
	.FILL BAD_INT	; x03
	.FILL BAD_INT	; x04
	.FILL BAD_INT	; x05
	.FILL BAD_INT	; x06
	.FILL BAD_INT	; x07
	.FILL BAD_INT	; x08
	.FILL BAD_INT	; x09
	.FILL BAD_INT	; x0A
	.FILL BAD_INT	; x0B
	.FILL BAD_INT	; x0C
	.FILL BAD_INT	; x0D
	.FILL BAD_INT	; x0E
	.FILL BAD_INT	; x0F
	.FILL BAD_INT	; x10
	.FILL BAD_INT	; x11
	.FILL BAD_INT	; x12
	.FILL BAD_INT	; x13
	.FILL BAD_INT	; x14
	.FILL BAD_INT	; x15
	.FILL BAD_INT	; x16
	.FILL BAD_INT	; x17
	.FILL BAD_INT	; x18
	.FILL BAD_INT	; x19
	.FILL BAD_INT	; x1A
	.FILL BAD_INT	; x1B
	.FILL BAD_INT	; x1C
	.FILL BAD_INT	; x1D
	.FILL BAD_INT	; x1E
	.FILL BAD_INT	; x1F
	.FILL BAD_INT	; x20
	.FILL BAD_INT	; x21
	.FILL BAD_INT	; x22
	.FILL BAD_INT	; x23
	.FILL BAD_INT   ; x24
	.FILL BAD_INT	; x25
	.FILL BAD_INT	; x26
	.FILL BAD_INT	; x27
	.FILL BAD_INT	; x28
	.FILL BAD_INT	; x29
	.FILL BAD_INT	; x2A
	.FILL BAD_INT	; x2B
	.FILL BAD_INT	; x2C
	.FILL BAD_INT	; x2D
	.FILL BAD_INT	; x2E
	.FILL BAD_INT	; x2F
	.FILL BAD_INT	; x30
	.FILL BAD_INT	; x31
	.FILL BAD_INT	; x32
	.FILL BAD_INT	; x33
	.FILL BAD_INT	; x34
	.FILL BAD_INT	; x35
	.FILL BAD_INT	; x36
	.FILL BAD_INT	; x37
	.FILL BAD_INT	; x38
	.FILL BAD_INT	; x39
	.FILL BAD_INT	; x3A
	.FILL BAD_INT	; x3B
	.FILL BAD_INT	; x3C
	.FILL BAD_INT	; x3D
	.FILL BAD_INT	; x3E
	.FILL BAD_INT	; x3F
	.FILL BAD_INT	; x40
	.FILL BAD_INT	; x41
	.FILL BAD_INT	; x42
	.FILL BAD_INT	; x43
	.FILL BAD_INT	; x44
	.FILL BAD_INT	; x45
	.FILL BAD_INT	; x46
	.FILL BAD_INT	; x47
	.FILL BAD_INT	; x48
	.FILL BAD_INT	; x49
	.FILL BAD_INT	; x4A
	.FILL BAD_INT	; x4B
	.FILL BAD_INT	; x4C
	.FILL BAD_INT	; x4D
	.FILL BAD_INT	; x4E
	.FILL BAD_INT	; x4F
	.FILL BAD_INT	; x50
	.FILL BAD_INT	; x51
	.FILL BAD_INT	; x52
	.FILL BAD_INT	; x53
	.FILL BAD_INT	; x54
	.FILL BAD_INT	; x55
	.FILL BAD_INT	; x56
	.FILL BAD_INT	; x57
	.FILL BAD_INT	; x58
	.FILL BAD_INT	; x59
	.FILL BAD_INT	; x5A
	.FILL BAD_INT	; x5B
	.FILL BAD_INT	; x5C
	.FILL BAD_INT	; x5D
	.FILL BAD_INT	; x5E
	.FILL BAD_INT	; x5F
	.FILL BAD_INT	; x60
	.FILL BAD_INT	; x61
	.FILL BAD_INT	; x62
	.FILL BAD_INT	; x63
	.FILL BAD_INT	; x64
	.FILL BAD_INT	; x65
	.FILL BAD_INT	; x66
	.FILL BAD_INT	; x67
	.FILL BAD_INT	; x68
	.FILL BAD_INT	; x69
	.FILL BAD_INT	; x6A
	.FILL BAD_INT	; x6B
	.FILL BAD_INT	; x6C
	.FILL BAD_INT	; x6D
	.FILL BAD_INT	; x6E
	.FILL BAD_INT	; x6F
	.FILL BAD_INT	; x70
	.FILL BAD_INT	; x71
	.FILL BAD_INT	; x72
	.FILL BAD_INT	; x73
	.FILL BAD_INT	; x74
	.FILL BAD_INT	; x75
	.FILL BAD_INT	; x76
	.FILL BAD_INT	; x77
	.FILL BAD_INT	; x78
	.FILL BAD_INT	; x79
	.FILL BAD_INT	; x7A
	.FILL BAD_INT	; x7B
	.FILL BAD_INT	; x7C
	.FILL BAD_INT	; x7D
	.FILL BAD_INT	; x7E
	.FILL BAD_INT	; x7F
	.FILL BAD_INT	; x80
	.FILL BAD_INT	; x81
	.FILL BAD_INT	; x82
	.FILL BAD_INT	; x83
	.FILL BAD_INT	; x84
	.FILL BAD_INT	; x85
	.FILL BAD_INT	; x86
	.FILL BAD_INT	; x87
	.FILL BAD_INT	; x88
	.FILL BAD_INT	; x89
	.FILL BAD_INT	; x8A
	.FILL BAD_INT	; x8B
	.FILL BAD_INT	; x8C
	.FILL BAD_INT	; x8D
	.FILL BAD_INT	; x8E
	.FILL BAD_INT	; x8F
	.FILL BAD_INT	; x90
	.FILL BAD_INT	; x91
	.FILL BAD_INT	; x92
	.FILL BAD_INT	; x93
	.FILL BAD_INT	; x94
	.FILL BAD_INT	; x95
	.FILL BAD_INT	; x96
	.FILL BAD_INT	; x97
	.FILL BAD_INT	; x98
	.FILL BAD_INT	; x99
	.FILL BAD_INT	; x9A
	.FILL BAD_INT	; x9B
	.FILL BAD_INT	; x9C
	.FILL BAD_INT	; x9D
	.FILL BAD_INT	; x9E
	.FILL BAD_INT	; x9F
	.FILL BAD_INT	; xA0
	.FILL BAD_INT	; xA1
	.FILL BAD_INT	; xA2
	.FILL BAD_INT	; xA3
	.FILL BAD_INT	; xA4
	.FILL BAD_INT	; xA5
	.FILL BAD_INT	; xA6
	.FILL BAD_INT	; xA7
	.FILL BAD_INT	; xA8
	.FILL BAD_INT	; xA9
	.FILL BAD_INT	; xAA
	.FILL BAD_INT	; xAB
	.FILL BAD_INT	; xAC
	.FILL BAD_INT	; xAD
	.FILL BAD_INT	; xAE
	.FILL BAD_INT	; xAF
	.FILL BAD_INT	; xB0
	.FILL BAD_INT	; xB1
	.FILL BAD_INT	; xB2
	.FILL BAD_INT	; xB3
	.FILL BAD_INT	; xB4
	.FILL BAD_INT	; xB5
	.FILL BAD_INT	; xB6
	.FILL BAD_INT	; xB7
	.FILL BAD_INT	; xB8
	.FILL BAD_INT	; xB9
	.FILL BAD_INT	; xBA
	.FILL BAD_INT	; xBB
	.FILL BAD_INT	; xBC
	.FILL BAD_INT	; xBD
	.FILL BAD_INT	; xBE
	.FILL BAD_INT	; xBF
	.FILL BAD_INT	; xC0
	.FILL BAD_INT	; xC1
	.FILL BAD_INT	; xC2
	.FILL BAD_INT	; xC3
	.FILL BAD_INT	; xC4
	.FILL BAD_INT	; xC5
	.FILL BAD_INT	; xC6
	.FILL BAD_INT	; xC7
	.FILL BAD_INT	; xC8
	.FILL BAD_INT	; xC9
	.FILL BAD_INT	; xCA
	.FILL BAD_INT	; xCB
	.FILL BAD_INT	; xCC
	.FILL BAD_INT	; xCD
	.FILL BAD_INT	; xCE
	.FILL BAD_INT	; xCF
	.FILL BAD_INT	; xD0
	.FILL BAD_INT	; xD1
	.FILL BAD_INT	; xD2
	.FILL BAD_INT	; xD3
	.FILL BAD_INT	; xD4
	.FILL BAD_INT	; xD5
	.FILL BAD_INT	; xD6
	.FILL BAD_INT	; xD7
	.FILL BAD_INT	; xD8
	.FILL BAD_INT	; xD9
	.FILL BAD_INT	; xDA
	.FILL BAD_INT	; xDB
	.FILL BAD_INT	; xDC
	.FILL BAD_INT	; xDD
	.FILL BAD_INT	; xDE
	.FILL BAD_INT	; xDF
	.FILL BAD_INT	; xE0
	.FILL BAD_INT	; xE1
	.FILL BAD_INT	; xE2
	.FILL BAD_INT	; xE3
	.FILL BAD_INT	; xE4
	.FILL BAD_INT	; xE5
	.FILL BAD_INT	; xE6
	.FILL BAD_INT	; xE7
	.FILL BAD_INT	; xE8
	.FILL BAD_INT	; xE9
	.FILL BAD_INT	; xEA
	.FILL BAD_INT	; xEB
	.FILL BAD_INT	; xEC
	.FILL BAD_INT	; xED
	.FILL BAD_INT	; xEE
	.FILL BAD_INT	; xEF
	.FILL BAD_INT	; xF0
	.FILL BAD_INT	; xF1
	.FILL BAD_INT	; xF2
	.FILL BAD_INT	; xF3
	.FILL BAD_INT	; xF4
	.FILL BAD_INT	; xF5
	.FILL BAD_INT	; xF6
	.FILL BAD_INT	; xF7
	.FILL BAD_INT	; xF8
	.FILL BAD_INT	; xF9
	.FILL BAD_INT	; xFA
	.FILL BAD_INT	; xFB
	.FILL BAD_INT	; xFC
	.FILL BAD_INT	; xFD
	.FILL RTI_NOT_SUPP	; xFE
	.FILL UNDEFINED	; xFF

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;; [ Sensitive Code! ] ;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;; [ Don't owerwrite ] ;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;     x0200:x02FF     ;;;;;;;;;;;;;;;
;
; To make it simple and avoid this code owerwriting itself during
; execution, it can be made read-only in the memory block.
;
; This is System code that allows to load memory space with data
; fed over serial port (It activates when special button is pressed)
; The words are expected in network order (big endian).
;
; Upon activation the following commands can be executed from Host:
;	<ESC>Q	: Query status
;			  Used by Host to check availability.
;			  "Ready." is send back.
;	<ESC>G	: Ged memory content. Followed by:
;				Word	Meaning
;				-------------------------------
;				 0 :	Number of words in the transfer (N)
;				 1 :	Offset from which to start reading the memory (O)
;             LC3 will respond with (N) words.
;	<ESC>U	: Upload. Followed by:
;				Word	Meaning
;				-------------------------------
;				 0 :	Number of words in the transfer (N)
;				 1 :	Offset from which to start writing to the memory (O)
;				...:	N words follows
;	<ESC>S	: Start program. Followed by:
;				Word	Meaning
;				-------------------------------
;				 0 :	Program start address
;
;;;;;;;;;;;;;;;;;;;;;;;;;;;
;  Ideas for the future:
;
;	<ESC>R	: Return
;             Jump to return address storred on the stack.
;
;	<ESC>?	: Show regs, other debug info
;
;
;

PROGRAMMER_START
;;; In new code we are allways started from 0x0200 when program button is pressed
;;; And start from MAIN_START otherwise


; We are in serial boot mode, Notify the user (send stdout msg, set led patterns)
   LEA R0, BootAccept_msg
   jsr OutputMsg

   LD  R0, LED_PATT
   STI R0, IO_LED 
   LD  R0, SSEG_PATT
   STI R0, IO_SSEG

   BR  wait_cmd

IO_LED    .FILL xFE16
IO_SSEG   .FILL xFE12
IO_STDIN_BASE   .FILL xFe00
; Led patterns for user notifiaction
LED_PATT  .FILL x00FF
SSEG_PATT .FILL xcccc
BootAccept_msg  .STRINGZ "\n--- Waiting for program...\n"

; Supported commands
ESCAPE_cmp		.FILL x-001B
CMD_QUERY_cmp	.FILL x-0051
CMD_GET_MEM_cmp	.FILL x-0047
CMD_UPLOAD_cmp	.FILL x-0055
CMD_START_cmp	.FILL x-0053


wait_cmd
	; Wait for Escape
	jsr	LoadByteFromSerial	
	ld	r1, ESCAPE_cmp
	add	r0, r0, r1
	brNP wait_cmd

	; Escape received, check command
	jsr	LoadByteFromSerial	

	ld	r1, CMD_QUERY_cmp
	add	r1, r0, r1
	brZ do_query_cmd

	ld	r1, CMD_GET_MEM_cmp
	add	r1, r0, r1
	brZ do_get_mem_cmd

	ld	r1, CMD_UPLOAD_cmp
	add	r1, r0, r1
	brZ do_upload_cmd

	ld	r1, CMD_START_cmp
	add	r1, r0, r1
	brZ do_start_cmd

	br wait_cmd

	;; ; If more commands are needed: Use jump table
	;;
	;; lea r2, CMD_QUERY_cmp
	;;
	;; check_next_cmd
	;;	ldr r1, r2, 0
	;;	add r1, r0, r1
	;;	add r2, r2, 1
	;;	brNP check_next_cmd
	;;
	;;	; command found
	;;	add r2, CMD_COUNT
	;;	jmp r2
	


;;;;;;;;;;;;;;; QUERY COMMAND
do_query_cmd
	lea	r0, QueryResp_msg
	jsr	OutputMsg
	br wait_cmd

QueryResp_msg  .STRINGZ "Ready."
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;; GET MEMORY CONTENT COMMAND
do_get_mem_cmd
  ; REG conventions:
  ; R4	Count words transfered
  ; R5	Write Offset

	JSR LoadWordFromSerial	; Receive Count
	ADD R4, R0, 0
	STI R0, IO_SSEG         ; Notification

	JSR LoadWordFromSerial	; Receive Write offset
	ADD R5, R0, 0
	STI R0, IO_SSEG         ; Notification

	; Read memory
ReadWord	;; Watch Out! No range checking is made
	STI R5, IO_SSEG         ; Notification
	LDR R0, R5, 0
	JSR SendWordToSerial
	ADD R5, R5, 1
	ADD R4, R4, -1
	BRp ReadWord

	br wait_cmd
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;; UPLOAD COMMAND
do_upload_cmd
; LoadMemFromSerial
  ; REG conventions:
  ; R4	Count words transfered
  ; R5	Write Offset

	JSR LoadWordFromSerial	; Receive Count
	ADD R4, R0, 0
	STI R0, IO_SSEG         ; Notification
    
	JSR LoadWordFromSerial	; Receive Write offset
	ADD R5, R0, 0
	STI R0, IO_SSEG         ; Notification

	; Write memory
WriteWord	;; Watch Out! No range checking is made
	JSR LoadWordFromSerial
	STI R5, IO_SSEG         ; Notification
	STR R0, R5, 0
	ADD R5, R5, 1
	ADD R4, R4, -1
	BRp WriteWord

;;; New code, just jumps to the begining of programming code to wait for new programming chunks
	lea	r0, ProgrammingDone_msg
	jsr	OutputMsg
	
	br wait_cmd

ProgrammingDone_msg  .STRINGZ "\nProgramming done.\n--- Press reset(ENTER push-button) or program next block...\n"
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;;;;;;;;;;;;;;; START COMMAND
do_start_cmd
; We are done, Notify the user by clearing Leds
    AND R0, R0, 0
    STI R0, IO_LED
    STI R0, IO_SSEG

	lea	r0, Starting_msg
	jsr	OutputMsg
	
	; Fetch the program start address
	JSR LoadWordFromSerial
	jmp	r0

Starting_msg  .STRINGZ "\nJumping to user code.\n"
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



;;;;;;;;;;;;;;;;;;;;
; Output Message pointed by R0 to stdout (equivalent of PUTS)
;-------------------
; IN:	R0 points start address of unpacked string
; KILL:	R0, R1, R2
;-------------------
STDOUT_s .FILL xFE04
STDOUT_d .FILL xFE06
OutputMsg
	ADD R1,R0,#0		; move string pointer (R0) into R1

 out_loop
	LDR R0,R1,#0		; write characters in string using OUT
	BRz out_done
	LDI R2, STDOUT_s 
	BRzp -2		; wait for the stdout to be ready
	STI R0, STDOUT_d

	ADD R1,R1,#1
	BRnzp out_loop 

  out_done
   RET

;;;;;;;;;;;;;;;;;;;;
; LoadByteFromSerial
;-------------------
; IN:	--
; OUT:	R0 holds byte just read
; KILL:	R2
;-------------------
	
LoadByteFromSerial
	LD R2, IO_STDIN_BASE	; store IO_STDIN_BASE and keep it there while in this proc 

	LDR R0, R2, 0
	BRzp -2		; poll until next word available
	LDR R0, R2, 2
	RET


	
;;;;;;;;;;;;;;;;;;;;
; LoadWordFromSerial
;-------------------
; IN:	--
; OUT:	R0 holds word just read
; KILL:	R1, R2
;-------------------
;					proc_size:14
;LOCALS:
; Used to detect that shift has been performed 7 times
  Bit9	.FILL	x0100
	

LoadWordFromSerial
	LD R2, IO_STDIN_BASE	; store IO_STDIN_BASE and keep it there while in this proc 

	; read the First byte (most significant in LC3)
	LDR R0, R2, 0
	BRzp -2		; poll until next word available
	LDR R0, R2, 2

	; read the Second byte
	LDR R1, R2, 0
	BRzp -2		; poll until next word available
	LDR R1, R2, 2
	
	SLL R0, R0, 8
	; form result (concat bytes)
	ADD R0, R1, R0
	ret

;;;;;;;;;;;;;;;;;;;;
; SendWordToSerial
;-------------------
; IN:	R0 holds word to be send
; OUT:	--
; KILL:	R1
;-------------------
;
;LOCALS:

SendWordToSerial

	; Send the First byte (most significant in LC3)
	LDI R1, STDOUT_s
	BRzp -2		; poll until UART ready
	SRA R1, R0, 8
	STI R1, STDOUT_d

	; Send the Second byte
	LDI R1, STDOUT_s
	BRzp -2		; poll until UART ready
	STI R0, STDOUT_d

	ret

PROGRAMMER_unused .BLKWTO x0300

;;;;;;;;;;;;;;;;;;     x0200:x02FF     ;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;; [ Sensitive Code! ] ;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;       OS CODE
;;  Currently only Traps
;;  Might add debug stubs in future
;;  The part from 0x0300-0x03ff should be read only
;;  ReadWrite data can be stored in 0x0400-0x04ff

OS_START

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Implementation of TRAP and Interrupt handlers:

TRAP_HALT	
	ST R7,OS_HALT_R7
	; an infinite loop of lowering OS_MCR's MSB
	LEA R0,TRAP_HALT_msg	; give a warning
	PUTS
	LDI R0,OS_MCR		; halt the machine
	LD R1,MASK_HI
	AND R0,R0,R1
	STI R0,OS_MCR
	LD R7,OS_HALT_R7

	ret

TRAP_HALT_msg	.STRINGZ "\n\n--- halting the LC-3 ---\n\n"
BAD_TRAP_msg	.STRINGZ "\n\n--- undefined trap executed ---\n\n"
BAD_TRAP_patt	.FILL xEEEE


BAD_TRAP
   ;; show Bad trap notice
	ld  R0, BAD_TRAP_patt
  bad_trap_int
	sti R0, OS_SSEGD
	
	br TRAP_HALT


	; print an error message, then HALT
	LEA R0,BAD_TRAP_msg	; give an error message
	PUTS
	BRnzp TRAP_HALT		; execute HALT

		  
	; interrupts aren't really defined, since privilege doesn't
	; quite work
INT_PRIV
INT_ILL		
BAD_INT	
   ;; show Bad trap notice
	AND R0, R0, 0
	ADD R0, R0, -3
	BR bad_trap_int
	

RTI_NOT_SUPP		 
   ;; show Bad trap notice
	AND R0, R0, 0
	ADD R0, R0, -2
	BR bad_trap_int

UNDEFINED
   ;; show Bad trap notice
	AND R0, R0, 0
	ADD R0, R0, -1
	BR bad_trap_int


TRAP_SSEG
	STI R0,OS_SSEGD		; write the character and return
	RET


TRAP_GETC
	LDI R0,OS_KBSR		; wait for a keystroke
	BRzp TRAP_GETC
	LDI R0,OS_KBDR		; read it and return
	RET


TRAP_OUT
	ST R1,TOUT_R1		; save R1
TRAP_OUT_WAIT
	LDI R1,OS_DSR		; wait for the display to be ready
	BRzp TRAP_OUT_WAIT
	STI R0,OS_DDR		; write the character and return
	LD R1,TOUT_R1		; restore R1
	RET

TRAP_PUTS
	ST R0,OS_R0		; save R0, R1, and R7
	ST R1,OS_R1
	ST R7,OS_R7
	ADD R1,R0,#0		; move string pointer (R0) into R1

TRAP_PUTS_LOOP
	LDR R0,R1,#0		; write characters in string using OUT
	BRz TRAP_PUTS_DONE
	OUT
	ADD R1,R1,#1
	BRnzp TRAP_PUTS_LOOP

TRAP_PUTS_DONE
	LD R0,OS_R0		; restore R0, R1, and R7
	LD R1,OS_R1
	LD R7,OS_R7
	RET

TRAP_IN
	; (no need to save R0, since we return result in it)
	ST R1,TIN_R1		; save R1
	ST R7,TIN_R7		; save R7

	LEA R0,TRAP_IN_msg	; prompt for input
	PUTS
	GETC			; read a character
	OUT			; echo back to monitor
	ST R0,OS_R0		; save the character
	AND R0,R0,#0		; write a linefeed, too
	ADD R0,R0,#10
	OUT
	LD R0,OS_R0		; restore the character

	LD R1,TIN_R1		; restore R1
	LD R7,TIN_R7		; restore R7
	RET

TRAP_PUTSP
	; NOTE: This trap will end when it sees any NUL, even in
	; packed form, despite the P&P second edition's requirement
	; of a double NUL.

	ST R0,OS_R0		; save R0, R1, R2, R3, and R7
	ST R1,OS_R1
	ST R2,OS_R2
	ST R3,OS_R3
	ST R7,OS_R7
	ADD R1,R0,#0		; move string pointer (R0) into R1

TRAP_PUTSP_LOOP
	LDR R2,R1,#0		; read the next two characters
	LD R0,LOW_8_BITS	; use mask to get low byte
	AND R0,R0,R2		; if low byte is NUL, quit printing
	BRz TRAP_PUTSP_DONE
	OUT			; otherwise print the low byte

	AND R0,R0,#0		; shift high byte into R0
	ADD R3,R0,#8
TRAP_PUTSP_S_LOOP
	ADD R0,R0,R0		; shift R0 left
	ADD R2,R2,#0		; move MSB from R2 into R0
	BRzp TRAP_PUTSP_MSB_0
	ADD R0,R0,#1
TRAP_PUTSP_MSB_0
	ADD R2,R2,R2		; shift R2 left
	ADD R3,R3,#-1
	BRp TRAP_PUTSP_S_LOOP

	ADD R0,R0,#0		; if high byte is NUL, quit printing
	BRz TRAP_PUTSP_DONE
	OUT			; otherwise print the low byte

	ADD R1,R1,#1		; and keep going
	BRnzp TRAP_PUTSP_LOOP

TRAP_PUTSP_DONE
	LD R0,OS_R0		; restore R0, R1, R2, R3, and R7
	LD R1,OS_R1
	LD R2,OS_R2
	LD R3,OS_R3
	LD R7,OS_R7
	RET

TRAP_IN_msg	.STRINGZ "\nInput a character> "
MASK_HI .FILL x7FFF
LOW_8_BITS .FILL x00FF
OS_KBSR	.FILL xFE00
OS_KBDR	.FILL xFE02
OS_DSR	.FILL xFE04
OS_DDR	.FILL xFE06
OS_MCR	.FILL xFFFE
OS_SSEGS	.FILL xFE10
OS_SSEGD	.FILL xFE12


OS_Code_unused .BLKWTO x0400
;;;;;;;;;;;;;;;;;; OS ReadWrite section ;;;;;;;;;;;;;;;;;;;;;;;;
OS_ReadWrite_data

OS_HALT_R7   .BLKW 1
TOUT_R1 .BLKW 1
TIN_R1  .BLKW 1
TIN_R7  .BLKW 1
OS_R0   .BLKW 1
OS_R1   .BLKW 1
OS_R2   .BLKW 1
OS_R3   .BLKW 1
OS_R7   .BLKW 1



OS_ReadWrite_unused .BLKWTO x0500
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;         Entry point for user application
MAIN_START     ;; This must allways be 0x3000

    LEA R0, PROGRAMING_HOWTO
	PUTS

    LD R0, echo_addr
    JMP R0

echo_addr .FILL echo

	;-------------------------
PROGRAMING_HOWTO	.STRINGZ "
 ******************************************************************
 *  This user program doesn't do anything interesting.            *
 *  You should try to upload your own program:                    *
 *    1. Compile your program (produce .obj file)                 *
 *       [option1] Use LC3Edit.exe                                *
 *       [option2] Use LC3 command line assembler                 *
 *          lc3as asm_source.asm                                  *
 *       [option3] Compile C source                               *
 *          lcc -o c_source.obj c_source.c                        *
 *    2. Activate programmer on FPGA (push `LEFT' push-button)    *
 *    3. Right click on .obj file and select                      *
 *          C:\\lc3\\bin\\LC3Terminal.exe in \"Open with\" dialog      *
 *    4. Wait for programming to finish                           *
 *       The I/O board leds will go off and message will appear.  *
 ******************************************************************\n\n"

echo_msg  .STRINGZ "Key pressed: [ ]\n"
echo_msg_end


echo
;; Echo....
    LEA R1, echo_msg
    LEA R2, echo_msg_end
    ADD R2, R2,-4


again

    ; Get byte from stdin
    GETC

    ; Update char in string
    STR R0, R2, 0
	 ADD R0, R1, 0

    ; Output to stdout
    PUTS

    BR again    ; ... and do it again!




.END


