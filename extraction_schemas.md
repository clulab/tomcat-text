# Odin Extraction Schemas

----------------------------------

###  Concept

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Unsure

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Unsure`, `Belief`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Action`] | _none_ | `true`

----------------------------------

###  Blue

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Blue`, `Color`, `Concept`] 
|keep             | [`false`, `true`] 

_No arguments_

----------------------------------

###  Green

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Green`, `Color`, `Concept`] 
|keep             | [`false`, `true`] 

_No arguments_

----------------------------------

###  Red

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Red`, `Color`, `Concept`] 
|keep             | [`false`, `true`] 

_No arguments_

----------------------------------

###  Direction

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Direction`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  East

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`East`, `Cardinal`, `Direction`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  North

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`North`, `Cardinal`, `Direction`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  NorthEast

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`NorthEast`, `Cardinal`, `Direction`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  NorthWest

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`NorthWest`, `Cardinal`, `Direction`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  South

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`South`, `Cardinal`, `Direction`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  SouthEast

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`SouthEast`, `Cardinal`, `Direction`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  SouthWest

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`SouthWest`, `Cardinal`, `Direction`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  West

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`West`, `Cardinal`, `Direction`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Down

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Down`, `Relative`, `Direction`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Left

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Left`, `Relative`, `Direction`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Right

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Right`, `Relative`, `Direction`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Up

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Up`, `Relative`, `Direction`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Entity

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Entity`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  DemPron

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`DemPron`, `Entity`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  EachPlayer

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`EachPlayer`, `Entity`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  Other

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Other`, `Entity`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Person

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Person`, `Entity`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Searcher

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Searcher`, `Role`, `Entity`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Self

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Self`, `Entity`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Team

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Team`, `Entity`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Yellow

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Yellow`, `Victim`, `Entity`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  You

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`You`, `Entity`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  CoordinateAnd

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`CoordinateAnd`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _action1_ | [`Action`] | _none_ | `true`
| _action2_ | [`Action`] | _none_ | `true`

----------------------------------

###  Block

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Block`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _target_ | [`Concept`] | `?` | `false`
| _source_ | [`Concept`, `Obstacle`] | `?` | `false`

----------------------------------

###  ChangePriority

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`ChangePriority`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Victim`, `Person`] | _none_ | `true`

----------------------------------

###  Clear

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Clear`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _location_ | [`Location`, `Direction`] | _none_ | [`false`, `true`]
| _target_ | [`Location`, `Direction`] | _none_ | [`false`, `true`]

----------------------------------

###  Close

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Close`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _target_ | [`Infrastructure`, `DemPron`] | `?` | `false`

----------------------------------

###  Communicate

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `false` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _topic_ | [`Action`] | `?` | `false`

----------------------------------

###  Acknowledge

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Acknowledge`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  Agreement

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Agreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  AgreeToAction

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`AgreeToAction`, `Agreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _action_ | [`Action`] | _none_ | `true`

----------------------------------

###  AgreementToIdea

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`AgreementToIdea`, `Agreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  Disagreement

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Disagreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  HelpOffer

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`HelpOffer`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Self`] | _none_ | `true`
| _location_ | [`Location`] | `?` | `false`
| _helpee_ | [`Entity`] | _none_ | [`false`, `true`]

----------------------------------

###  Instruction

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Instruction`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _topic_ | [`Action`] | _none_ | `true`

----------------------------------

###  HelpCommand

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`HelpCommand`, `Instruction`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | _none_ | [`false`, `true`]
| _location_ | [`Location`] | `?` | `false`
| _helpee_ | [`Entity`] | _none_ | `true`

----------------------------------

###  KnowledgeSharing

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _location_ | [`Location`] | `?` | `false`
| _exists_ | [`Victim`, `Person`, `EventLike`, `Item`, `PuzzleConcept`] | _none_ | `true`
| _map_ | [`Map`] | `?` | `false`
| _obstacle_ | [`Obstacle`] | `?` | `false`

----------------------------------

###  HelpRequest

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`HelpRequest`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _action_ | [`Action`] | _none_ | [`false`, `true`]
| _agent_ | [`Self`, `Entity`] | `?` | `false`
| _location_ | [`Location`] | `?` | `false`
| _helper_ | [`Entity`] | `?` | `false`

----------------------------------

###  AmTrapped

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`AmTrapped`, `HelpRequest`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`, `Self`] | [_none_, `?`] | [`false`, `true`]
| _location_ | [`Location`] | `?` | `false`

----------------------------------

###  NeedPresence

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`NeedPresence`, `HelpRequest`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Self`] | `?` | `false`
| _location_ | [`Location`] | `?` | `false`
| _helper_ | [`Entity`] | _none_ | `true`

----------------------------------

###  NeedAction

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`NeedAction`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _action_ | [`Action`] | _none_ | `true`
| _agent_ | [`Entity`] | `?` | `false`

----------------------------------

###  NeedItem

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`NeedItem`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _item_ | [`Item`] | _none_ | `true`

----------------------------------

###  NeedRole

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`NeedRole`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _role_ | [`Role`] | _none_ | `true`
| _agent_ | [`Entity`] | `?` | `false`

----------------------------------

###  ContingentPlan

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`ContingentPlan`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _solution_ | [`Action`] | _none_ | `true`
| _condition_ | [`Action`] | _none_ | `true`

----------------------------------

###  DeliberatePlan

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`DeliberatePlan`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | [_none_, `?`] | [`false`, `true`]
| _target_ | [`Action`] | _none_ | [`false`, `true`]
| _topic_ | [`Action`] | _none_ | [`false`, `true`]

----------------------------------

###  PlanLanguage

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`PlanLanguage`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Proposal

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Proposal`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _topic_ | [`Action`] | _none_ | `true`

----------------------------------

###  ShouldPlan

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`ShouldPlan`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _topic_ | [`Action`] | _none_ | `true`

----------------------------------

###  Question

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _location_ | [`Location`] | `?` | `false`
| _topic_ | [`Concept`, `Action`] | _none_ | `true`

----------------------------------

###  HowQuestion

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`HowQuestion`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _topic_ | [`Action`] | _none_ | `true`

----------------------------------

###  LocationQuestion

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`LocationQuestion`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _location_ | [`Location`] | `?` | `false`
| _topic_ | [`Entity`, `Move`] | _none_ | `true`

----------------------------------

###  NumberQuestion

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`NumberQuestion`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _topic_ | [`Action`, `Remaining`] | [_none_, `?`] | [`false`, `true`]
| _item_ | [`Product`] | _none_ | `true`

----------------------------------

###  PriceQuestion

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`PriceQuestion`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _topic_ | [`Product`] | _none_ | `true`

----------------------------------

###  QuestionParticle

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`QuestionParticle`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  WhatRemaining

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`WhatRemaining`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  WhichVictimType

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`WhichVictimType`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _location_ | [`Location`] | `?` | `false`
| _topic_ | [`Type`] | _none_ | `true`

----------------------------------

###  YesNoQuestion

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`YesNoQuestion`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _location_ | [`Location`] | `?` | `false`
| _target_ | [`Entity`] | _none_ | [`false`, `true`]
| _topic_ | [`Action`, `Need`] | _none_ | [`false`, `true`]

----------------------------------

###  ReportBomb

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`ReportBomb`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _location_ | [`Location`] | `?` | `false`
| _bomb_ | [`Bomb`] | _none_ | `true`

----------------------------------

###  AmReady

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`AmReady`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | _none_ | `true`

----------------------------------

###  Completed

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Completed`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Action`] | _none_ | `true`

----------------------------------

###  DeclareColor

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`DeclareColor`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Self`] | _none_ | `true`
| _color_ | [`Color`] | _none_ | `true`

----------------------------------

###  LocationClear

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`LocationClear`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _location_ | [`Location`, `Direction`] | _none_ | `true`

----------------------------------

###  FlagAreaClear

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`FlagAreaClear`, `LocationClear`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _target_ | [`Flag`] | _none_ | `true`

----------------------------------

###  OutOfThing

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`OutOfThing`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _object_ | [`Product`, `Coin`] | _none_ | `true`

----------------------------------

###  Remaining

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Remaining`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _number_ | [`Number`] | _none_ | [`false`, `true`]
| _item_ | [`Item`, `Color`] | _none_ | `true`

----------------------------------

###  DoorClosed

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`DoorClosed`, `ReportItemStatus`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _status_ | [`Concept`] | _none_ | `true`

----------------------------------

###  DoorOpen

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`DoorOpen`, `ReportItemStatus`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _status_ | [`Concept`] | _none_ | `true`

----------------------------------

###  ToolBroken

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`ToolBroken`, `ReportItemStatus`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _tool_ | [`Tool`] | _none_ | `true`

----------------------------------

###  ReportLocation

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`ReportLocation`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | _none_ | `true`
| _location_ | [`Location`] | _none_ | `true`

----------------------------------

###  ReportPossession

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`ReportPossession`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _object_ | [`Product`, `Item`, `Coin`] | [_none_, `?`] | [`false`, `true`]
| _number_ | [`Number`] | _none_ | [`false`, `true`]

----------------------------------

###  RoleDeclare

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`RoleDeclare`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | _none_ | `true`
| _target_ | [`Role`] | _none_ | `true`

----------------------------------

###  Stuck

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Stuck`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  Scout

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Scout`, `TeamStrategies`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Location`] | `?` | `false`
| _map_ | [`Map`] | `?` | `false`

----------------------------------

###  SpreadOut

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`SpreadOut`, `TeamStrategies`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`

----------------------------------

###  StickTogether

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`StickTogether`, `TeamStrategies`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`

----------------------------------

###  Vote

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Vote`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _for_ | [`Phase`] | _none_ | `true`

----------------------------------

###  VoteFor

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`VoteFor`, `Vote`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _for_ | [`Phase`] | _none_ | `true`

----------------------------------

###  Defuse

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Defuse`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Bomb`] | _none_ | `true`

----------------------------------

###  FindBomb

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`FindBomb`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _location_ | [`Location`] | `?` | `false`
| _bomb_ | [`Bomb`] | _none_ | `true`
| _map_ | [`Map`] | `?` | `false`

----------------------------------

###  Move

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | [`false`, `true`] 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Item`, `Flag`] | _none_ | [`false`, `true`]
| _direction_ | [`Direction`] | _none_ | [`false`, `true`]

----------------------------------

###  Enter

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Enter`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Location`, `Deictic`] | _none_ | `true`

----------------------------------

###  Meet

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Meet`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _location_ | [`Location`, `Flag`, `Beacon`] | _none_ | `true`
| _target_ | [`Entity`] | `?` | `false`

----------------------------------

###  MoveEntity

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`MoveEntity`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Entity`] | _none_ | `true`

----------------------------------

###  MoveFrom

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`MoveFrom`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | [`false`, `true`] 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Deictic`, `Location`] | _none_ | [`false`, `true`]

----------------------------------

###  MoveTo

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`MoveTo`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Location`, `Entity`, `Deictic`] | _none_ | `true`

----------------------------------

###  MoveToField

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`MoveToField`, `MoveToPhase`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Field`] | _none_ | `true`

----------------------------------

###  MoveToPlan

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`MoveToPlan`, `MoveToPhase`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`PlanningPhase`] | _none_ | `true`

----------------------------------

###  MoveToStore

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`MoveToStore`, `MoveToPhase`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Store`] | _none_ | `true`

----------------------------------

###  OnMyWay

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`OnMyWay`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _target_ | [`Location`, `Entity`] | `?` | `false`

----------------------------------

###  Open

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Open`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _location_ | [`Location`] | `?` | `false`
| _target_ | [`Infrastructure`] | _none_ | `true`
| _map_ | [`Map`] | `?` | `false`

----------------------------------

###  Precedence

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Precedence`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _second_ | [`EventLike`] | [_none_, `?`] | [`false`, `true`]
| _first_ | [`EventLike`] | [_none_, `?`] | [`false`, `true`]

----------------------------------

###  Remove

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Remove`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _location_ | [`Location`] | `?` | `false`
| _target_ | [`Item`] | _none_ | `true`

----------------------------------

###  RoleSwitch

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`RoleSwitch`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Role`] | _none_ | `true`

----------------------------------

###  Search

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Search`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | [_none_, `?`] | [`false`, `true`]
| _location_ | [`Location`] | `?` | `false`
| _target_ | [`Location`, `Concept`, `Direction`] | [_none_, `?`] | [`false`, `true`]
| _area_ | [`Location`] | _none_ | [`false`, `true`]

----------------------------------

###  Shop

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Shop`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Product`, `Number`] | _none_ | `true`
| _for_ | [`Entity`] | `?` | `false`

----------------------------------

###  Afford

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Afford`, `Shop`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Product`] | `?` | `false`
| _for_ | [`Entity`] | `?` | `false`

----------------------------------

###  ReBuy

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`ReBuy`, `Shop`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Product`] | `?` | `false`
| _for_ | [`Entity`] | `?` | `false`

----------------------------------

###  Sight

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Sight`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _location_ | [`Location`] | `?` | `false`
| _map_ | [`Map`] | [_none_, `?`] | [`false`, `true`]
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Entity`, `Item`, `EventLike`] | _none_ | [`false`, `true`]
| _item_ | [`Item`] | _none_ | [`false`, `true`]

----------------------------------

###  Transfer

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Transfer`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _beneficiary_ | [`Color`] | `?` | `false`
| _target_ | [`Coin`, `Product`] | _none_ | `true`

----------------------------------

###  Use

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Use`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Item`] | _none_ | `true`
| _for_ | [`Action`] | _none_ | `true`

----------------------------------

###  UseTool

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`UseTool`, `Use`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _for_ | [`Action`] | `?` | `false`
| _tool_ | [`Product`] | _none_ | `true`

----------------------------------

###  Location

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _target_ | [`Concept`] | _none_ | `true`

----------------------------------

###  Desert

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Desert`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Forest

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Forest`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Tree

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Tree`, `Forest`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Deictic

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Deictic`, `Inferred`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Infrastructure

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Infrastructure`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Room

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Swamp

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Swamp`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Village

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Village`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Church

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Church`, `Village`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Zone

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Zone`, `Location`, `EventLike`, `Concept`] 
|keep             | [`false`, `true`] 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _num_ | [`Number`] | _none_ | `true`

----------------------------------

###  Fire

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Fire`, `Obstacle`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Rubble

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Rubble`, `Obstacle`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Time

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Time`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  TimeUnit

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`TimeUnit`, `Time`, `EventLike`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  Bomb

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Bomb`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  BombIsType

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`BombIsType`, `BombAttributes`, `Bomb`, `Item`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _type_ | [`Sequence`] | _none_ | `true`

----------------------------------

###  Sequence

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Sequence`, `Bomb`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  FourStep

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`FourStep`, `Sequence`, `Bomb`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  OneStep

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`OneStep`, `Sequence`, `Bomb`, `Item`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  ThreeStep

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`ThreeStep`, `Sequence`, `Bomb`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  TwoStep

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`TwoStep`, `Sequence`, `Bomb`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  Coin

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Coin`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  Flag

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Flag`, `Item`, `Concept`] 
|keep             | [`false`, `true`] 

_No arguments_

----------------------------------

###  Product

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Product`, `Item`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Health

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Health`, `Product`, `Item`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  OfEachTool

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`OfEachTool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _number_ | [`Number`] | _none_ | `true`

----------------------------------

###  SensorTool

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`SensorTool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  Tool

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Tool`, `Product`, `Item`, `Concept`] 
|keep             | [`false`, `true`] 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _owner_ | [`Entity`] | `?` | `false`

----------------------------------

###  Beacon

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Beacon`, `Tool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  OneUseTool

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`OneUseTool`, `Tool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  BlueTool

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`BlueTool`, `OneUseTool`, `Tool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  GreenTool

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`GreenTool`, `OneUseTool`, `Tool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  RedTool

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`RedTool`, `OneUseTool`, `Tool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  GPS

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`GPS`, `Tools`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  Hammer

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Hammer`, `Tools`, `Item`, `Concept`] 
|keep             | `false` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _owner_ | [`Entity`] | `?` | `false`

----------------------------------

###  Map

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Map`, `Tools`, `Item`, `Concept`] 
|keep             | `false` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _owner_ | [`Entity`] | `?` | `false`

----------------------------------

###  MapSector

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`MapSector`, `Map`, `Tools`, `Item`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  MedKit

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`MedKit`, `Tools`, `Item`, `Concept`] 
|keep             | `false` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _owner_ | [`Entity`] | `?` | `false`

----------------------------------

###  Stretcher

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Stretcher`, `Tools`, `Item`, `Concept`] 
|keep             | `false` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _owner_ | [`Entity`] | `?` | `false`

----------------------------------

###  Number

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Number`, `Concept`] 
|keep             | [`false`, `true`] 

_No arguments_

----------------------------------

###  All

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`All`, `Number`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Less

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Less`, `Number`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  More

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`More`, `Number`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Some

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Some`, `Number`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Field

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Field`, `Phase`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  PlanningPhase

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`PlanningPhase`, `Phase`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Store

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Store`, `Phase`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  RoomTag

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`RoomTag`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Encouragement

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Encouragement`, `Positive`, `Sentiment`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  Gratitude

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Gratitude`, `Positive`, `Sentiment`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  Switch

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Switch`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  FutureTense

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`FutureTense`, `Tense`, `Concept`] 
|keep             | `false` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _tense_ | [`Concept`] | _none_ | `true`

----------------------------------

###  PastTense

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`PastTense`, `Tense`, `Concept`] 
|keep             | `false` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _tense_ | [`Concept`] | _none_ | `true`

----------------------------------

###  Token

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Token`] 
|keep             | `false` 

_No arguments_

