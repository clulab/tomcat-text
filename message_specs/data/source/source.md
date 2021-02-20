# Datatype: chat_analysis_message.data.source


## TOPIC

message_spec/data/source


## Member Fields

| Field Name | Type | Description
| --- | --- | --- |
| source_type | string | Either the message bus or a file type
| source_name | String | Either a message bus topic or the filename


## Example
```json
{
  "source_type": "vtt_file",
  "source_name": "AudioTranscript.vtt"
}
```
