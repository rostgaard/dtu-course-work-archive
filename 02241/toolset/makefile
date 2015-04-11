GNATMAKE=gnatmake -P common

all: route_validator schedule_validator

clean:
	-gnatclean -P common
	-rm build/*
	-rm src/*~

route_validator:
	@-echo ""
	@-echo "***************************"
	@-echo Building $@ ...
	@-echo "***************************"
	@-echo ""
	@$(GNATMAKE) $@

schedule_validator:
	@-echo ""
	@-echo "***************************"
	@-echo Building $@ ...
	@-echo "***************************"
	@-echo ""
	@$(GNATMAKE) $@

distclean: clean
	-rm railway_validator railval-tokenizer-test railval-validator-test route_validator schedule_validator

tests: railval-tokenizer-test railval-validator-test crude_containers-multiset_test

railval-tokenizer-test:
	gnatmake -P common $@

railval-validator-test:
	gnatmake -P common $@

crude_containers-multiset_test:
	gnatmake -P common $@

