# Header Message Type: Agent, Data Subtype: Version Info Message Format
This data message subtype is used to communicate information about a running agent in the testbed.

## TOPIC

agent/<unique_agent_name>/versioninfo

## Message Fields

| Field Name | Type | Description
| --- | --- | ---|
| header | object | From Common Header Format section
| msg | object | From the Common Event Message Format section 
| data.agent_name | string | the name of the agent being report on
| data.version | string | The version of the agent being reported on in the format <major>.<minor>.<patch>
| data.owner | string | the name of the person or organization that owns or supports this agent
| data.agent_type | string | the type of agent.  One of "ASI", "AC" or "other".  Only ASI agents can display messages to players.
| data.source | list of string | a list of the URLs of where the agent was obtained from
| data.dependencies | list of string | a list of the dependent components of the agent
| List of configuration parameters |  |
| data.config[n].name | string | The name of the configuration parameter
| data.config[n].value | string | The value of the configuration parameter 
| List of publishes | |
| data.publishes[n].topic | string | the topic the agent publishes the message on
| data.publishes[n].message_type | string | the message_type for the published message
| data.publishes[n].sub_type | string | the sub type of the message the agent publishes
| List of subscribes | |
| data.subscribes[n].topic | string | the topic the agent subscribes to
| data.subscribes[n].message_type | string | the message_type the agent subscribes to
| data.subscribes[n].sub_type | string | the message sub_type that the agent subscribes to

## Message Example

```json
{
  "header": {
    "timestamp": "2021-12-09T04:31:34.320Z",
    "message_type": "agent",
    "version": "0.6"
  },
  "msg": {
    "experiment_id": "msg.experiment_id",
    "trial_id": "msg.trial_id",
    "timestamp": "2021-12-09T04:31:34.320Z",
    "source": "uaz_dialog_agent",
    "sub_type": "versioninfo",
    "version": "3.1.4",
    "replay_root_id": null,
    "replay_id": null
  },
  "data": {
    "agent_name": "uaz_dialog_agent",
    "owner": "University of Arizona",
    "version": "3.1.4",
    "source": [
      "https://gitlab.asist.aptima.com:5050/asist/testbed/uaz_dialog_agent:3.1.4"
    ],
    "dependencies": [],
    "config": [],
    "publishes": [
      {
        "topic": "agent/dialog",
        "message_type": "event",
        "sub_type": "Event:dialogue_event"
      },
      {
        "topic": "agent/tomcat_textAnalyzer/versioninfo",
        "message_type": "agent",
        "sub_type": "versioninfo"
      },
      {
        "topic": "agent/uaz_dialog_agent/heartbeats",
        "message_type": "status",
        "sub_type": "heartbeat"
      }
    ],
    "subscribes": [
      {
        "topic": "trial",
        "message_type": "trial",
        "sub_type": "versioninfo"
      },
      {
        "topic": "agent/asr/final",
        "message_type": "observation",
        "sub_type": "asr"
      },
      {
        "topic": "minecraft/chat",
        "message_type": "chat",
        "sub_type": "Event:Chat"
      }
    ]
  }
}

```
