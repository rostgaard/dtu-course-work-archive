#!/usr/bin/gnuplot

set term postscript enhanced color
set output "output/ex15.ps"

#set encoding iso_8859_1
set grid

set xlabel "100 Iteraterations"
#set ylabel "p"
#set title "Coverage"

#set style line 1 lt 2 lw 3
#set key box linestyle 1


plot 'ex15.data' using 1 title 'Coverage' with line lt 1 lw 4;
#     'ex15.data.java' using 2 title 'Coverage (Java)' with linespoints lt 4 lw 1 pt 1;
