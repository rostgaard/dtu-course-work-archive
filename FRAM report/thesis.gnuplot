#!/usr/bin/gnuplot

set terminal png
set output 'thesis.png'

set xlabel 'Time'
set ylabel "Words"
set title "Thesis progress"
set xrange [1326668400:1333234800]
set yrange [0:5500]
#set style line 1 lt 2 lw 3
#set key box linestyle 1


plot 'statistics' using 1:2 notitle with line lt 3 lw 2
