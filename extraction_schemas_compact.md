ASIST Extraction Schemas
========================

Authors: Adarsh Pyarelal, Rebecca Sharp

Agent version: `5.3.1`

Document generation timestamp (UTC): `2023-03-16 22:08:10.386731`

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
    - [Blue]
    - [Green]
    - [Red]
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
      - [Alpha]
      - [Bravo]
      - [Charlie]
    - [Self]
    - [Team]
    - [You]
  - EventLike
    - Action
      - CoordinatedAction
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
          - target (Direction|Location) ()
          - [agent] (Entity) (?)
          - location (Direction|Location) ()

        - Close
          - [target] (DemPron|Infrastructure) (?)

        - [Communicate]
          - [agent] (Entity) (?)
          - [topic] (Action) (?)

          - Acknowledge
          - Agreement
            - AgreeToAction
              - action (Action) ()
              - [agent] (Entity) (?)

            - AgreementToIdea
          - Belief
            - Unsure
              - [agent] (Entity) (?)
              - topic (Action) ()

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
            - exists (PuzzleConcept|Person|Victim|EventLike|Item) ()
            - [location] (Location) (?)
            - [obstacle] (Obstacle) (?)
            - [map] (Map) (?)

            - ReportBomb
              - [agent] (Entity) (?)
              - exists (Bomb) ()
              - [location] (Location) (?)

              - FindBomb
                - exists (Bomb) ()
                - [agent] (Entity) (?)
                - [location] (Location) (?)
                - [map] (Map) (?)

          - Need
            - HelpRequest
              - [agent] (Entity|Self) (?)
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
              - topic (Entity|Move) ()
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
              - topic (Action|Entity|Need) ()
              - [location] (Location) (?)
              - [agent] (Entity) (?)

          - ReportStatus
            - AmReady
              - agent (Entity) ()

            - Completed
              - [agent] (Entity) (?)
              - target (Action) ()

            - DeclareColor
              - agent (Self) ()
              - color (Color) ()

            - LocationClear
              - [agent] (Entity) (?)
              - location (Direction|Location) ()

              - FlagAreaClear
                - location (Flag) ()

            - OutOfThing
              - [agent] (Entity) (?)
              - item (Product|Coin) ()

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
              - object (Product|Coin) ()
              - number (Number) ()
              - [object] (Item) (?)

            - RoleDeclare
              - target (Role) ()
              - agent (Entity) ()

            - Stuck
          - Sentiment
            - Positive
              - Encouragement
              - Gratitude
          - TeamStrategies
            - SpreadOut
              - [agent] (Entity) (?)

            - StickTogether
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

        - Move
          - direction (Direction) ()
          - [agent] (Entity) (?)
          - target (Item|Flag) ()

          - Enter
            - target (Deictic|Location) ()
            - [agent] (Entity) (?)

          - Meet
            - location (Flag|Beacon|Location) ()
            - [agent] (Entity) (?)
            - [target] (Entity) (?)

          - MoveEntity
            - target (Entity) ()
            - [agent] (Entity) (?)

          - MoveFrom
            - target (Deictic|Location) ()
            - [agent] (Entity) (?)

          - MoveTo
            - target (Deictic|Entity|Location) ()
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
            - [target] (Entity|Location) (?)

        - Open
          - [agent] (Entity) (?)
          - target (Infrastructure) ()
          - [location] (Location) (?)
          - [map] (Map) (?)

        - PlaceFlag
          - target (Flag) ()
          - [location] (Location) (?)
          - [flag_meaning] (Concept) (?)

        - Precedence
          - second (EventLike) ()
          - [first] (EventLike) (?)
          - [second] (EventLike) (?)
          - first (EventLike) ()

        - Remove
          - target (Item) ()
          - [agent] (Entity) (?)
          - [location] (Location) (?)

        - RoleSwitch
          - [agent] (Entity) (?)
          - target (Role) ()

        - Search
          - [agent] (Entity) (?)
          - target (Direction|Location) ()
          - [location] (Location) (?)
          - area (Location) ()
          - [target] (Location|Concept) (?)
          - agent (Entity) ()
          - [map] (Map) (?)

        - Shop
          - [agent] (Entity) (?)
          - target (Number|Product) ()
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
          - target (Item|Entity|EventLike) ()
          - [location] (Location) (?)
          - [map] (Map) (?)
          - item (Item) ()
          - map (Map) ()

        - Transfer
          - [agent] (Entity) (?)
          - [beneficiary] (Color) (?)
          - target (Product|Coin) ()

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

      - CoordinatedLocation
        - location1 (Location) ()
        - location2 (Location) ()

      - [Desert]
      - [Forest]
        - [Tree]
      - Inferred
        - [Deictic]
      - [Infrastructure]
        - [Room]
      - MapSection
        - A
          - [A1]
          - [A2]
          - [A3]
          - [A4]
          - [A5]
          - [A6]
          - [A7]
        - B
          - [B1]
          - [B2]
          - [B3]
          - [B4]
          - [B5]
          - [B6]
          - [B7]
        - C
          - [C1]
          - [C2]
          - [C3]
          - [C4]
          - [C5]
          - [C6]
          - [C7]
        - D
          - [D1]
          - [D2]
          - [D3]
          - [D4]
          - [D5]
          - [D6]
          - [D7]
        - E
          - [E1]
          - [E2]
          - [E3]
          - [E4]
          - [E5]
          - [E6]
          - [E7]
        - F
          - [F1]
          - [F2]
          - [F3]
          - [F4]
          - [F5]
          - [F6]
          - [F7]
        - G
          - [G1]
          - [G2]
          - [G3]
          - [G4]
          - [G5]
          - [G6]
          - [G7]
        - H
          - [H1]
          - [H2]
          - [H3]
          - [H4]
          - [H5]
          - [H6]
          - [H7]
        - I
          - [I1]
          - [I2]
          - [I3]
          - [I4]
          - [I5]
          - [I6]
          - [I7]
      - [Swamp]
      - [Village]
        - [Church]
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
      - Type
        - [ChainBomb]
        - [VolatileBomb]
    - Coin
    - Flag
    - [Product]
      - CoordinatedProduct
        - product1 (Product) ()
        - product2 (Product) ()

      - [Health]
      - OfEachTool
        - number (Number) ()

      - Tool
        - [owner] (Entity) (?)

        - Beacon
          - BombBeacon
          - HazardBeacon
        - BombDisposer
        - BombPPE
        - BombSensor
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
    - [All]
    - [Less]
    - [More]
    - [Some]
  - Phase
    - [Field]
    - [PlanningPhase]
    - [Store]
  - [RoomTag]
  - [Switch]
  - Tense
    - [FutureTense]
      - tense (Concept) ()

    - [PastTense]
      - tense (Concept) ()

- [Token]
```
