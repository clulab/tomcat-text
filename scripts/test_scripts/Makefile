# test all DialogAgent applications

# Test everything a few times to test moving across trial boundaries.
PHONY: test
test:
	make test_everything -s

# A complete tour of every subscribed topic tested
PHONY: test_everything
test_everything:
	make test_mqtt_agent -s
	make test_file_agent -s
	make test_reprocessor_agent -s

PHONY: test_mqtt_agent
test_mqtt_agent:
	cd mqtt_agent_testing; make test -s

PHONY: test_file_agent
test_file_agent:
	cd file_agent_testing; make test -s

PHONY: test_reprocessor_agent
test_reprocessor_agent:
	cd reprocessor_agent_testing; make test -s

# remove intermediate test result files
PHONY: clean
clean:
	cd mqtt_agent_testing; make clean -s
	cd file_agent_testing; make clean -s
	cd reprocessor_agent_testing; make clean -s

PHONY: show_received_files
show_received_files:
	find -name "*received*.*"; 
