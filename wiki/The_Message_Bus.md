
#The ToMCAT Message Bus 

###Installing and running the Mosquitto (MQTT) applications.   

Linux installation
MacOS installation
Macports
Homebrew
Configuration
Starting MQTT
Testing the installation



ToMCAT implements the Message Bus using Mosquitto (MQTT) software.   The system works over TCP/IP, given a host machine and port.  ToMCAT uses the MQTT default host and port:
   

Default host:
localhost
Default port:
1883



There are three components to the MQTT system.    

Application name
Role
Description
mosquitto 
Broker
Coordinator for subscription and publication.  This *is* the “Message Bus”.   A single instance is run.
mosquitto_sub
Reader
Subscribes to topics on the Message Bus.  Prints to stdout any messages it receives on the subscribed topics.
mosquitto_pub
Writer
Publishes messages to topics on the Message Bus. 



Linux installation

sudo apt-get update
sudo apt-get install -y mosquitto mosquitto-clients



MacOS installation

Macports
TODO

Homebrew
brew install mosquitto mosquitto-clients


Configuration

The default MQTT URI is used at ToMCAT:    tcp://localhost:1883

Password setting, or not

Port forwarding

Starting MQTT

Service start

sudo service mosquitto start / stop


Manual start

/usr/sbin/mosquitto -c /etc/mosquitto/mosquitto.conf


Testing the installation

Subscribe to a test topic

mosquitto_sub -t "test"


Publish to a test topic:

mosquitto_pub -t "test" -m "message from mosquitto_pub client"


If the installation has been successful, the mosquitto_sub instance will print the test message.



Show two-computer interaction using forwarded port



