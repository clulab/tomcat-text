
# Replaying Metadata 

Metadata recorded during testbed runs can be reprocessed using the Dialog Agent at any time.  This is accomplished by running the Dialog Agent on the Message Bus and then using the elkless_replayer script to publish the metadata to the Message Bus.   The Dialog Agent will then process the data as if it was running on the testbed.

### Preparation

Clone the tomcat and tomcat-text repos if you don't already have them:

```
git clone https://github.com/ml4ai/tomcat
git clone https://github.com/clulab/tomcat-text
```

If you have the repos already installed, make sure you have the latest version of the master branch:

```
git checkout master
git pull
```

[Install the MQTT mosquitto software](Using_the_Message_Bus.md#running-the-message-bus) if is not already on your machine.





, and start the broker.  




In the tomcat-text repo, run the Message Bus version of the Dialog Agent:

```
cd tomcat-text
sbt "runMain org.clulab.asist.RunDialogAgent mqtt localhost 1883"
```

The Dialog Agent will take a minute or more to initialize, finishing with  
```
INFO  org.clulab.asist.AgentMqtt - Running.
```


### Publishing Metadata on the Message Bus




In the tomcat/tools directory, you will find the elkless_replayer script.

