
# The ToMCAT Testbed Message Bus 


ToMCAT implements the Testbed Message Bus using the Mosquitto (MQTT) application.   The system works over TCP/IP and provides a lightweight, robust infrastructure for interprocess communication.  Messages are written to the Message Bus by publishing them to topics.   Messages are read by subscribing to topics.  Message arrival is handled by asynchronous callback.

There are three components to the MQTT system.    

Application name | Role | Description
---- | ----  |  ----
mosquitto | Broker | Coordinator for subscription and publication.  This *is* the “Message Bus”.   A single instance is run.
mosquitto_sub  |  Reader | Subscribes to topics on the Message Bus.  Prints to stdout any messages it receives on the subscribed topics.  n instances may be run.
mosquitto_pub |  Writer  | Publishes messages to topics on the Message Bus.  n instances may be run.

#### Configuration

* ToMCAT uses the MQTT default host '**localhost**', and the MQTT default port '**1883**'.  
* No password is used.


## Linux 

Installation
```
sudo apt-get update
sudo apt-get install -y mosquitto mosquitto-clients
```

To start the Message Bus:

```
sudo service mosquitto start
```

To stop the Message Bus:
```
sudo service mosquitto stop
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
/opt/local/sbin/mosquitto -c /opt/local/etc/mosquitto//mosquitto.conf 
```

If the broker starts correctly it will output some status information:

```
1619221743: mosquitto version 1.5.8 starting
1619221743: Config loaded from /opt/local/etc/mosquitto//mosquitto.conf.
1619221743: Opening ipv6 listen socket on port 1883.
1619221743: Opening ipv4 listen socket on port 1883.
```

Stop the broker by killing its process ID.




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



