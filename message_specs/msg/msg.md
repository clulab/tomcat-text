# Datatype: chat_analysis_message.msg

Structure of the chat_analysis_message.msg datatype


## TOPIC

chat_analysis_message/msg


## Member fields

| Field Name | Type | Description
| --- | --- | --- |
| source | String | Name of the creator of this structor
| experiment_id | String | A unique identifier
| timestamp | String | The UTC time the message was published, in format: YYYY-MM-DDThh:mm:ss.ssssZ
| sub_type | String | This field describes the format of this particular type of data
| version | String | The version of the sub_type format


## Example
```json
{
  "source": "tomcat_textAnalyzer",
  "experiment_id": null,
  "timestamp": "2021-02-11T19:22:23.494Z",
  "sub_type": "Event:dialogue_event",
  "version": "0.1"
}
```
