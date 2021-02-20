# Datatype: chat_analysis_message.data.extraction

Structure of the chat_analysis_message.data.extraction datatype


## Member Fields

| Field Name       | Type                | Description
| ---              | ---                 | ---
| label            | String              | The label of the extracted event or mention
| span             | String              | The substring of text being analyzed
| arguments        | String              | A string of all the mention argument key labels, separated by spaces.
| start_offset     | Integer             | The start of the span in the dialog text
| end_offset       | Integer             | The end of the span in the dialog text
| taxonomy_matches | Seq(String, String) | A list of taxonomy entries (for a given external taxonomy) that the label best aligns with, along with the corresponding alignment scores.


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
