# Datatype: chat_analysis_message.data

Structure of the chat_analysis_message.data datatype

## Member Fields

| Field Name     | Type                 | Description
| ---            | ---                  | ---
| participant_id | String               | The person who produced the chat message or utterance.
| text           | String               | The dialog text being analyzed.
| source         | [Source][1]          | The origin of the text (either a filename or message bus topic).
| extractions    | Seq([Extraction][2]) | The results of the text analysis.


[1]: https://github.com/clulab/tomcat-text/blob/master/message_specs/data/source/extraction.md
[2]: https://github.com/clulab/tomcat-text/blob/master/message_specs/data/extraction/extraction.md

## Data Example

```json
{
  "participant_id": "Participant person",
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
```
