#!/usr/bin/gnuplot

set term postscript enhanced color
set output "output/scatter.ps"

#set encoding iso_8859_1
set grid

set xlabel "8 Least significant bytes"
set ylabel "8 Most significant bytes"
#set ylabel "p"
#set title "Coverage"

#set style line 1 lt 2 lw 3
#set key box linestyle 1


set key off
plot 'LCG_Keyspace.txt' title 'Keys' with dots lt 3 lw 8;
