# The following line holds compiler options
CFLAGS = -fno-exceptions -fno-common -Isrc/include

# Do not change the following line. You will not be able to compile without it
PREFIX := "${PWD}/../../support_software/bin/bin"

bochs_boot: boot/kernel
	${PREFIX}/genext2fs -b 1440 -d boot bochs_stuff/b.img
	(cd bochs_stuff; nice -20 ${PREFIX}/bochs)

kernel: boot/kernel

src/include/scwrapper.h: src/include/sysdefines.h

src/kernel/kernel.h: src/include/sysdefines.h

boot/kernel: objects/kernel/boot32.o objects/kernel/boot64.o objects/kernel/enter.o objects/kernel/kernel.o objects/kernel/threadqueue.o objects/kernel/mm.o src/kernel/kernel_link.ld objects/program_0/executable.o objects/program_1/executable.o objects/program_2/executable.o
	${PREFIX}/x86_64-pc-elf-ld --no-warn-mismatch -Tsrc/kernel/kernel_link.ld -o boot/kernel objects/kernel/boot32.o objects/kernel/boot64.o objects/kernel/enter.o objects/kernel/kernel.o objects/kernel/threadqueue.o objects/kernel/mm.o objects/program_0/executable.o objects/program_1/executable.o objects/program_2/executable.o

objects/kernel/boot32.o: src/kernel/boot32.s
	${PREFIX}/x86_64-pc-elf-as --32 -o objects/kernel/boot32.o src/kernel/boot32.s

objects/kernel/boot64.o: src/kernel/boot64.s
	${PREFIX}/x86_64-pc-elf-as --64 -o objects/kernel/boot64.o src/kernel/boot64.s

objects/kernel/enter.o: src/kernel/enter.s
	${PREFIX}/x86_64-pc-elf-as --64 -o objects/kernel/enter.o src/kernel/enter.s

objects/kernel/kernel.o: src/kernel/kernel.c src/kernel/syscall.c src/kernel/kernel.h src/kernel/threadqueue.h src/kernel/mm.h src/kernel/scheduler.c src/kernel/pscheduler.c
	${PREFIX}/x86_64-pc-elf-gcc -m64 -msoft-float -mno-mmx -mno-sse $(CFLAGS) -O3 -c -o objects/kernel/kernel.o src/kernel/kernel.c

objects/kernel/threadqueue.o: src/kernel/threadqueue.c src/kernel/kernel.h src/kernel/threadqueue.h
	${PREFIX}/x86_64-pc-elf-gcc -m64 -msoft-float -mno-mmx -mno-sse $(CFLAGS) -O3 -c -o objects/kernel/threadqueue.o src/kernel/threadqueue.c

objects/kernel/mm.o: src/kernel/mm.c src/kernel/kernel.h src/kernel/mm.h
	${PREFIX}/x86_64-pc-elf-gcc -m64 -msoft-float -mno-mmx -mno-sse $(CFLAGS) -O3 -c -o objects/kernel/mm.o src/kernel/mm.c

objects/program_startup_code/startup.o: src/program_startup_code/startup.s
	${PREFIX}/x86_64-pc-elf-as --64 -o objects/program_startup_code/startup.o src/program_startup_code/startup.s

objects/program_0/main.o: src/program_0/main.c src/include/scwrapper.h
	${PREFIX}/x86_64-pc-elf-gcc -fPIE -m64 $(CFLAGS) -c -O3 -o objects/program_0/main.o src/program_0/main.c

objects/program_0/executable: objects/program_startup_code/startup.o objects/program_0/main.o
	${PREFIX}/x86_64-pc-elf-ld -static -Tsrc/program_startup_code/program_link.ld -o objects/program_0/executable objects/program_startup_code/startup.o objects/program_0/main.o

objects/program_0/executable.o: objects/program_0/executable
	${PREFIX}/x86_64-pc-elf-strip objects/program_0/executable
	${PREFIX}/x86_64-pc-elf-objcopy  -I binary -O elf32-i386 -B i386 --set-section-flags .data=alloc,contents,load,readonly,data objects/program_0/executable objects/program_0/executable.o

objects/program_1/main.o: src/program_1/main.c src/include/scwrapper.h
	${PREFIX}/x86_64-pc-elf-gcc -fPIE -m64 $(CFLAGS) -c -O3 -o objects/program_1/main.o src/program_1/main.c

objects/program_1/executable: objects/program_startup_code/startup.o objects/program_1/main.o
	${PREFIX}/x86_64-pc-elf-ld -static -Tsrc/program_startup_code/program_link.ld -o objects/program_1/executable objects/program_startup_code/startup.o objects/program_1/main.o

objects/program_1/executable.o: objects/program_1/executable
	${PREFIX}/x86_64-pc-elf-strip objects/program_1/executable
	${PREFIX}/x86_64-pc-elf-objcopy  -I binary -O elf32-i386 -B i386 --set-section-flags .data=alloc,contents,load,readonly,data objects/program_1/executable objects/program_1/executable.o

objects/program_2/main.o: src/program_2/main.c src/include/scwrapper.h
	${PREFIX}/x86_64-pc-elf-gcc -fPIE -m64 $(CFLAGS) -c -O3 -o objects/program_2/main.o src/program_2/main.c

objects/program_2/executable: objects/program_startup_code/startup.o objects/program_2/main.o
	${PREFIX}/x86_64-pc-elf-ld -static -Tsrc/program_startup_code/program_link.ld -o objects/program_2/executable objects/program_startup_code/startup.o objects/program_2/main.o

objects/program_2/executable.o: objects/program_2/executable
	${PREFIX}/x86_64-pc-elf-strip objects/program_2/executable
	${PREFIX}/x86_64-pc-elf-objcopy  -I binary -O elf32-i386 -B i386 --set-section-flags .data=alloc,contents,load,readonly,data objects/program_2/executable objects/program_2/executable.o

clean:
	-rm -rf objects
	-rm boot/kernel
	-rm bochs_stuff/b.img
	mkdir objects
	mkdir objects/kernel
	mkdir objects/program_startup_code
	mkdir objects/program_0
	mkdir objects/program_1
	mkdir objects/program_2

compile: boot/kernel

IMPORTS := syscall.c scheduler.c pscheduler.c

import: .imported

.imported:
	@if [ -e .imported ]; \
	then echo Already imported!; \
	else \
	  for n in ${IMPORTS}; do \
	   cp ../task3/src/kernel/$$n src/kernel/; \
	  done && \
	  touch .imported && \
	  echo Files imported, but manual changes may still be required.; \
	fi 
