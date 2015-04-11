/*! \file
 *      \brief The first user program - tests the memory allocation routines
 *             by allocating and de-allocating random memory blocks.
 *
 */
#include <scwrapper.h>

/* Generates a pseduo random number */
static inline unsigned long
rnd(void) {
    const unsigned long seed = 101;
    static unsigned long memory;

    if ((memory == 0) ||
            (memory == 1) ||
            (memory == -1)) {
        memory = seed;
    }

    memory = (9973 * (~memory))+((memory) % 701);
    return memory;
}

void
main(int argc, char* argv[]) {

    struct {
        unsigned long addr;
        unsigned long size;
    } blocks[16];
    register int clock;
    register long total_memory_size = 0;

    /* Reset the information on the blocks */
    for (clock = 0; clock < 16; clock++) {
        blocks[clock].addr = 0;
    }

    clock = 0;

    while (1) {
        prints("NEW RUN!\n");
        long addr;
        unsigned int flags = rnd()&3;

        /* randomize the size of a block. */
        blocks[clock].size = (24 * 1024 * 1024 - total_memory_size)*(rnd()&(256 * 256 - 1)) /
                (256 * 256 * 8);

        /* Sanity check the block size. */
        if ((blocks[clock].size > 0) &&
                (blocks[clock].size < (24 * 1024 * 1024))) {
            /* Try to allocate memory. */
            addr = alloc(blocks[clock].size, flags);
            prints("alloc address is: ");
            printhex(addr);
            prints("\n");
            prints("\n");
            prints("\n");

            /* Check if it was successful. */
            if (addr <= 0) {
                prints("Memory block allocate failed!\n");
                break;
            }

            prints(".");

            /* Keep track of how much memory we have allocated... */
            total_memory_size += blocks[clock].size;
            /* and the address. */
            blocks[clock].addr = addr;
        } else {
            blocks[clock].addr = 0;
        }

        clock = (clock + 1)&15;

        /* Try to free one block. */
        if (0 != blocks[clock].addr) {
            if (0 != free(blocks[clock].addr)) {
                prints("Memory block free failed!\n");
                break;
            }
            prints("*");
            total_memory_size -= blocks[clock].size;
        }
    }
}
