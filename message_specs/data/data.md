# Datatype: chat_analysis_message.data 

Structure of the chat_analysis_message.data datatype


## Member Fields

| Field Name | Type | Description
| --- | --- | --- |
| participant_id | String | The name of the subject
| text | String | What was said in the message
| source | Source | The origin of the text (either a file or the message bus) and either the filename or the message bus topic.
| extractions | Seq[Extraction] | A sequence of Extraction structs including taxonomy matches


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
