
# Metadata Reprocessing on the Message Bus 

Metadata recorded during testbed runs can be reprocessed at any time.  One way of doing this is by running the Dialog Agent on the Message Bus and then using the elkless_replayer script to publish the metadata to the Message Bus.   The Dialog Agent will then process the data as if it was running on the testbed.

## Preparation

Clone the tomcat and tomcat-text repos if you don't already have them:

```
git clone https://github.com/ml4ai/tomcat
git clone https://github.com/clulab/tomcat-text
```

If you have the repos already installed, make sure you have the latest version of the master branch in each with:

```
git checkout master
git pull
```

[Install the MQTT mosquitto software](Using_the_Message_Bus.md#installation) if it is not already installed on your machine.


## Starting the software


### Message Bus
[Start the Message Bus](Using_the_Message_Bus.md#running-the-message-bus) if it is not already running on your machine.


### Dialog Agent

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

The Dialog Agent will publish its text analysis on Message Bus topic **agent/dialog**.

To capture this data, start a mosquitto subcriber on this topic, and redirect its output to a file:

```
mosquitto_sub -t agent/dialog > my_output_file.json
```

The analysis is in a compact json format.  This can be made human-readable using the jq program:

```
jq < my_output_file.json > my_pretty_output_file.json
```




