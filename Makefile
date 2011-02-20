all:
	latex -interaction=nonstopmode techdoc.tex|bibtex techdoc.aux|latex -interaction=nonstopmode techdoc.tex|latex -interaction=nonstopmode techdoc.tex| dvips -o techdoc.ps techdoc.dvi | ps2pdf techdoc.ps

wordcount:
	pdftotext report.pdf - | wc -w
