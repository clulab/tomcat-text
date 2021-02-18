tomcat-text
===========

Natural language text processing code for the DARPA ASIST program

A Dialog Agent is provided that will read json input, process the text, and write
the results to json output.  Two input json formats are currently supported.

| Datatype | Description
| --- | ---
| [ASR Json][1] | Input from Automated Speech Recognition 
| [OBS Json][2] | Input from Observation chat
| [DialogAgentMessage][3] | Output from the Dialog Agent

[1]:
[2]: 
[3]: https://github.com/clulab/tomcat-text/blob/dialog_agent_edits/message_specs/chat_analysis_message.md



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
