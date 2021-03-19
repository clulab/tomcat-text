# Datatype: chat_analysis_message.data.extraction

Structure of the chat_analysis_message.data.extraction datatype


## Member Fields

| Field Name       | Type                | Description
| ---              | ---                 | ---
| label            | String              | The label of the extracted event or mention
| span             | String              | The substring of text being analyzed
| arguments        | Map[String, Seq[chat_analysis_message.data.extraction]] | A mapping of labels and their extractions
| start_offset     | Integer             | The start of the span in the dialog text
| end_offset       | Integer             | The end of the span in the dialog text
| taxonomy_matches | Seq(String, String) | A list of taxonomy entries (for a given external taxonomy) that the label best aligns with, along with the corresponding alignment scores.


## Data Example

```json
{
  "label": "Save",
  "span": "triage this guy",
  "arguments": {
    "target": [
      {
        "label": "Victim",
        "span": "guy",
        "arguments": {},
        "start_offset": 18,
        "end_offset": 21,
        "taxonomy_matches": [
          {
            "stop-triaging-victim": "0.8395602656543135"
          },
          {
            "start-triaging-victim": "0.8029033009354714"
          },
          {
            "spotted-injured-victim": "0.7711013459044712"
          },
          {
            "finish-triaging-victim": "0.7611114771230145"
          },
          {
            "spotted-severely-injured-victim": "0.7505735410831119"
          }
        ]
      }
    ]
  },
  "start_offset": 6,
  "end_offset": 12,
  "taxonomy_matches": [
    {
      "start-triaging-victim": "0.6412404735269176"
    },
    {
      "move-and-mark-for-revisit": "0.6301429287571527"
    },
    {
      "stop-triaging-victim": "0.5984661144719438"
    },
    {
      "done-searching": "0.5813625507008925"
    },
    {
      "start-triaging": "0.5794733783911182"
    }
  ]
}
{
  "label": "Commit",
  "span": "can triage this guy",
  "arguments": {
    "target": [
      {
        "label": "Save",
        "span": "triage this guy",
        "arguments": {
          "target": [
            {
              "label": "Victim",
              "span": "guy",
              "arguments": {},
              "start_offset": 18,
              "end_offset": 21,
              "taxonomy_matches": [
                {
                  "stop-triaging-victim": "0.8395602656543135"
                },
                {
                  "start-triaging-victim": "0.8029033009354714"
                },
                {
                  "spotted-injured-victim": "0.7711013459044712"
                },
                {
                  "finish-triaging-victim": "0.7611114771230145"
                },
                {
                  "spotted-severely-injured-victim": "0.7505735410831119"
                }
              ]
            }
          ]
        },
        "start_offset": 6,
        "end_offset": 12,
        "taxonomy_matches": [
          {
            "start-triaging-victim": "0.6412404735269176"
          },
          {
            "move-and-mark-for-revisit": "0.6301429287571527"
          },
          {
            "stop-triaging-victim": "0.5984661144719438"
          },
          {
            "done-searching": "0.5813625507008925"
          },
          {
            "start-triaging": "0.5794733783911182"
          }
        ]
      }
    ]
  },
  "start_offset": 2,
  "end_offset": 5,
  "taxonomy_matches": []
}
{
  "label": "Victim",
  "span": "guy",
  "arguments": {},
  "start_offset": 18,
  "end_offset": 21,
  "taxonomy_matches": [
    {
      "stop-triaging-victim": "0.8395602656543135"
    },
    {
      "start-triaging-victim": "0.8029033009354714"
    },
    {
      "spotted-injured-victim": "0.7711013459044712"
    },
    {
      "finish-triaging-victim": "0.7611114771230145"
    },
    {
      "spotted-severely-injured-victim": "0.7505735410831119"
    }
  ]
}
```
