tomcat-text
===========

Natural language text processing code for the DARPA ASIST program


Running the Code
----------------

The Dialog Agent will analyze text that it receives either as input files or
on the Message Bus.  The results are output in DialogAgentMessage format.

### Running the Dialog Agent on files

Individual files or the first level of a directory can be processed by using
the agent in file mode.  Output is written to a single output file either way.

```
sbt "runMain org.clulab.asist.RunDialogAgent file {input filename} {output filename}"
```

### Running the Dialog Agent on the Message Bus

The agent can be run on the message bus, and will process input the same 

```
sbt "runMain org.clulab.asist.RunDialogAgent mqtt {hostname} {port}"
```
