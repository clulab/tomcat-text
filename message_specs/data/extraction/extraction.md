# Datatype: chat_analysis_message.data.extraction

Structure of the chat_analysis_message.data.extraction datatype


## Member Fields

| Field Name | Type | Description
| --- | --- | --- |
| label | String | The dialog mention label
| span | String | The text that was analyzed in the dialog
| arguments | String | A string of all the mention argument key labels, separated by spaces.
| start_offset | Integer | The start of the span in the dialog text
| end_offset | Integer | The end of the span in the dialog text
| taxonomy_matches | Seq(String, String) | The taxonomy mappings of the text analysis as key-value pairs


## Data Example
```json
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
}
```
