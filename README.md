# tomcat-text

This repository contains natural language text processing code for the DARPA Artificial Social Intelligence for Successful Teams (ASIST) program. See the main ToMCAT project page for more information: https://ml4ai.github.io/tomcat.


# Web App

The repo includes a webapp you can use to debug ODIN rules. When you input a piece of text, it will run the text through the system and visually display any mentions extracted by the system. The app will reload if it detects any changes to the rule files, so you can easily jump back and forth between writing rules and testing them. It also includes a syntax parse and specifies which rule generated each mention. 

To open the webapp run the following command from the top level directory:
```
sbt webapp/run
```
Then navigate to the specified port using your web browser.



# Dialog Agent

The repo also includes a Dialog Agent application that will ingest text and output extracted events of interest for a particular domain.

Sources of Dialog Agent input text are files, the MQTT message bus, and interactively from a terminal.

In all cases, a final optional argument of "-m n" can be used to control the number of taxonomy matches, where n can range from 0 to 5, and defaults to 0.



## Stdin mode

```
  sbt "runMain org.clulab.asist.RunDialogAgent stdin"
```

In this mode, the Dialog Agent will prompt the user for text, and return the extractions directly.  


An example of the agent running in stdin mode, with the number of taxonomy matches at the default setting of zero

```

Dialog Agent stdin extractor running.
Enter plaintext for extraction, [CTRL-D] to exit.

> I see a green victim!
{"label":"Self","span":"I","arguments":{},"start_offset":0,"end_offset":1,"taxonomy_matches":[]}
{"label":"Victim","span":"victim","arguments":{},"start_offset":14,"end_offset":20,"taxonomy_matches":[]}

> There is rubble here.
{"label":"Rubble","span":"rubble","arguments":{},"start_offset":9,"end_offset":15,"taxonomy_matches":[]}
{"label":"Deictic","span":"here","arguments":{},"start_offset":16,"end_offset":20,"taxonomy_matches":[]}

>

```


To exit the program, press [CTRL+D].  It will take several seconds for sbt to gracefully shut down the agent.



## File mode

To run the Dialog Agent with files, the user specifies the input and output filenames, and optionally the number of taxonomy matches to return.  

```
  sbt "runMain org.clulab.asist.RunDialogAgent file inputfile outputfile"
```

  Supported input file types are WebVtt(.vtt), and TomCAT metadata (.metadata).  A directory can be specified as input.  Directories are traversed one level deep, and only the .vtt and .metadata files are processed.  Input files are processed in alphabetical order.

  The ouput from the file(s) written to a singe output file in the order of processing.  The output is in [chat_analysis_message][1] Json format.
   

### MQTT mode

To run the Dialog Agent on the MQTT Message Bus, specify the mqtt run mode, then the host and port that the MQTT message broker is running on.

```
  sbt "runMain org.clulab.asist.RunDialogAgent mqtt hostname port"
```

### Input

Messages read by the Dialog Agent, either from files or the Message Bus, are expected to include the following Json fields.  Extra structures and fields are ignored.  Missing data are replaced with null values in the output Json


#### Chat 

```
{
  "topic": "minecraft/chat",
  "msg": {
    "experiment_id": string,
    "trial_id": string,
    "replay_root_id": string,
    "replay_id": string,
  },
  "data": {
    "sender": string,
    "text": string
  }
}
```  

#### UAZ ASR

```
{
  "topic": "agent/asr/final",
  "msg": {
    "experiment_id": string,
    "trial_id": string,
    "replay_root_id": string,
    "replay_id": string,
  },
  "data": {
    "participant_id": string,
    "id": string,
    "text": string
  }
}
```

#### Aptima ASR

```
{
  "topic": "status/asistdataingester/userspeech",
  "msg": {
    "experiment_id": string,
    "trial_id": string,
    "replay_root_id": string,
    "replay_id": string,
  },
  "data": {
    "playername": string,
    "text": string
  }
}
```

### Output 

The Dialog Agent will publish its analysis to the message bus in Chat Analysis Message Json format:

```json
{
  "header": {
    "timestamp": "2021-02-11T19:22:23.494Z",
    "message_type": "event",
    "version": "0.1"
  },
  "msg": {
    "experiment_id":"123e4567-e89b-12d3-a456-426655440000",
    "trial_id": "123e4567-e89b-12d3-a456-426655440000",
    "timestamp": "2019-12-26T14:05:02.1412Z",
    "source": "tomcat_textAnalyzer",
    "sub_type": "Event:dialogue_event",
    "version": "0.1",
    "replay_root_id": "123e4567-e89b-12d3-a456-426655440000",
    "replay_id": "876e4567-ab65-cfe7-b208-426305dc1234"
  },
  "data": {
    "participant_id": "Participant 21",
    "asr_msg_id": "59678a5f-9c5b-451f-8506-04bc020f2cf3",
    "text": "Five because at least I know there was one yellow victim that died so",
    "source": {
      "source_type": "vtt_file",
      "source_name": "AudioTranscript.vtt"
    },
    "extractions": [
      {
        "label": "Sight",
        "span": "was one yellow victim",
        "arguments": "Victim",
        "start_offset": 20,
        "end_offset": 25,
        "taxonomy_matches": [
          {
            "stop-triaging": "11.709686762798679"
          },
          {
            "no-victims-spotted": "10.767969549025242"
          }
        ]
      },
      {
        "label": "Victim",
        "span": "victim",
        "arguments": "",
        "start_offset": 60,
        "end_offset": 75,
        "taxonomy_matches": [
          {
            "stop-triaging-victim": "18.593763750341402"
          },
          {
            "start-triaging-victim": "17.326888048081006"
          }
        ]
      }
    ]
  }
}
```
