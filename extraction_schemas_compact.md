ASIST Extraction Schemas
========================

Authors: Adarsh Pyarelal, Rebecca Sharp

Agent version: `5.3.1`

Document generation timestamp (UTC): `2023-02-19 19:44:07.508010`

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
  - Color
    - Blue
    - Green
    - Red
  - [Direction]
    - Cardinal
      - [East]
      - [North]
      - [NorthEast]
      - [NorthWest]
      - [South]
      - [SouthEast]
      - [SouthWest]
      - [West]
    - Relative
      - [Down]
      - [Left]
      - [Right]
      - [Up]
  - [Entity]
    - [DemPron]
    - EachPlayer
    - [Other]
    - [Person]
    - Role
      - [Searcher]
    - [Self]
    - [Team]
    - Victim
      - [Yellow]
    - [You]
  - EventLike
    - Action
      - CoordinateAnd
        - action1 (Action) ()
        - action2 (Action) ()

      - SimpleAction
        - Block
          - [source] (Obstacle|Concept) (?)
          - [target] (Concept) (?)

        - ChangePriority
          - target (Person|Victim) ()
          - [agent] (Entity) (?)

        - Clear
          - target (Location) ()
          - [agent] (Entity) (?)

        - Close
          - [target] (Infrastructure|DemPron) (?)

        - [Communicate]
          - [agent] (Entity) (?)
          - [topic] (Action) (?)

          - Agreement
            - AgreeToAction
              - action (Action) ()
              - [agent] (Entity) (?)

            - AgreementToIdea
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
            - exists (Item|EventLike|Person|PuzzleConcept|Victim) ()
            - [location] (Location) (?)
            - [obstacle] (Obstacle) (?)
            - [map] (Map) (?)

          - Need
            - HelpRequest
              - [agent] (Self|Entity) (?)
              - [location] (Location) (?)
              - [helper] (Entity) (?)
              - action (Action) ()

              - AmTrapped
                - [agent] (Entity) (?)
                - [location] (Location) (?)
                - agent (Self) ()

              - NeedPresence
                - helper (Entity) ()
                - [agent] (Self) (?)
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
            - ContingentPlan
              - condition (Action) ()
              - solution (Action) ()

            - DeliberatePlan
              - agent (Entity) ()
              - target (Action) ()
              - topic (Action) ()
              - [agent] (Entity) (?)

            - [PlanLanguage]
            - Proposal
              - topic (Action) ()
              - [agent] (Entity) (?)

            - ShouldPlan
              - topic (Action) ()
              - [agent] (Entity) (?)

          - Question
            - topic (Action|Concept) ()
            - [location] (Location) (?)

            - HowQuestion
              - topic (Action) ()

            - LocationQuestion
              - topic (Move|Entity) ()
              - [location] (Location) (?)

            - NumberQuestion
              - item (Product) ()
              - topic (Action) ()
              - [topic] (Remaining) (?)

            - PriceQuestion
              - topic (Product) ()

            - [QuestionParticle]
            - WhatRemaining
            - WhichVictimType
              - topic (Type) ()
              - [location] (Location) (?)

            - YesNoQuestion
              - topic (Need|Action) ()
              - [location] (Location) (?)
              - [agent] (Entity) (?)
              - target (Entity) ()

          - ReportBomb
            - [agent] (Entity) (?)
            - bomb (Bomb) ()

          - ReportStatus
            - AmReady
              - agent (Entity) ()

            - DeclareColor
              - agent (Self) ()
              - color (Color) ()

            - OutOfThing
              - [agent] (Entity) (?)
              - object (Coin|Product) ()

            - Remaining
              - item (Item|Color) ()
              - [agent] (Entity) (?)
              - number (Number) ()

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

            - ReportPossession
              - [agent] (Entity) (?)
              - object (Coin|Product) ()
              - number (Number) ()
              - [object] (Item) (?)

            - RoleDeclare
              - target (Role) ()
              - agent (Entity) ()

            - RoomStatus
              - RoomClear
            - Stuck
          - TeamStrategies
            - Scout
              - [agent] (Entity) (?)
              - [target] (Location) (?)
              - [map] (Map) (?)

            - SpreadOut
              - [agent] (Entity) (?)

            - StickTogether
              - [agent] (Entity) (?)

            - TakeZone
              - zone (Zone) ()
              - [agent] (Entity) (?)

          - Vote
            - for (Phase) ()
            - [agent] (Entity) (?)

            - VoteFor
              - for (Phase) ()
              - [agent] (Entity) (?)

        - Defuse
          - [agent] (Entity) (?)
          - target (Bomb) ()

        - FindBomb
          - bomb (Bomb) ()
          - [agent] (Entity) (?)
          - [location] (Location) (?)
          - [map] (Map) (?)

        - Move
          - direction (Direction) ()
          - [agent] (Entity) (?)
          - target (Item) ()

          - Enter
            - target (Location|Deictic) ()
            - [agent] (Entity) (?)

          - MoveEntity
            - target (Entity) ()
            - [agent] (Entity) (?)

          - MoveFrom
            - target (Location|Deictic) ()
            - [agent] (Entity) (?)

          - MoveTo
            - target (Location|Deictic|Entity) ()
            - [agent] (Entity) (?)

          - MoveToPhase
            - MoveToField
              - target (Field) ()
              - [agent] (Entity) (?)

            - MoveToPlan
              - target (PlanningPhase) ()
              - [agent] (Entity) (?)

            - MoveToStore
              - target (Store) ()
              - [agent] (Entity) (?)

          - OnMyWay
            - [target] (Location|Entity) (?)

        - Open
          - [agent] (Entity) (?)
          - target (Infrastructure) ()
          - [location] (Location) (?)
          - [map] (Map) (?)

        - Precedence
          - [second] (EventLike) (?)
          - [first] (EventLike) (?)

        - Remove
          - target (Item) ()
          - [agent] (Entity) (?)
          - [location] (Location) (?)

        - RoleSwitch
          - [agent] (Entity) (?)
          - target (Role) ()

        - Search
          - [agent] (Entity) (?)
          - target (Location|Direction) ()
          - [location] (Location) (?)
          - area (Location) ()
          - [target] (Location|Concept) (?)
          - agent (Entity) ()

        - Shop
          - [agent] (Entity) (?)
          - target (Product) ()
          - [for] (Entity) (?)

          - Afford
            - [agent] (Entity) (?)
            - [target] (Product) (?)
            - [for] (Entity) (?)

          - ReBuy
            - [agent] (Entity) (?)
            - [target] (Product) (?)
            - [for] (Entity) (?)

        - Sight
          - [agent] (Entity) (?)
          - target (Item|EventLike|Entity) ()
          - [location] (Location) (?)
          - [map] (Map) (?)
          - item (Item) ()
          - map (Map) ()

        - Transfer
          - [agent] (Entity) (?)
          - [beneficiary] (Color) (?)
          - target (Coin|Product) ()

        - Use
          - [agent] (Entity) (?)
          - target (Item) ()
          - for (Action) ()

          - UseTool
            - [agent] (Entity) (?)
            - tool (Product) ()
            - [for] (Action) (?)

    - [Location]
      - target (Concept) ()

      - [Desert]
      - [Forest]
      - Inferred
        - [Deictic]
      - [Infrastructure]
        - [Room]
      - [Swamp]
      - [Village]
      - Zone
        - num (Number) ()

    - Obstacle
      - [Fire]
      - [Rubble]
    - [Time]
      - TimeUnit
  - Item
    - Bomb
      - BombAttributes
        - BombIsType
          - type (Sequence) ()

      - Sequence
        - FourStep
        - [OneStep]
        - ThreeStep
        - TwoStep
    - Coin
    - Flag
    - [Product]
      - [Health]
      - OfEachTool
        - number (Number) ()

      - SensorTool
      - Tool
        - [owner] (Entity) (?)

        - Beacon
        - OneUseTool
          - BlueTool
          - GreenTool
          - RedTool
    - Tools
      - GPS
      - [Hammer]
        - [owner] (Entity) (?)

      - [Map]
        - [owner] (Entity) (?)

        - [MapSector]
      - [MedKit]
        - [owner] (Entity) (?)

      - [Stretcher]
        - [owner] (Entity) (?)

  - Number
    - [Less]
    - [More]
    - [Some]
  - Phase
    - [Field]
    - [PlanningPhase]
    - [Store]
  - [RoomTag]
  - Sentiment
    - Positive
      - Encouragement
      - Gratitude
    - Unsure
  - [Switch]
  - Tense
    - [FutureTense]
      - tense (Concept) ()

    - [PastTense]
      - tense (Concept) ()

- [Token]
```
