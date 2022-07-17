# tomcat-text

This repository contains natural language text processing code for the DARPA
Artificial Social Intelligence for Successful Teams (ASIST) program. See the
main ToMCAT project page for more information: https://ml4ai.github.io/tomcat.

For documentation on the entities and events being extracted by the master
branch version of the code, please see https://clulab.github.io/tomcat-text.

# Web App

The repo includes a webapp you can use to debug ODIN rules. When you input a
piece of text, it will run the text through the system and visually display any
mentions extracted by the system. The app will reload if it detects any changes
to the rule files, so you can easily jump back and forth between writing rules
and testing them. It also includes a syntax parse and specifies which rule
generated each mention.

To open the webapp run the following command from the top level directory:

    sbt webapp/run

Then navigate to the specified port using your web browser.

You can also run the Dockerized version of the webapp by running

    docker-compose up -f docker-compose.webapp.yml


# Dialog Agent

The repo also includes a Dialog Agent application that will ingest text and
output extracted events of interest for a particular domain.  Sources of Dialog
Agent input text are files, the MQTT message bus, and interactively from a
terminal.


### Stdin mode

to start the DialogAgent in `stdin` mode, invoke the following:

    sbt "runMain org.clulab.asist.apps.RunDialogAgent stdin"

In this mode, the Dialog Agent will prompt the user for text, and return the
extractions directly.


```

Dialog Agent stdin extractor running.
Enter plaintext for extraction, [CTRL-D] to exit.

> I see a green victim!
{"label":"Self","span":"I","arguments":{},"start_offset":0,"end_offset":1}
{"label":"Victim","span":"victim","arguments":{},"start_offset":14,"end_offset":20}

> There is rubble here.
{"label":"Rubble","span":"rubble","arguments":{},"start_offset":9,"end_offset":15}
{"label":"Deictic","span":"here","arguments":{},"start_offset":16,"end_offset":20}

>

```


To exit the program, press [CTRL+D].  It will take several seconds for sbt to
gracefully shut down the agent.


### File mode

To run the Dialog Agent with files, the user specifies the input and output
filenames.

    sbt "runMain org.clulab.asist.apps.RunDialogAgent file inputfile outputfile"

Supported input file types are WebVtt(.vtt), and ToMCAT metadata (.metadata).
A directory can be specified as input.  Directories are traversed one level
deep, and only the `.vtt` and `.metadata` files are processed.  Input files are
processed in alphabetical order.

The output from the file(s) written to a single output file in the order of
processing.


### MQTT mode

To run the Dialog Agent on an MQTT message bus, specify the `mqtt` run mode,
then the host and port that the MQTT message broker is running on.

    sbt "runMain org.clulab.asist.apps.RunDialogAgent mqtt hostname port"


### Reprocessing

The Dialog Agent can reprocess metadata that it has already produced.  The new output
will be identical except for the data.extractions field, which will be
replaced with extractions created with the latest Dialog Agent rules.


    sbt "runMain org.clulab.asist.apps.RunDialogAgent reprocess inputDirectory outputDirectory"

### Run evaluation app

To generate CSV files for evaluating the rules, set the
`export.ruleAnnotationDir`  and `DialogAgent.inputDir` properties
appropriately in `src/main/resources/application.conf`, and then
run the following invocation

    sbt "runMain org.clulab.asist.apps.RunExtractionEvaluation"

## Metadata Input

Messages read by the Dialog Agent, either from `.metadata` files or the message
bus, are expected to include the following JSON fields.  Extra structures and
fields are ignored.  Missing data are replaced with null values in the output
JSON.


#### Chat

```
{
  "topic": "minecraft/chat",
  "msg": {
    "experiment_id": string,
    "trial_id": string,
    "replay_root_id": string,
    "replay_id": string
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
    "replay_id": string
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
    "replay_id": string
  },
  "data": {
    "playername": string,
    "text": string
  }
}
```

When using the message bus, it is not necessary to include a `topic` JSON
element.


## Output

See below for an example of an output JSON message that is published to the
message bus.

```json
{
  "header": {
    "timestamp": "2021-10-07T18:27:42.843Z",
    "message_type": "event",
    "version": "2.3.0"
  },
  "msg": {
    "experiment_id": "367624f8-81cd-4661-a03f-b61908c39581",
    "trial_id": "78822ceb-448a-436e-a1f1-f154f2066261",
    "timestamp": "2021-10-07T18:27:42.843Z",
    "source": "AC_UAZ_TA1_DialogAgent",
    "sub_type": "Event:dialogue_event",
    "version": "2.3.0",
    "replay_root_id": "",
    "replay_id": ""
  },
  "data": {
    "participant_id": "P00012",
    "asr_msg_id": "bc36d1aa-25e6-11ec-ab58-7831c1b845fe",
    "text": "I'm going to room 204.",
    "dialog_act_label": null,
    "utterance_source": {
      "source_type": "message_bus",
      "source_name": "agent/asr/final"
    },
    "extractions": [
      {
        "labels": [
          "MoveTo",
          "Move",
          "SimpleActions",
          "Action",
          "EventLike",
          "Concept"
        ],
        "span": "going to room 204",
        "arguments": {
          "target": [
            {
              "labels": [
                "NumberedRoom",
                "Room",
                "Infrastructure",
                "Location",
                "EventLike",
                "Concept"
              ],
              "span": "room 204",
              "arguments": {
                "number": [
                  {
                    "labels": [
                      "Number",
                      "Concept"
                    ],
                    "span": "204",
                    "arguments": {},
                    "attachments": [],
                    "start_offset": 18,
                    "end_offset": 21,
                    "rule": "numbers"
                  }
                ]
              },
              "attachments": [],
              "start_offset": 13,
              "end_offset": 21,
              "rule": "room_numbered"
            }
          ]
        },
        "attachments": [
          "{\"text\":\"I\",\"agentType\":\"Self\",\"labels\":[\"Self\",\"Entity\",\"Concept\"],\"span\":[0]}"
        ],
        "start_offset": 4,
        "end_offset": 21,
        "rule": "move_nmod_action"
      },
      {
        "labels": [
          "NumberedRoom",
          "Room",
          "Infrastructure",
          "Location",
          "EventLike",
          "Concept"
        ],
        "span": "room 204",
        "arguments": {
          "number": [
            {
              "labels": [
                "Number",
                "Concept"
              ],
              "span": "204",
              "arguments": {},
              "attachments": [],
              "start_offset": 18,
              "end_offset": 21,
              "rule": "numbers"
            }
          ]
        },
        "attachments": [],
        "start_offset": 13,
        "end_offset": 21,
        "rule": "room_numbered"
      },
      {
        "labels": [
          "Room",
          "Infrastructure",
          "Location",
          "EventLike",
          "Concept"
        ],
        "span": "room",
        "arguments": {},
        "attachments": [],
        "start_offset": 13,
        "end_offset": 17,
        "rule": "room_detection"
      }
    ]
  }
}
```
