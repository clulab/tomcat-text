# Datatype: chat_analysis_message.header

All messages will start with a header stating the UTC time of the message, the software version, and the message type.

## TOPIC

message_spec/header



## Member Fields

| Field Name | Type | Description
| --- | --- | --- |
| timestamp | string | The UTC time the message was published, in format:  YYYY-MM-DDThh:mm:ss.ssssZ 
| message_type | String | One of the defined message types.
| version | String | The version of the publishing software.


## Example
```json
{
"timestamp": "2019-12-26T12:47:23.1234Z",
"message_type": "event",
"version": "1.0"
}
```
