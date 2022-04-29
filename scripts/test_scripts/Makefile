# test all DialogAgent applications

# Test everything a few times to test moving across trial boundaries.
test: FORCE
	make test_everything -s

# A complete tour of every subscribed topic tested
test_everything: FORCE
	make test_mqtt_agent -s
	make test_file_agent -s
	make test_reprocessor_agent -s

test_mqtt_agent: FORCE
	cd mqtt_agent_testing; make test -s

test_file_agent: FORCE
	cd file_agent_testing; make test -s

test_reprocessor_agent: FORCE
	cd reprocessor_agent_testing; make test -s

# remove intermediate test result files
clean: FORCE
	cd mqtt_agent_testing; make clean -s
	cd file_agent_testing; make clean -s
	cd reprocessor_agent_testing; make clean -s

show_received_files: FORCE
	find -name "*received*.*"; 

FORCE: ;
