tomcat-text
===========

This repository contains natural language text processing code for the DARPA
Artificial Social Intelligence for Successful Teams (ASIST) program. See the
main ToMCAT project page for more information: https://ml4ai.github.io/tomcat.

The main program in this repository is a 'dialog agent' program that can ingest
text from a number of sources (files, MQTT message bus topics) and output
extracted events of interest for a particular domain.

The Dialog Agent will analyze text that reads either from the message bus or
from files.  The results are output in DialogAgentMessage format.

The dialog agent can be run in either 'file' mode or 'mqtt' mode. The modes are
described below.

File mode
---------

The dialog agent can take as individual files or a directory as an argument
when running in file mode. When a directory path is provided, the agent will
process all the files in the first level of that directory.

    sbt "runMain org.clulab.asist.RunDialogAgent file {input filename} {output filename}"

The input files are expected to be in WebVTT file (.vtt) format.


MQTT mode
---------

The dialog agent can also be run in 'mqtt' mode to process streaming data from
an MQTT message bus. The following invocation launches the agent in mqtt mode,
specifying the host and port that the MQTT message broker is running on.

    sbt "runMain org.clulab.asist.RunDialogAgent mqtt {hostname} {port}"

To connect to a broker on localhost at the MQTT default port (1883), the agent
can be started using "mqtt" as its sole argument.

    sbt "runMain org.clulab.asist.RunDialogAgent mqtt"


When run on the message bus, the agent will analyze chat messages and ASR messages.

### Chat messages

Message bus topic: `observations/chat`

This topic represents text chat messages that players send to each other in
Minecraft.

Message received on this topic are expected to have the following json format:

```json
{
  "header": {
    "timestamp": "2019-12-26T12:47:23.1234Z",
    "message_type": "chat",
    "version": "0.4"
  },
  "msg": {
    "experiment_id": "12345678-9abc-1234-a456-123456780000",
    "trial_id": "trial 1",
    "replay_id": "5555555-ab12-ffff-b200-123456az1234",
    "timestamp": "2019-12-26T14:05:02.1412Z",
    "source": "simulator",
    "sub_type": "Event:Chat",
    "version": "0.4"
  },
  "data": {
    "mission_timer": "8 : 36",
    "sender": "Miner9",
    "addressees": [
      "Player123"
    ],
    "text": "I'm in room 210"
  }
}
```

### ASR messages

Message bus topic: `agent/asr`

This topic corresponds to utterances by dialogue participants that are
automatically transcribed in real-time using an automatic speech recognition
(ASR) service like Google Cloud Speech. Messages received on this topic are
expected to have the following format:

```json
{
  "data": {
    "text": "I am going to save a green victim.",
    "asr_system": "Google",
    "is_final": true,
    "participant_id": "participant_1"
  },
  "header": {
    "timestamp": "2021-01-19T23:27:58.633076Z",
    "message_type": "observation",
    "version": "0.1"
  },
  "msg": {
    "timestamp": "2021-01-19T23:27:58.633967Z",
    "experiment_id": "12345678-9abc-1234-a456-123456780000",
    "trial_id": "trial 1",
    "version": "0.1",
    "source": "tomcat_asr_agent",
    "sub_type": "asr"
  }
}
```


### Output 

Message bus topic: `agent/tomcat_chatbot`

The Dialog Agent will publish its analysis to the message bus in [chat_analysis_message][1] format.

[1]: https://github.com/clulab/tomcat-text/blob/master/message_specs/chat_analysis_message.md
