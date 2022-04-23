
# remove intermediate test result files

clean:
	rm -f *received*.*


# test message bus telemetry.  The trial start test must be run
# before the trial stop test

test: FORCE
	./test_heartbeat_trial_start; \
	./test_heartbeat_trial_stop; \
	./test_version_info


FORCE: ;
