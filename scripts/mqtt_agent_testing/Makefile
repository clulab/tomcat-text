# test message bus telemetry

# Test everything a few times to test moving across trial boundaries.
test: FORCE
	make test_everything -s
	make test_everything -s
	make test_everything -s

# A complete tour of every subscribed topic tested
test_everything: FORCE
	make test_trial_start -s
	make test_asr -s
	make test_chat -s
	make test_trial_stop -s

test_trial_start: FORCE
	cd trial_start; make test -s

test_asr: FORCE
	cd asr; make test -s

test_chat: FORCE
	cd chat; make test -s

test_trial_stop: FORCE
	cd trial_stop; make test -s


# remove intermediate test result files
clean: FORCE
	cd trial_start; make clean -s
	cd asr; make clean -s
	cd chat; make clean -s
	cd trial_stop; make clean -s

show_received_files: FORCE
	find -name "*received*.*"; 


FORCE: ;
