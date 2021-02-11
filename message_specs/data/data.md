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
  "participant_id": "participant 21",
  "text": "I see a green victim",
  "source": {
    "source_type": "message_bus",
    "source_name": "message_bus_topic"
  }
  "extractions": 
}
```
