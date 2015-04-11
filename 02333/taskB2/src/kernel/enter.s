# This file holds the long mode assembly code for handling system calls.
# WARNING: This code, like most assembly code, is far from easy to understand.
#  Take help from a teaching assistant!

.global syscall_dummy_target
syscall_dummy_target:
 # We should never reach this!
 hlt
 jmp    syscall_dummy_target

.global syscall_target
syscall_target:
 # Swap in the supervisor gs
 swapgs
 # Now we can use gs to access data. We save rax so that we have one register
 # available for calculations.
 mov    %rax,%gs:scratch_space

 # Set segment registers to supervisor mode
 mov    $32,%eax
 mov    %eax,%ds
 mov    %eax,%es
 mov    %eax,%fs

 # Get the index to the thread that should be run
 mov    current_thread,%eax
 # mask off everything except the lowest 8 bits
 and    $255,%rax
 # The size of a thread structure is 1024 bytes. We multiply the index with
 # 1024 to get an offset into the thread_table. Multiplying with 1024 is the
 # same as shifting left 10 bits.
 shl    $10,%rax
 # Add the address of thread_table to get the address of the thread structure.
 # We also add 0x200 to get an address to the integer registers.
 add    $thread_table+0x200,%rax

 # Save most registers
 mov    %rbx,1*8(%rax)
 mov    %rcx,17*8(%rax)    # Is the rip
 mov    %rdx,3*8(%rax)
 mov    %rdi,4*8(%rax)
 mov    %rsi,5*8(%rax)
 mov    %rbp,6*8(%rax)
 mov    %rsp,7*8(%rax)
 mov    %r8,8*8(%rax)
 mov    %r9,9*8(%rax)
 mov    %r10,10*8(%rax)
 mov    %r11,16*8(%rax)    # Is the rflags
 mov    %r12,12*8(%rax)
 mov    %r13,13*8(%rax)
 mov    %r14,14*8(%rax)
 mov    %r15,15*8(%rax)

 # Save the data we previously stored into the scratch space
 mov    scratch_space,%rbx
 mov    %rbx,0*8(%rax)

 # Set the stack pointer to the supervisor stack
 mov    $stack,%rsp

 # Save the FPU state
 fxsave -512(%rax)

 # call the c portion of the system call handler
 call   system_call_handler

.global return_to_user_mode
return_to_user_mode:
 # Return to user mode.

 # Get the index to the thread that should be run
 mov    current_thread,%eax
 # mask off everything except the lowest 8 bits
 and    $255,%rax
 # The size of a thread structure is 1024 bytes. We multiply the index with
 # 1024 to get an offset into the thread_table. Multiplying with 1024 is the
 # same as shifting left 10 bits.
 shl    $10,%rax
 # Add the address of thread_table to get the address of the thread structure.
 # We also add 0x200 to get an address to the integer registers.
 add    $thread_table+0x200,%rax

 # Restore the FPU state
 fxrstor -512(%rax)

 # Restore registers
 mov    0(%rax),%rbx
 mov    %rbx,scratch_space

 mov    1*8(%rax),%rbx
 mov    17*8(%rax),%rcx  # rcx is the rip
 mov    3*8(%rax),%rdx
 mov    4*8(%rax),%rdi
 mov    5*8(%rax),%rsi
 mov    6*8(%rax),%rbp
 mov    7*8(%rax),%rsp

 mov    8*8(%rax),%r8
 mov    9*8(%rax),%r9
 mov    10*8(%rax),%r10
 mov    16*8(%rax),%r11  # r11 is the rflags
 mov    12*8(%rax),%r12
 mov    13*8(%rax),%r13
 mov    14*8(%rax),%r14
 mov    15*8(%rax),%r15

 # Set segment registers to user mode data access
 mov    $11,%eax
 mov    %eax,%ds
 mov    %eax,%es
 mov    %eax,%fs

 # Use gs so that we can acces kernel data space. All the other segment
 # registers are for user space.
 mov    %gs:scratch_space,%rax
 # Swap in the user space gs
 swapgs
 # And go to user mode
 sysretq
 .bss
 # We reserve two pages for stack
 .align 8
 .skip  2*4096
.global stack
stack:
 .align 8
scratch_space:
 .skip  8

