/*! \file 
    \brief Holds the implementation of the memory sub-system. */

#include "kernel.h"
#include "mm.h"

/* Variable definitions. */

struct page_frame
page_frame_table[MAX_NUMBER_OF_FRAMES];

/* Set when the system is initialized. */
unsigned long memory_pages;

/* The following three variables are set by the assembly code. */
unsigned long first_available_memory_byte;

const unsigned long memory_size;

const unsigned long kernel_page_table_root;

/* Function definitions. */

/* Change this function in task B4. */
extern long
kalloc(const register unsigned long length,
       const register unsigned int  process,
       const register unsigned long flags)
{
	
 if ((length<1 || length>memory_size)) {
  return ERROR;
  }
  
 int first_available_page=first_available_memory_byte/(4*1024);
 int num_pages = ((length+4095)>>12);
 int free_page_counter = 0;
 int cur_page;
 for(cur_page=first_available_page; cur_page<memory_pages; cur_page++)
  {
   if (page_frame_table[cur_page].owner == -1) {
	free_page_counter++;
   } else {
    free_page_counter = 0;
    continue;
   }
   if (free_page_counter == num_pages) {
    int start_addr = cur_page - free_page_counter;
	int alloc_page;
    for (alloc_page = start_addr; alloc_page <= cur_page; alloc_page++){
	 page_frame_table[alloc_page].owner = process;
	 page_frame_table[alloc_page].start = (start_addr*4096);
	 page_frame_table[alloc_page].free_is_allowed = (flags & ALLOCATE_FLAG_KERNEL);
	}

	kprints("page_frame_table[cur_page].start \n");
	kprinthex(page_frame_table[cur_page].start);
	kprints(" \n");
	kprints("alloc_page \n");
	
	kprinthex(alloc_page);
	kprints(" \n");
	kprints("cur_page: \n");
	kprinthex(cur_page);
	kprints(" \n");
	kprints(" \n");
	kprints(" \n");

	return page_frame_table[cur_page].start;
   }
  }
  return ERROR;
 
}

/* Change this function in task B4. */
long
kfree(const register unsigned long address)
{
	
	if (!(address%4096) || address<1 || address>memory_size) {
	  return ERROR;
	}
	
	int page = (address/4096);
	int start_addr = page_frame_table[page].start;
	
	if (start_addr!=address) {
	  return ERROR;
	}
	
	while (page_frame_table[page].start==address) {
	  if (page_frame_table[page].free_is_allowed) {
	    page_frame_table[page].owner = -1;
		page_frame_table[page].start = 0;
		page_frame_table[page].free_is_allowed = 0;
	  }
	  page++;
	}
	
 return ALL_OK;
}

/* Change this function in task A4. */
extern void
update_memory_protection(const register unsigned long page_table,
                         const register unsigned long start_address,
                         const register unsigned long length,
                         const register unsigned long flags)
{
}

/* Change this function in task A4. */
extern void
initialize_memory_protection()
{
}

/* Put any code you need to add to implement tasks B4 and A4 here. */
