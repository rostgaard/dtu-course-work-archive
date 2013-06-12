
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

solutions: exercise_01.output exercise_08 exercise_11

folders:
	@-(ls build > /dev/null || mkdir build)

clean:
	${GNATCLEAN}


distclean: clean
	rm exercise1
	rm exercise2

exercise_01:
	@-echo "Building $@"
	@${GNATMAKE} $@

exercise_07:
	@-echo "Building $@"
	@${GNATMAKE} $@

exercise_08:
	@-echo "Building $@"
	@${GNATMAKE} $@

exercise_11:
	@-echo "Building $@"
	@${GNATMAKE} $@
	

exercise_01.output:exercise_01
	@./exercise_01 | grep "BEWARE" > exercise_01.output
