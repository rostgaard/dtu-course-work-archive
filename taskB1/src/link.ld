OUTPUT_ARCH ( "i386" )
OUTPUT_FORMAT("elf32-i386")
ENTRY(_start)

PHDRS
{
  headers PT_PHDR PHDRS ;
  text PT_LOAD FILEHDR PHDRS ;
  /*data PT_LOAD ;
    dynamic PT_DYNAMIC ; */
}

SECTIONS
{
  . = SIZEOF_HEADERS;

  /* You can make to image to be much more compact. This link script
     was done so that it is remotely possible to read. It is complex 
     but could have been much more complex.*/
  .text (0x00100000 + SIZEOF_HEADERS) :
   AT (0x00100000 + SIZEOF_HEADERS)
  {
   objects/boot32.o (.text) 
   objects/boot64.o (.text) 
   objects/enter.o (.text) 
   objects/kernel.o (.text)
   * (.text) /* Any remaining text sections. */
   . = ALIGN(4096);
  } : text

  .data (ADDR(.text) + SIZEOF (.text)) :
   AT (LOADADDR(.text) + SIZEOF (.text))
  {
   objects/boot32.o (.data)
   * (.data) /* Any remaining data sections. */
   * (.ro*)  /* Any remaining read only data sections. */
   * (.eh*)  /* Any remaining eh_frame sections. */
   . = ALIGN(4096);
  } : text
 
 
  .bss (ADDR(.data) + SIZEOF (.data)) :
   AT (LOADADDR(.data) + SIZEOF (.data))
  {
   objects/enter.o (.bss)
   * (.bss)  /* Any remaining bss sections. */
  }
}