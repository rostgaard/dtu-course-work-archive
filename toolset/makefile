all:
	 gnatmake -P common test

clean:
	-gnatclean -P common
	-rm build/*
	-rm src/*~

railway_validator:
	gnatmake -P common $@


distclean: clean
	-rm railway_validator
