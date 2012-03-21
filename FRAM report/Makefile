wordcount: thesis
	echo `date +%s ` `pdftotext Thesis.pdf - | wc -w` >> statistics
	./thesis.gnuplot # Update the graph
thesis:
	pdflatex Thesis.tex

draft:
	pdflatex Draft.tex
references:
	bibtex Thesis
