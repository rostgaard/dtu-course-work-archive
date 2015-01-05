all: test_run

Lexer.fs:
	fslex Lexer.fsl

Parser.fs:
	fsyacc --module Parser Parser.fsy

Parser.fsi: Parser.fs

test_run: Lexer.fs Parser.fs
	@-fsharpi -I lib < Script.fsx 

clean:
	@-rm Lexer.fs Parser.fs Parser.fsi
