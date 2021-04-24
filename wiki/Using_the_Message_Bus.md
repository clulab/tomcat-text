
# The ToMCAT Testbed Message Bus 


ToMCAT implements the Testbed Message Bus using the Mosquitto (MQTT) application.   The system works over TCP/IP and provides a lightweight, robust infrastructure for interprocess communication.  Messages are written to the Message Bus by publishing them to topics.   Messages are read by subscribing to topics.  Message arrival is handled by asynchronous callback.

There are three components to the MQTT system.    

Application name | Role | Description
---- | ----  |  ----
mosquitto | Broker | Coordinator for subscription and publication.  This *is* the “Message Bus”.   A single instance is run.
mosquitto_sub  |  Reader | Subscribes to topics on the Message Bus.  Prints to stdout any messages it receives on the subscribed topics.  n instances may be run.
mosquitto_pub |  Writer  | Publishes messages to topics on the Message Bus.  n instances may be run.



## Installing the Message Bus software:

### Linux
```
sudo apt-get update
sudo apt-get install -y mosquitto mosquitto-clients
```

### MacOs
```
sudo port install mosquitto
```
or
```
sudo brew install mosquitto
```

### Configuration

* ToMCAT uses the MQTT default host '**localhost**', and the MQTT default port '**1883**'.  
* No password is used.


## Starting the Message Bus

### Linux

```
sudo service mosquitto start
```

No output is show on screen 



### MacOS 

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




## Using the Message Bus

First start the Message bus using the previously described procedure for your operating system.

Next, subscribe to a Message Bus topic:

```
mosquitto_sub -t my_test_topic
```

Then publish a message on that topic:

```
mosquitto_pub -t my_test_topic -m "Hello world!"
```

If the mosquitto_sub process displays the message, your message bus is running correctly.


## Stopping the Message Bus

### Linux

```
sudo service mosquitto stop
```

### MacOS

Find the process ID of the broker instance...

```
ps -aux | grep mosquitto
mosquit+     866  0.0  0.0  28112  4164 ?  Ssl  11:13   0:05 /opt/local/sbin/mosquitto -c /opt/local/etc/mosquitto/mosquitto.conf
```

...and kill it:

```
kill 866
```
