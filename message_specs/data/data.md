# Datatype: chat_analysis_message.data 

Structure of the chat_analysis_message.data datatype


## Member Fields

| Field Name | Type | Description
| --- | --- | --- |
| label | String | The mention label
| span | String | A string of all the mention words, separated by spaces.
| arguments | String | A string of all the mention argument key labels, separated by spaces.
| text | String | What was said in the message
| timestamp | String | The UTC time the message was published, in format: YYYY-MM-DDThh:mm:ss.ssssZ
| taxonomy_matches | String array | The taxonomy mappings of the mention label 


## Data Example
```json
{
"label": "Diectic",
"span": "???",
"arguments": "???",
"text": "I am saving a victim",
"timestamp": "2019-12-26T12:47:23.1234Z",
"taxonomy_matches": "???"
}
```
