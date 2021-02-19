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

The input files are expected to have one JSON object per line, corresponding to
utterances in a WebVTT file (.vtt) that has been converted using the
`scripts/vtt_to_json_msgs` tool. The example below shows an example JSON object
from an input file.

```json
{
  "header": {
    "timestamp": "2021-02-04T19:41:57.205166Z",
    "version": "0.1"
  },
  "msg": {
    "timestamp": "2021-02-04T19:41:57.205166Z",
    "experiment_id": "bdcd487f-b409-495e-9325-2155890a287d",
    "trial_id": "42418c83-f63a-495f-9b27-db0be18fd1cf",
    "message_type": "observation",
    "version": "0.1",
    "source": "vtt_to_json_msgs_script"
  },
  "data": {
    "asr_system": "Zoom",
    "source_filename": "AudioTranscript_CondBtwn-TriageSignal_CondWin-na_Trial-na_Team-na_Member-21_Vers-1.vtt",
    "participant_id": "anagha mudigonda",
    "caption_start": "00:00:24.090",
    "caption_end": "00:00:32.040",
    "text": "Something's missing redundant or we may not answer all your questions here"
  }
}

```


MQTT mode
---------

The dialog agent can also be run in 'mqtt' mode to process streaming data from
an MQTT message bus. The following invocation launches the agent in mqtt mode,
specifying the host and port that the MQTT message broker is running on.

    sbt "runMain org.clulab.asist.RunDialogAgent mqtt {hostname} {port}"

When run on the message bus, the agent will analyze messages received on two topics:

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
    "experiment_id": "e2a3cb96-5f2f-11eb-8971-18810ee8274e",
    "trial_id": "ec76544c-7080-11eb-9123-18810ee8274e",
    "version": "0.1",
    "source": "tomcat_asr_agent",
    "sub_type": "asr"
  }
}
```


### Output 

Message bus topic: `agent/tomcat_chatbot`

The Dialog Agent will publish its analysis to the message bus in [chat_analysis_message][1] format.

[1]: https://github.com/clulab/tomcat-text/blob/dialog_agent_edits/message_specs/chat_analysis_message.md
