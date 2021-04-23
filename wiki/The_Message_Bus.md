
# The ToMCAT Message Bus 


ToMCAT implements the Message Bus using Mosquitto (MQTT) software.   The system works over TCP/IP, given a host machine and port.  Mosquitto provides a lightweight, robust infrastructure for interprocess communication.  Messages are written to the Message Bus by publishing them to topics.   Messages are read by subscribing to topics.  Message arrival is handled by asynchronous callback.

There are three components to the MQTT system.    

Application name | Role | Description
---- | ----  |  ----
mosquitto | Broker | Coordinator for subscription and publication.  This *is* the “Message Bus”.   A single instance is run.
mosquitto_sub  |  Reader | Subscribes to topics on the Message Bus.  Prints to stdout any messages it receives on the subscribed topics.  n instances may be run.
mosquitto_pub |  Writer  | Publishes messages to topics on the Message Bus.  n instances may be run.


#### Configuration

Default host | Default port
---- | ----
localhost | 1883






## Linux 

Installation
```
sudo apt-get update
sudo apt-get install -y mosquitto mosquitto-clients
```

Start the Message Bus by starting the broker:

```
xxx
```


## MacOS 

Installation

```
sudo port install mosquitto
```
or
```
brew install mosquitto
```

Start the Message Bus by starting the broker:

```
/usr/local/sbin/mosquitto -c /usr/local/etc/mosquitto/mosquitto.conf
```

#### Configuration

The default MQTT URI is used at ToMCAT:    tcp://localhost:1883

Password setting, or not

Port forwarding

Starting MQTT

#### Service start

sudo service mosquitto start / stop


#### Manual start

/usr/sbin/mosquitto -c /etc/mosquitto/mosquitto.conf


Testing the installation

Subscribe to a test topic

mosquitto_sub -t "test"


Publish to a test topic:

mosquitto_pub -t "test" -m "message from mosquitto_pub client"


If the installation has been successful, the mosquitto_sub instance will print the test message.



Show two-computer interaction using forwarded port



