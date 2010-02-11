# This file holds all long mode assembly code.
# WARNING: This code, like most assembly, code is far from easy to understand.
#  Take help from a teaching assistant!

.global syscall_dummy_target
syscall_dummy_target:
 # We should never reach this!
 hlt
 jmp    syscall_dummy_target

.global syscall_target
syscall_target:
 # Save the user_stack_pointer temporarily. There are faster ways of doing this
 # but using a temporary variable is an easy way...
 swapgs

 # Now we can save registers
 mov    %rsp,%gs:(saved_registers+0x200+8*7)
 mov    %rbx,%gs:(saved_registers+0x200+8*1)
 mov    $stack,%rsp
 
 # Set segments to supervisor mode
 mov    $32,%ebx
 mov    %ebx,%ds
 mov    %ebx,%es
 mov    %ebx,%fs

 mov    %rax,(saved_registers+0x200+8*0)
 mov    %rcx,(saved_registers+0x200+8*17) # Is the rip
 mov    %rdx,(saved_registers+0x200+8*3)
 mov    %rdi,(saved_registers+0x200+8*4)
 mov    %rsi,(saved_registers+0x200+8*5)
 mov    %rbp,(saved_registers+0x200+8*6)

 mov    %r8,(saved_registers+0x200+8*8)
 mov    %r9,(saved_registers+0x200+8*9)
 mov    %r10,(saved_registers+0x200+8*10)
 mov    %r11,(saved_registers+0x200+8*16) # Is the rflags
 mov    %r12,(saved_registers+0x200+8*12)
 mov    %r13,(saved_registers+0x200+8*13)
 mov    %r14,(saved_registers+0x200+8*14)
 mov    %r15,(saved_registers+0x200+8*15)

 # call the c portion of the system call handler
 call   system_call_handler
 
 # Return to user mode.

 # Restore registers
 mov    (saved_registers+0x200+8*0),%rax
 mov    (saved_registers+0x200+8*17),%rcx # rcx is the rip
 mov    (saved_registers+0x200+8*3),%rdx
 mov    (saved_registers+0x200+8*4),%rdi
 mov    (saved_registers+0x200+8*5),%rsi
 mov    (saved_registers+0x200+8*6),%rbp

 mov    (saved_registers+0x200+8*8),%r8
 mov    (saved_registers+0x200+8*9),%r9
 mov    (saved_registers+0x200+8*10),%r10
 mov    (saved_registers+0x200+8*16),%r11 # r11 is the rflags
 mov    (saved_registers+0x200+8*12),%r12
 mov    (saved_registers+0x200+8*13),%r13
 mov    (saved_registers+0x200+8*14),%r14
 mov    (saved_registers+0x200+8*15),%r15

 # Set segments to user mode
 mov    $11,%ebx
 mov    %ebx,%ds
 mov    %ebx,%es
 mov    %ebx,%fs
 mov    %gs:(saved_registers+0x200+8*1),%rbx
 mov    %gs:(saved_registers+0x200+8*7),%rsp
 swapgs
 sysretq
 
 .global user_level_start 
user_level_start:
 # Set up our own stack
 mov    $user_stack,%rsp

 # Print a string onto the console
 mov    $hello_world_string,%rdi
 mov    $1,%rax
 syscall

 xor    %rax,%rax
 syscall
 mov    %rax,%rdi

 # Print the version number onto the console
 mov    $2,%rax
 syscall

 # Go to the next line
 mov    $line_end,%rdi
 mov    $1,%rax
 syscall

 # and go to sleep
snooze:
 mov    $3,%rax
 syscall
 jmp    snooze

 .data
hello_world_string:
 .ascii "Hello World!"
line_end:
 .byte  10,13,0
 .bss
 # We reserve two pages for stack
 .align 8
 .skip  2*4096
.global stack # also used by boot64.s
stack:
 .skip  2*4096
user_stack:
