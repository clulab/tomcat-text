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
output extracted events of interest for a particular domain.  The Dialog Agent is run
in a variety of modes, each specific to a source of user input and expected output.


## REST API Agent
The Dialog Agent can be run as a REST API server.  Extractions are generated from plaintext input via HTTP POST request, and are returned in JSON format.

The server URL is currently http://localhost:8080.  The host and port are set in the **DialogAgent** structure defined in 
```tomcat-text/src/main/resources/application.conf```

### Starting the REST API Agent 
``` console
sbt "runMain org.clulab.asist.apps.RunDialogAgent rest"
```

The base rule path can be specified with the '--rulepath' argument.  The default rule path is ```/org/clulab/asist/grammars/master.yml``` if this argument is not set.

### Using the REST API Agent
Send an HTTP POST request to http://localhost:8080 with a plaintext string as the data.  The agent will return the extractions as a single line of JSON formatted text.

#### Example
input:
``` console
curl -d 'I see you' -X POST http://localhost:8080
```
output:
``` json
[{"arguments":{"target":[{"attachments":[],"end_offset":9,"labels":["You","Entity","Concept"],"rule":"you_token_capture","span":"you","start_offset":6}]},"attachments":[{"agentType":"Self","labels":["Self","Entity","Concept"],"span":[0],"text":"I"}],"end_offset":9,"labels":["Sight","SimpleAction","Action","EventLike","Concept"],"rule":"lemma_verb_dobj-sight_entity","span":"see you","start_offset":2}]
```

### Checking the status of the REST API Agent
Send an HTTP GET request to http://localhost:8080/status.   If the agent is running it will return a message with the uptime in seconds.

#### Example
input:

``` console
curl http://localhost:8080/status
```

output:

    Dialog Agent REST API has been running for 123.456 seconds

## Console Agent

The Dialog Agent can run interactively from a console 

### Starting the Console Agent

``` console
sbt "runMain org.clulab.asist.apps.RunDialogAgent console"
```

### Using the Console Agent

Enter text at the prompt, and the extractions are returned as lines of JSON text.

#### Example

```console
Dialog Agent version 5.2.0
Enter plaintext for extraction, two blank lines to exit.

> I see you 
{"labels":["Sight","SimpleAction","Action","EventLike","Concept"],"span":"see you","arguments":{"target":[{"labels":["You","Entity","Concept"],"span":"you","arguments":{},"attachments":[],"start_offset":6,"end_offset":9,"rule":"you_token_capture"}]},"attachments":[{"text":"I","agentType":"Self","labels":["Self","Entity","Concept"],"span":[0]}],"start_offset":2,"end_offset":9,"rule":"lemma_verb_dobj-sight_entity"}

```

## MQTT Agent

The Dialog Agent can be run on a Mosquitto Testbed Message Bus.  The user can specify the hostname and port.  If not set the default values are 'localhost' and '1883' by default.

### Starting the MQTT Agent

```console
sbt "runMain org.clulab.asist.apps.RunDialogAgent mqtt hostname port"
```
    
    
## File Agent

The Dialog Agent can process text files.  The input and output are identical to that of
the MQTT agent with the exception that regular heartbeat messages are not generated.  

### Running the File Agent

```console
sbt "runMain org.clulab.asist.apps.RunDialogAgent file inputfile outputfile"
```

Supported input file types are plaintext (.txt), WebVtt (.vtt), and ToMCAT metadata (.metadata).
A directory can be specified as input.  Directories are traversed one level
deep, and only the supported file types are processed.  Input files are
processed in alphabetical order.

Output is written to a single file in the order of processing.


## Reprocessing Agent

The Dialog Agent can reprocess metadata that it has already produced.  The new output
will be identical except for the data.extractions field, which will be
replaced with extractions created with the latest Dialog Agent rules.

### Running the Reprocessing Agent

```console
sbt "runMain org.clulab.asist.apps.RunDialogAgent reprocess inputDirectory outputDirectory"
```


# Run evaluation app

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
