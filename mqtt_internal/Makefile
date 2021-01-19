# Build and start a MQTT broker in a Docker container using the 
# Docker internal network

host := "host.docker.internal
port := 1883
name :="mqtt_internal"

all:
	make build run

.PHONY: build
build:
	docker-compose up --build

.PHONY: run
run:
	docker run --hostname=$(host) -p $(port):$(port) $(name) 

