ASIST Extraction Schemas
========================

Authors: Adarsh Pyarelal, Rebecca Sharp

Agent version: `4.1.5`

Document generation timestamp (UTC): `2022-04-26 16:37:06.084085`

This is automatically generated documentation of the different entities and
events being extracted by the University of Arizona [Dialog
Agent](https://github.com/clulab/tomcat-text).

Format
------

The extractions are listed below.

The full path to the leaf node in the taxonomy is represented in a hierarchical 
structure, with each level indented from a new line. If the extraction is only 
output as an argument of another extraction, rather than as a standalone 
extraction, the name of the extraction is wrapped in square brackets (`[]`).

The argument of an extraction, if any, is listed below the extraction.  If the
the argument is optional, the name is enclosed in square brackets, it is an 
optional argument. The possible labels that can be assigned to the argument 
come next, enclosed in parentheses and separated by the `|` character. 
Finally, the third component of the argument line is a list of quantifiers,
separated by commas, and enclosed in
parentheses.

[Odin](https://github.com/clulab/processors) arguments can be quantified in
several ways. First, they may be optional (`?`), meaning that if they can be
added, given a particular sentence, they will be, but if not the Mention can
still be found. Further, they can be allowed to match multiple arguments. For
example, in the sentence Ferdinand ate ice cream and pop tarts., if you made a
rule to extract eating events and used a `+` quantifier with the food argument
`(food: Food+ = ...)`, then the Mention would have two separate arguments (ice
cream and pop tarts). The quantifiers you may see are:

- `?` (arg is optional)
- `+` (you need at least one, can have as many as are there)
- `(_min_, _max_)` (you need at least min and can have up to max, where min can be 0).

Take for example the following block:

```
- Concept
  - LeafNode
    - arg1 (LeafNode1|LeafNode2) ()
    - [arg2] (LeafNode3) (?)
```

The full path to the leaf node in the taxonomy is `Concept/LeafNode`. The
extraction has two arguments, `arg1` and `arg2`, with `arg2` being optional.
The 'arg1' argument can be labeled as either `LeafNode1` or `LeafNode2` (we
elide the full taxonomy paths here for clarity). Additionally, `arg2` has an
optional quantifier.

The extractions are documented in the following section.

Extractions
-----------

```

- [Concept]
  - Direction
    - Cardinal
      - East
      - North
      - NorthEast
      - NorthWest
      - South
      - SouthEast
      - SouthWest
      - West
    - Relative
      - [Down]
      - Left
      - Right
      - [Up]
  - [Entity]
    - [DemPron]
    - [Other]
    - [Person]
    - Player
      - Blue
      - Green
      - Red
    - Role
      - Engineer
      - Medic
      - Searcher
      - Transporter
    - [Self]
    - [Team]
    - Victim
      - CriticalVictim
      - NoVictim
      - RegularVictim
        - VictimTypeA
        - VictimTypeB
      - [Type]
      - [Yellow]
    - [You]
  - EventLike
    - Action
      - ComplexAction
        - ChangePriority
          - target (Victim|Person) ()
          - [agent] (Entity) (?)

        - Continue
          - agent (Entity) ()

        - MoveVictim
          - victim (Victim) ()
          - [target] (Location) (?)
          - [agent] (Entity) (?)

        - Precedence
          - [second] (EventLike) (?)
          - [first] (EventLike) (?)

        - Search
          - [agent] (Entity) (?)
          - target (Location) ()
          - [location] (Location) (?)
          - area (Location) ()
          - [target] (Concept) (?)
          - agent (Entity) ()

      - [GenericAction]
        - [target] (Entity) (?)

      - SimpleAction
        - Block
          - [source] (Obstacle|Concept) (?)
          - [target] (Concept) (?)

        - Clear
          - [agent] (Entity) (?)
          - target (Location|Rubble) ()

        - Close
          - [target] (DemPron|Infrastructure) (?)

        - Communicate
          - Agreement
          - Disagreement
          - HelpOffer
            - agent (Self) ()
            - helpee (Entity) ()
            - [location] (Location) (?)

          - Instruction
            - [agent] (Entity) (?)
            - topic (Action) ()

            - HelpCommand
              - agent (Entity) ()
              - helpee (Entity) ()
              - [location] (Location) (?)

          - KnowledgeSharing
            - exists (Victim|Item|Person|EventLike|PuzzleConcept) ()
            - [location] (Location) (?)
            - [obstacle] (Obstacle) (?)
            - [map] (Map) (?)

          - Need
            - HelpRequest
              - [agent] (Entity) (?)
              - [location] (Location) (?)
              - [helper] (Entity) (?)

              - AmTrapped
                - [agent] (Entity) (?)
                - [location] (Location) (?)

            - NeedAction
              - [agent] (Entity) (?)
              - action (Action) ()

            - NeedItem
              - [agent] (Entity) (?)
              - item (Item) ()

            - NeedRole
              - [agent] (Entity) (?)
              - role (Role) ()

          - Plan
            - Commitment
              - DeliberatePlan
                - agent (Entity) ()
                - target (Action) ()
                - topic (Action) ()
                - [agent] (Entity) (?)

              - MakeCommitment
                - topic (Action) ()
                - [agent] (Entity) (?)

            - ContingentPlan
              - condition (Action) ()
              - solution (Action) ()

            - PlanLanguage
          - Question
            - topic (Concept) ()
            - [location] (Location) (?)

            - HowQuestion
              - topic (Action) ()

            - LocationQuestion
              - topic (Move|Entity) ()
              - [location] (Location) (?)

            - [QuestionParticle]
            - WhichVictimType
              - topic (Type) ()
              - [location] (Location) (?)

            - YesNoQuestion
              - topic (Action|Need) ()
              - [location] (Location) (?)
              - [agent] (Entity) (?)

          - ReportStatus
            - ReportItemStatus
              - DoorClosed
                - status (Concept) ()

              - DoorOpen
                - status (Concept) ()

              - ToolBroken
                - tool (Tool) ()

            - ReportLocation
              - location (Location) ()
              - agent (Entity) ()

            - RoleDeclare
              - target (Role) ()
              - agent (Entity) ()

            - RoomStatus
              - ReportThreatRoom
                - threat (ThreatRoom|ThreatRoomMarker|ThreatSign) ()
                - room (Room) ()

              - RoomClear
            - Stuck
              - [agent] (Entity) (?)
              - [location] (Location) (?)

        - Move
          - direction (Direction) ()
          - [agent] (Entity) (?)

          - Enter
            - [target] (Location) (?)
            - [agent] (Entity) (?)
            - target (Location|Deictic) ()

          - MoveFrom
            - target (Location|Deictic) ()
            - [agent] (Entity) (?)

          - MoveTo
            - target (Location|Entity|Deictic) ()
            - [agent] (Entity) (?)

          - OnMyWay
            - [target] (Location|Entity) (?)

        - Open
          - [agent] (Entity) (?)
          - target (Infrastructure) ()
          - [location] (Location) (?)
          - [map] (Map) (?)

        - PlaceMarker
          - target (MarkerBlock) ()
          - [location] (Location) (?)
          - [marker_meaning] (Concept) (?)

        - RescueInteractions
          - Save
            - target (Victim|Person|Entity) ()
            - [location] (Location) (?)
            - [agent] (Entity) (?)

          - Stabilize
            - target (Victim|Person) ()
            - [location] (Location) (?)
            - [agent] (Entity) (?)

          - WakeUp
            - target (Person|Victim) ()
            - [location] (Location) (?)
            - [agent] (Entity) (?)

        - RoleSwitch
          - [agent] (Entity) (?)
          - target (Role) ()

        - Sight
          - [agent] (Entity) (?)
          - target (EventLike|Item|Entity) ()
          - [location] (Location) (?)
          - [map] (Map) (?)
          - item (Item) ()
          - map (Map) ()

        - Toggle
          - [agent] (Entity) (?)
          - target (Switch) ()
          - [location] (Location) (?)
          - [map] (Map) (?)

    - Location
      - target (Concept) ()

      - Inferred
        - [Deictic]
      - Infrastructure
        - [Room]
          - A1
          - A2
          - A3
          - A4
          - A4A
          - B1
          - B2
          - B3
          - B4
          - B5
          - B6
          - B7
          - B8
          - B9
          - C1
          - C2
          - C3
          - C4
          - C5
          - C6
          - C7
          - C8
          - D1
          - D2
          - D3
          - D4
          - E1
          - E2
          - E3
          - E4
          - E5
          - F1
          - F2
          - F3
          - F4
          - F5
          - G1
          - G2
          - G3
          - H1
          - H1A
          - H2
          - I1
          - I1A
          - I2
          - I2A
          - I3
          - I3A
          - I4
          - I4A
          - J1
          - J2
          - J3
          - J4
          - K1
          - K2
          - K3
          - K4
          - L1
          - L2
          - L3
          - M1
          - M2
          - M3
          - ThreatRoom
        - Zone
          - num (Number) ()

    - Obstacle
      - Fire
      - Rubble
    - [Time]
      - TimeUnit
  - Item
    - MarkerBlock
      - CriticalMarkerBlock
      - NoVictimMarkerBlock
      - RegularMarkerBlock
        - TypeAMarker
        - TypeBMarker
      - RubbleMarker
      - SOSMarker
      - ThreatRoomMarker
    - Tool
      - [owner] (Entity) (?)

      - Hammer
        - [owner] (Entity) (?)

      - Map
        - [owner] (Entity) (?)

        - ThreatSign
          - [Threat]
      - MedKit
        - [owner] (Entity) (?)

      - Stretcher
        - [owner] (Entity) (?)

  - [Number]
  - PuzzleConcept
    - Damage
      - [location] (Location) (?)
      - [type] (Damage) (?)

      - [MildDamage]
      - [ModerateDamage]
      - [SevereDamage]
    - Meeting
      - [location] (Location) (?)

  - [RoomTag]
  - Sentiment
    - Positive
      - Encouragement
      - Gratitude
  - Switch
  - Tense
    - [FutureTense]
      - tense (Concept) ()

    - [PastTense]
      - tense (Concept) ()

```
