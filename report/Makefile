all: report.pdf

report.pdf: report.tex
	@echo Building initial $@ ..
	@-pdflatex report.tex >/dev/null
	@echo Running bibtex initial $@ ..
	@bibtex report  >/dev/null
	@echo Building $@ with bibtex
	@pdflatex report.tex  >/dev/null
	@echo Building final $@
	@pdflatex report.tex  >/dev/null
	@echo Done!

report.tex:

clean:
	-@rm report.aux report.log 2> /dev/null

distclean: clean
	@-rm report.toc report.bbl report.blg 2> /dev/null
	@-rm report.pdf 2> /dev/null

