#!/usr/bin/gnuplot

set term postscript enhanced color
set output "output/coverage_map.ps"

#set encoding iso_8859_1
set grid

set xlabel "Time (seconds)"
#set ylabel "Coverage"
set title "Time/Coverage plot"

#set style line 1 lt 2 lw 3
#set key box linestyle 1


plot 'ex15.data' using 1:2 title 'Coverage' with linespoints lt 2 lw 1 pt 1, \
     'ex15.data' using 1:3 title '10M Collisions' with linespoints lt 3 lw 1 pt 1;

