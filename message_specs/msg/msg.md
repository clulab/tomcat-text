# Datatype: chat_analysis_message.msg

Structure of the chat_analysis_message.msg datatype


## TOPIC

chat_analysis_message/msg


## Member fields

| Field Name | Type | Description
| --- | --- | --- |
| experiment_id | String | The experiment id this message is associated with
| timestamp | String | The UTC time the message was published, in format: YYYY-MM-DDThh:mm:ss.ssssZ
| source | String | The name of the testbed component that published this data
| sub_type | String | The subtype of the data.  This field describes the format of this particular type of data
| filename | String | Transcript file name
| version | String | The version of the sub_type format


## Example
```json
{
"experiment_id": "123e4567-e89b-12d3-a456-426655440000",
"timestamp": "2019-12-26T12:47:23.1234Z",
"source": "simulator",
"sub_type": "pickup",
"filename": "message.vtt",
"version": "1.0"
}
```
