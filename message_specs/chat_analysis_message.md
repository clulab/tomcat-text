# Data type: chat_analysis_message

Structure of the chat_analysis_message datatype

## TOPIC
message_spec/header


## Member Fields

| Field Name | Type | Description
| --- | --- | --- |
| header | [header][1] | Contains the UTC message publication time, the message type, and the software version.
| msg | [msg][2] | Identifies the source of the analysis, in this case the tomcat-text dialog analyzer
| data| [data][3] | Contains the text being analyzed, the source of the text, and the extractions produced by the analysis.

[1]: https://github.com/clulab/tomcat-text/blob/dialog_agent_edits/message_specs/header/header.md
[2]: https://github.com/clulab/tomcat-text/blob/dialog_agent_edits/message_specs/msg/msg.md
[3]: https://github.com/clulab/tomcat-text/blob/dialog_agent_edits/message_specs/data/data.md


## Example
```json
{
  "header": {
    "timestamp": "2021-02-11T19:22:23.494Z",
    "message_type": "event",
    "version": "0.1"
  },
  "msg": {
    "source": "tomcat_textAnalyzer",
    "experiment_id": null,
    "timestamp": "2021-02-11T19:22:23.494Z",
    "sub_type": "Event:dialogue_event",
    "version": "0.1"
  },
  "data": {
    "participant_id": "Participant 21",
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
          },
          {
            "start-triaging": "10.602379112681499"
          },
          {
            "end-mission": "10.557661089612202"
          },
          {
            "stop-triaging-victim": "10.495014278024438"
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
          },
          {
            "spotted-injured-victim": "16.777340870340307"
          },
          {
            "finish-triaging-victim": "16.340800440275004"
          },
          {
            "spotted-severely-injured-victim": "15.606892463887728"
          }
        ]
      }
    ]
  }
}
```
