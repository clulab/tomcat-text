# Run the mosquitto broker in a docker container as root

# copy this image
FROM ubuntu

# blame
MAINTAINER jastier@email.arizona.edu

# applications
RUN  apt-get update && \
  apt-get install -y iputils-ping && \
  apt-get install -y mosquitto

# start here
ENTRYPOINT /usr/sbin/mosquitto -c /etc/mosquitto/mosquitto.conf


