
# Metadata Reprocessing on the Message Bus 

Metadata recorded during testbed runs can be reprocessed at any time.  One way of doing this is by running the Dialog Agent on the Message Bus and then using the elkless_replayer script to publish the metadata to the Message Bus.   The Dialog Agent will then process the data as if it was running on the testbed.

## Preparation

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

[Install the MQTT mosquitto software](Using_the_Message_Bus.md#installation) if is not already on your machine.


## Starting the software


### Message Bus
[Start the Message Bus](Using_the_Message_Bus.md#running-the-message-bus) 


### Dialog Agent

The Message Bus version of the Dialog Agent subscribes to the following Message Bus topics:

Topic | Publisher
--- | ---
minecraft/chat | In-game Minecraft chat text
agent/asr/final | University of Arizona ASR 
status/asistdataingester/userspeech | Aptima ASR

And will publish message text analysis to **agent/dialog**


With the Message Bus running, cd into the tomcat-text repo and start the Message Bus version of the Dialog Agent:

```
cd tomcat-text
sbt "runMain org.clulab.asist.RunDialogAgent mqtt localhost 1883"
```

The Dialog Agent will take a minute or more to initialize, finishing with  
```
INFO  org.clulab.asist.DialogAgentMqtt - Extractor initialized.
INFO  org.clulab.asist.AgentMqtt - Connected to MQTT broker at tcp://localhost:1883
INFO  org.clulab.asist.AgentMqtt - Subscribed to: minecraft/chat, agent/asr/final, status/asistdataingester/userspeech
INFO  org.clulab.asist.AgentMqtt - Publishing on: agent/dialog
INFO  org.clulab.asist.AgentMqtt - Running.
```


### Mosquitto subscriber

When the Message Bus version of the Dialog Agent receives a message, it will publish the processing results back to the Message Bus.   It does this on topic 'agent/dialog'.  In order to capture this data, 



If the Dialog Agent gets this far, you have a working "testbed" ready for the replay of metadata.

## Replaying Metadata




In the tomcat/tools directory, you will find the elkless_replayer script.

