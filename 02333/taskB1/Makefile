# The following line holds compiler options
CFLAGS = -msoft-float -fno-exceptions -fno-common -O3

# Do not change the following line. You will not be able to compile without it
# PATH changed to prefix variable to resolve issues with some Cygwin installations.
# PATH := ${PATH}:${PWD}/../../support_software/bin/bin
PREFIX := "${PWD}/../../support_software/bin/bin"

bochs_boot: boot/kernel
	${PREFIX}/genext2fs -b 1440 -d boot bochs_stuff/b.img
	(cd bochs_stuff; nice -20 ${PREFIX}/bochs -q)

boot/kernel: objects/boot32.o objects/boot64.o objects/enter.o objects/kernel.o src/link.ld
	${PREFIX}/x86_64-pc-elf-ld --no-warn-mismatch -Tsrc/link.ld -o boot/kernel objects/boot32.o objects/boot64.o objects/enter.o objects/kernel.o

objects/boot32.o: src/boot32.s
	${PREFIX}/x86_64-pc-elf-as --32 -o objects/boot32.o src/boot32.s
	
objects/boot64.o: src/boot64.s 
	${PREFIX}/x86_64-pc-elf-as --64 -o objects/boot64.o src/boot64.s

objects/enter.o: src/enter.s
	${PREFIX}/x86_64-pc-elf-as --64 -o objects/enter.o src/enter.s

objects/kernel.o: src/kernel.c src/syscall.c src/kernel.h
	${PREFIX}/x86_64-pc-elf-gcc -m64 $(CFLAGS) -c -o objects/kernel.o src/kernel.c


clean:
	-rm -rf objects
	-rm boot/kernel
	-rm bochs_stuff/b.img
	mkdir objects

compile: boot/kernel
