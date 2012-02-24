wordcount: thesis
	echo `date +%s ` `pdftotext Thesis.pdf - | wc -w` >> statistics
	./thesis.gnuplot # Update the graph
thesis:
	pdflatex Thesis.tex
