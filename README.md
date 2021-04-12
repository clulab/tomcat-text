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

The dialog agent can be run in either 'web_vtt' mode on files, 'mqtt' mode on the Message Bus, or interactively with 'stdin'. 

File mode
---------

The dialog agent can process WebVtt files (.vtt)  as individual files or a directory containing them. When a directory path is provided, the agent will
process all the files in the first level of that directory.

    sbt "runMain org.clulab.asist.RunDialogAgent web_vtt inputfile outputfile"



The dialog agent can also process metadata from files in the same way it does when it receives messages on the Message Bus.  Specify one file or a directory.

    sbt "runMain org.clulab.asist.RunDialogAgent metadata inputfile outputfile"


Web App
---------
The repo includes a webapp you can use to debug ODIN rules. When you input a piece of text, it will run the text through the system and visually display any mentions extracted by the system. The app will reload if it detects any changes to the rule files, so you can easily jump back and forth between writing rules and testing them. It also includes a syntax parse and specifies which rule generated each mention. 

To open the webapp run the following command from the top level directory:
```
sbt webapp/run
```
Then navigate to the specified port using your web browser.


MQTT mode
---------

The dialog agent can also be run in 'mqtt' mode to process streaming data from
an MQTT message bus. The following invocation launches the agent in mqtt mode,
specifying the host and port that the MQTT message broker is running on.

    sbt "runMain org.clulab.asist.RunDialogAgent mqtt hostname port"

To connect to a broker on localhost at the MQTT default port (1883), the agent
can be started as follows:

    sbt "runMain org.clulab.asist.RunDialogAgent mqtt localhost 1883"

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
    "experiment_id":"123e4567-e89b-12d3-a456-426655440000",
    "trial_id": "123e4567-e89b-12d3-a456-426655440000",
    "timestamp": "2019-12-26T14:05:02.1412Z",
    "source": "simulator",
    "sub_type": "chat",
    "version": "0.5",
    "replay_root_id": "123e4567-e89b-12d3-a456-426655440000",
    "replay_id": "876e4567-ab65-cfe7-b208-426305dc1234",
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

### UAZ ASR messages

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
    "experiment_id":"123e4567-e89b-12d3-a456-426655440000",
    "trial_id": "123e4567-e89b-12d3-a456-426655440000",
    "timestamp": "2019-12-26T14:05:02.1412Z",
    "source": "tomcat_asr_agent",
    "sub_type": "asr",
    "version": "0.1",
    "replay_root_id": "123e4567-e89b-12d3-a456-426655440000",
    "replay_id": "876e4567-ab65-cfe7-b208-426305dc1234",
  }
}
```

### Aptima ASR messages

Message bus topic: `status/asistdataingester/userspeech`

This topic corresponds to utterances by dialogue participants that are
automatically transcribed in real-time using an automatic speech recognition
(ASR) service like Google Cloud Speech. Messages received on this topic are
expected to have the following format:

```json
{
  "msg": {
    "experiment_id":"123e4567-e89b-12d3-a456-426655440000",
    "trial_id": "123e4567-e89b-12d3-a456-426655440000",
    "timestamp": "2019-12-26T14:05:02.1412Z",
    "source": "tomcat_asr_agent",
    "sub_type": "asr",
    "version": "0.1",
    "replay_root_id": "123e4567-e89b-12d3-a456-426655440000",
    "replay_id": "876e4567-ab65-cfe7-b208-426305dc1234",
  },
  "data": {
    "text": "You want me to share my screen?",
    "playername": "intermonk"
  },
  "header": {
    "timestamp": "2021-01-19T23:27:58.633076Z",
    "message_type": "observation",
    "version": "0.1"
  }
}
```


### Output 

Message bus topic: `agent/dialog`

The Dialog Agent will publish its analysis to the message bus in [chat_analysis_message][1] format.

[1]: https://github.com/clulab/tomcat-text/blob/master/message_specs/chat_analysis_message.md
