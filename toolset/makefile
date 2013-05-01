all:
	 gnatmake -P common test

clean:
	-rm build/*
	-rm src/*~

railway_validator:
	gnatmake -P common $@


distclean: clean
	-rm railway_validator
