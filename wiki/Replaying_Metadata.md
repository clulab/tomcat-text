
# Replaying Metadata 

Metadata recorded during testbed runs can be reprocessed using the Dialog Agent by publishing it on the Message Bus.

This is done using the elkless_replayer script in the tomcat/tools directory.

### Preparation

Clone the ToMCAT repo:

```
git clone https://github.com/ml4ai/tomcat
```

Next start the Dialog Agent to monitor the Message Bus

```
sbt "runMain org.clulab.asist.RunDialogAgent mqtt localhost 1883"
```




In the tomcat/tools directory, you will find the elkless_replayer script.

