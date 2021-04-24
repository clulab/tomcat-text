
# Replaying Metadata 

Metadata recorded during testbed runs can be reprocessed using the Dialog Agent by publishing it on the Message Bus.

This is done using the elkless_replayer script in the tomcat/tools directory.

### Preparation

Clone the tomcat and tomcat-text repos:

```
git clone https://github.com/ml4ai/tomcat
git clone https://github.com/clulab/tomcat-text
```

Prepare the metadata files you wish to reprocess by making sure their file extensions are '.metadata'

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

