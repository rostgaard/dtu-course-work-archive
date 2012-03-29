#!/usr/bin/gnuplot

set terminal png
set output 'thesis.png'

set xlabel 'Time'
set ylabel "Words"
set title "Entire period"
set xrange [1326668400:1333234800]
#set yrange [0:5500]
#set style line 1 lt 2 lw 3
#set key box linestyle 1

plot 'statistics' using 1:2 notitle with line lt 3 lw 2


# Last 2 weeks
set title "Two final weeks"
set output 'thesis_last_two_weeks.png'
set xrange [1332025200:1333234800]
plot 'statistics' using 1:2 notitle with line lt 3 lw 2
