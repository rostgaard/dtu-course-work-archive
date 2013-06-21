
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

solutions: exercise_01.output exercise_02 exercise_08 exercise_09 exercise_11

folders:
	@-(ls build > /dev/null || mkdir build)

clean:
	${GNATCLEAN}


distclean: clean
	-rm exercise_01
	-rm exercise_08
	-rm exercise_09
	-rm exercise_11

exercise_15.output: exercise_15
	@-echo ""
	@-echo "***************************"
	@-echo Building $@ ...
	@-echo "***************************"
	@-echo ""
	@(ls ex15.data > /dev/null || (echo "Datafile not found - regenerating..." && ./exercise_15 > ex15.data))
	tools/coverage.gnuplot
	(cd output && ps2pdf coverage_map.ps)
	

exercise_15:
	@-echo ""
	@-echo "***************************"
	@-echo Building $@ ...
	@-echo "***************************"
	@-echo ""
	@${GNATMAKE} $@ 1> /dev/null

exercise_02:
	@-echo ""
	@-echo "***************************"
	@-echo Building $@ ...
	@-echo "***************************"
	@-echo ""
	@${GNATMAKE} $@ 1> /dev/null

exercise_05:
	@-echo ""
	@-echo "***************************"
	@-echo Building $@ ...
	@-echo "***************************"
	@-echo ""
	@${GNATMAKE} $@ 1> /dev/null

exercise_07:
	@-echo ""
	@-echo "***************************"
	@-echo Building $@ ...
	@-echo "***************************"
	@-echo ""
	@${GNATMAKE} $@ 1> /dev/null

exercise_08:
	@-echo ""
	@-echo "***************************"
	@-echo Building $@ ...
	@-echo "***************************"
	@-echo ""
	@${GNATMAKE} $@ 1> /dev/null

exercise_09:
	@-echo ""
	@-echo "***************************"
	@-echo Building $@ ...
	@-echo "***************************"
	@-echo ""
	@${GNATMAKE} $@ 1> /dev/null

exercise_11:
	@-echo ""
	@-echo "***************************"
	@-echo Building $@ ...
	@-echo "***************************"
	@-echo ""
	@${GNATMAKE} $@ 1> /dev/null

exercise_01.output:exercise_01
	@./exercise_01 | grep "BEWARE" > output/exercise_01.output
