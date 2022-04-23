# test message bus telemetry

# Test everything twice so we know the Agent can survie multiple trials
test: FORCE
	make test_every_topic -s
	make test_every_topic -s

# A complete tour of every subscribed topic tested
test_every_topic: FORCE
	make test_asr -s
	make test_chat -s
	make test_trial -s

test_asr: FORCE
	cd asr; make test -s

test_chat: FORCE
	cd chat; make test -s

test_trial: FORCE
	cd trial; make test -s


# remove intermediate test result files

clean:
	make clean_asr -s
	make clean_chat -s
	make clean_trial -s

clean_asr:
	cd asr; make clean -s

clean_chat:
	cd chat; make clean -s

clean_trial:
	cd trial; make clean -s

# show intermediate files that would be cleaned
show_dirt:
	find -name "*received*.*"

FORCE: ;
