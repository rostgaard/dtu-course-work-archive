# This file holds all long mode assembly code.
# WARNING: This code, like most assembly, code is far from easy to understand.
#  Take help from a teaching assistant!

 .text
 .global start_of_64bit_code
start_of_64bit_code:
 # We reload segment registers so that we use 64-bit space for everything
 mov    $32,%eax
 mov    %eax,%ss
 mov    %eax,%ds
 mov    %eax,%es
 
 # We can now set the kernel stack
 mov    $stack,%rsp
 
 # Set all flags to a well defined state
 push   $0
 popf
 
 # Set the FS base to 0
 mov    $0xc0000100,%ecx
 xor    %eax,%eax
 xor    %edx,%edx
 wrmsr  #  must use the wrmsr instruction to write a 64-bit value to FS, GS and KernelGS
 
 # And the GS base to 0
 mov    $0xc0000101,%ecx
 xor    %eax,%eax
 xor    %edx,%edx
 wrmsr

 # And finally the KernelGS base to 0
 mov    $0xc0000102,%ecx
 xor    %eax,%eax
 xor    %edx,%edx
 wrmsr

 # And reload FS and GS
 mov    $32,%eax
 mov    %eax,%fs
 mov    %eax,%gs
 
 # We do not set a new GDT since all the descriptors we are interested in are
 # there already and there is no problem for us to have it below the 4Gbyte
 # barrier.
 
 # We set up the registers necessary to use syscall
 # The registers are STAR, LSTAR, CSTAR and SFMASK.
 # LSTAR holds the target address of system calls in
 # long mode so STAR and CSTAR just points to a dummy target.
 # See section 6.1.1 in AMD64 Programmers Manual, Vol. 2
 
 # Write STAR
 mov    $0xc0000081,%ecx
 mov    $0x00030018,%edx
 mov    $syscall_dummy_target,%eax
 wrmsr

 # Write LSTAR
 mov    $0xc0000082,%ecx
 xor    %edx,%edx
 mov    $syscall_target,%eax
 wrmsr

 # Write CSTAR
 mov    $0xc0000083,%ecx
 xor    %edx,%edx
 mov    $syscall_dummy_target,%eax
 wrmsr
 
 # Write SFMASK
 mov    $0xc0000084,%ecx
 xor    %edx,%edx
 mov    $0x00000300,%eax
 wrmsr
 
 # We can now switch to c!
 call   initialize

 # Back in assembly land after calling the c function initialize.
 # We should now jump into user space. Set up registers the way we want and go!

 # Set user level start
 mov    $user_level_start,%rcx

 # Reset all flags. This will mean that we will run with interrupts disabled
 xor    %r11,%r11

 # Set segments to user mode
 mov    $11,%eax
 mov    %eax,%ds
 mov    %eax,%es
 mov    %eax,%fs
 mov    %eax,%gs
 sysretq
 
