# test the MQTT Dialog Agent

# Test everything a few times to test moving across trial boundaries.
.PHONY: test
test:
	make test_everything -s
	make test_everything -s
	make test_everything -s

# A complete tour of every subscribed topic tested
.PHONY: test_everything
test_everything:
	make test_trial_start -s
	make test_rollcall_request -s
	make test_asr -s
	make test_chat -s
	make test_rollcall_request -s
	make test_asr -s
	make test_chat -s
	make test_rollcall_request -s
	make test_trial_stop -s

.PHONY: test_asr
test_asr:
	cd asr; make test -s

.PHONY: test_chat
test_chat:
	cd chat; make test -s

.PHONY: test_rollcall_request
test_rollcall_request:
	cd rollcall_request; make test -s

.PHONY: test_trial_start
test_trial_start:
	cd trial_start; make test -s

.PHONY: test_trial_stop
test_trial_stop:
	cd trial_stop; make test -s


# remove intermediate test result files
.PHONY: clean
clean:
	cd trial_start; make clean -s
	cd rollcall_request; make clean -s
	cd asr; make clean -s
	cd chat; make clean -s
	cd trial_stop; make clean -s

.PHONY: show_received_files
show_received_files:
	find -name "*received*.*"; 
