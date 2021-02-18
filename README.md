tomcat-text
===========

Natural language text processing code for the DARPA ASIST program


Running the Code
----------------

The Dialog Agent will analyze text that reads either from the message bus or 
from files.  The results are output in DialogAgentMessage format.

## Running the Dialog Agent on files

Individual files or the first level of a directory can be processed by using
the agent in file mode.  Output is written to a single output file in both cases.

```
sbt "runMain org.clulab.asist.RunDialogAgent file {input filename} {output filename}"
```

Messages read from files are expected as transcript (.vtt) that has been converted to json
using the 'scripts/vtt_to_json_msgs' script, as in the following example:

```json
{
  "header": {
    "timestamp": "2021-02-04T19:41:57.205166Z",
    "version": "0.1"
  },
  "msg": {
    "timestamp": "2021-02-04T19:41:57.205166Z",
    "experiment_id": null,
    "participant_id": "anagha mudigonda",
    "trial_id": null,
    "message_type": "observation",
    "version": "0.1",
    "source": "vtt_to_json_msgs_script"
  },
  "data": {
    "asr_system": "Zoom",
    "source_filename": "AudioTranscript_CondBtwn-TriageSignal_CondWin-na_Trial-na_Team-na_Member-21_Vers-1.vtt",
    "caption_start": "00:00:24.090",
    "caption_end": "00:00:32.040",
    "text": "Something's missing redundant or we may not answer all your questions here"
  }
}

```


## Running the Dialog Agent on the Message Bus

The agent can be run on the message bus by running it in MQTT mode with the hostname
and port of the MQTT broker.  
```
sbt "runMain org.clulab.asist.RunDialogAgent mqtt {hostname} {port}"
```
When run on the message bus, the agent will analyze messages received on two topics:

### Chat messages

Message bus topic 'observations/chat'

Message received on the chat topic are expected to have the following json format:
```json
{
  "header": {
    "timestamp": "2019-12-26T12:47:23.1234Z",
    "message_type": "chat",
    "version": "0.4"
  },
  "msg": {
    "experiment_id": "123e4567-e89b-12d3-a456-426655440000",
    "trial_id": "123e4567-e89b-12d3-a456-426655440000",
    "replay_id": "876e4567-ab65-cfe7-b208-426305dc1234",
    "timestamp": "2019-12-26T14:05:02.1412Z",
    "source": "simulator",
    "sub_type": "Event:Chat",
    "version": "0.4"
  },
  "data": {
    "mission_timer": "8 : 36",
    "sender": "Aptiminer1",
    "addressees": [
      "Player746"
    ],
    "text": "I'm in room 210"
  }
}
```

### ASR messages

Message bus topic 'agent/asr'

Messages recieved on the Automated Speech Recognition (ASR) topic are expected to have the following json format

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
    "experiment_id": "e2a3cb96-5f2f-11eb-8971-18810ee8274e",
    "trial_id": "ec76544c-7080-11eb-9123-18810ee8274e",
    "version": "0.1",
    "source": "tomcat_asr_agent",
    "sub_type": "asr"
  }
}
```
