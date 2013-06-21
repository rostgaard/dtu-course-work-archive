###############################################################################
#                                                                             #
#  This is free software;  you can redistribute it  and/or modify it          #
#  under terms of the  GNU General Public License as published  by the        #
#  Free Software  Foundation;  either version 3,  or (at your option) any     #
#  later version.  This software is distributed in the hope  that it will     #
#  be useful, but WITHOUT ANY WARRANTY;  without even the implied warranty    #
#  of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU        #
#  General Public License for  more details.                                  #
#  You should have  received  a copy of the GNU General  Public  License      #
#  distributed  with  this  software;   see  file COPYING3.  If not, go       #
#  to http://www.gnu.org/licenses for a complete copy of the license.         #
#                                                                             #
###############################################################################

GNATMAKE=gnatmake -P common
GNATCLEAN=gnatclean -P common

all: folders solutions

solutions: exercise_15.output exercise_17.output

folders:
	@-(ls build > /dev/null || mkdir build)

clean:
	${GNATCLEAN}


distclean: clean
	-rm exercise_15
	-rm exercise_17
	-rm ex15.data
	-rm ex17.data

exercise_15.output: exercise_15
	@-echo ""
	@-echo "***************************"
	@-echo " Plotting graph..."
	@-echo "***************************"
	@-echo ""
	@(ls ex15.data 1> /dev/null 2> /dev/null|| (echo "Datafile not found - regenerating..." && ./exercise_15 > ex15.data))
	@echo Running GNUPlot.
	@tools/coverage_ex15.gnuplot
	@echo Converting to PDF.
	@(cd output && ps2pdf ex15.ps)

exercise_17.output: exercise_17
	@-echo ""
	@-echo "***************************"
	@-echo " Plotting graph..."
	@-echo "***************************"
	@-echo ""
	@(ls ex17.data 1> /dev/null 2> /dev/null|| (echo "Datafile not found - regenerating..." && ./exercise_17 > ex17.data))
	@echo Running GNUPlot.
	@tools/coverage_ex17.gnuplot
	@echo Converting to PDF.
	@(cd output && ps2pdf ex17.ps)
	@(cd output && ps2pdf ex17a.ps)
	

exercise_15:
	@-echo ""
	@-echo "***************************"
	@-echo Building $@ ...
	@-echo "***************************"
	@-echo ""
	@${GNATMAKE} $@ 1> /dev/null

.PHONY: exercise_15
