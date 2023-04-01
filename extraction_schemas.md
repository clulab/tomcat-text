# Odin Extraction Schemas

----------------------------------

###  Concept

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Blue

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Blue`, `Color`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Green

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Green`, `Color`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Red

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Red`, `Color`, `Concept`] 
|keep             | `false` 

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

###  Alpha

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Alpha`, `Role`, `Entity`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Bravo

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Bravo`, `Role`, `Entity`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  Delta

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Delta`, `Role`, `Entity`, `Concept`] 
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

###  You

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`You`, `Entity`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  CoordinatedAction

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`CoordinatedAction`, `Action`, `EventLike`, `Concept`] 
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

###  AgreeWithEntity

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`AgreeWithEntity`, `Agreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _entity_ | [`Entity`] | _none_ | `true`

----------------------------------

###  AgreementToIdea

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`AgreementToIdea`, `Agreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  Unsure

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Unsure`, `Belief`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _topic_ | [`Action`] | _none_ | `true`

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

###  ReportBomb

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`ReportBomb`, `KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _location_ | [`Location`] | `?` | `false`
| _exists_ | [`Bomb`] | _none_ | `true`

----------------------------------

###  FindBomb

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`FindBomb`, `ReportBomb`, `KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _location_ | [`Location`] | `?` | `false`
| _exists_ | [`Bomb`] | _none_ | `true`
| _map_ | [`Map`] | `?` | `false`

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
| _topic_ | [`Action`] | _none_ | `true`

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
| _topic_ | [`Action`, `Entity`, `Need`] | _none_ | `true`

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
| _location_ | [`Flag`] | _none_ | `true`

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
| _item_ | [`Product`, `Coin`] | _none_ | `true`

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

###  Encouragement

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Encouragement`, `Positive`, `Sentiment`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  Gratitude

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Gratitude`, `Positive`, `Sentiment`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_No arguments_

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

###  StickToLocation

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`StickToLocation`, `TeamStrategies`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Location`] | _none_ | `true`

----------------------------------

###  StickToStrategy

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`StickToStrategy`, `TeamStrategies`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Action`] | _none_ | `true`

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

###  Extinguish

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Extinguish`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _target_ | [`Fire`] | _none_ | `true`

----------------------------------

###  Mark

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`Mark`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _agent_ | [`Entity`] | `?` | `false`
| _location_ | [`Location`] | [_none_, `?`] | [`false`, `true`]
| _target_ | [`Bomb`, `Obstacle`] | _none_ | `true`

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

###  PlaceFlag

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`PlaceFlag`, `SimpleAction`, `Action`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _location_ | [`Location`] | `?` | `false`
| _target_ | [`Flag`] | _none_ | `true`
| _flag_meaning_ | [`Concept`] | `?` | `false`

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
| _area_ | [`Location`] | _none_ | [`false`, `true`]
| _location_ | [`Location`] | `?` | `false`
| _map_ | [`Map`] | `?` | `false`
| _agent_ | [`Entity`] | [_none_, `?`] | [`false`, `true`]
| _target_ | [`Location`, `Concept`, `Direction`] | [_none_, `?`] | [`false`, `true`]

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

###  CoordinatedLocation

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`CoordinatedLocation`, `Location`, `EventLike`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _location1_ | [`Location`] | _none_ | `true`
| _location2_ | [`Location`] | _none_ | `true`

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

###  A1

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`A1`, `A`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  A2

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`A2`, `A`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  A3

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`A3`, `A`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  A4

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`A4`, `A`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  A5

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`A5`, `A`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  A6

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`A6`, `A`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  A7

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`A7`, `A`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  B1

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`B1`, `B`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  B2

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`B2`, `B`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  B3

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`B3`, `B`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  B4

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`B4`, `B`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  B5

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`B5`, `B`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  B6

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`B6`, `B`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  B7

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`B7`, `B`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  BottomSection

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`BottomSection`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  C1

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`C1`, `C`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  C2

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`C2`, `C`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  C3

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`C3`, `C`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  C4

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`C4`, `C`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  C5

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`C5`, `C`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  C6

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`C6`, `C`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  C7

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`C7`, `C`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  D1

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`D1`, `D`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  D2

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`D2`, `D`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  D3

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`D3`, `D`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  D4

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`D4`, `D`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  D5

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`D5`, `D`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  D6

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`D6`, `D`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  D7

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`D7`, `D`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  E1

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`E1`, `E`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  E2

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`E2`, `E`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  E3

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`E3`, `E`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  E4

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`E4`, `E`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  E5

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`E5`, `E`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  E6

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`E6`, `E`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  E7

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`E7`, `E`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  F1

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`F1`, `F`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  F2

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`F2`, `F`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  F3

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`F3`, `F`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  F4

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`F4`, `F`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  F5

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`F5`, `F`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  F6

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`F6`, `F`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  F7

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`F7`, `F`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  G1

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`G1`, `G`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  G2

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`G2`, `G`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  G3

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`G3`, `G`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  G4

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`G4`, `G`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  G5

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`G5`, `G`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  G6

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`G6`, `G`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  G7

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`G7`, `G`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  H1

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`H1`, `H`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  H2

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`H2`, `H`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  H3

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`H3`, `H`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  H4

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`H4`, `H`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  H5

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`H5`, `H`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  H6

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`H6`, `H`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  H7

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`H7`, `H`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  I1

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`I1`, `I`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  I2

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`I2`, `I`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  I3

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`I3`, `I`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  I4

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`I4`, `I`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  I5

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`I5`, `I`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  I6

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`I6`, `I`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  I7

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`I7`, `I`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  LeftSection

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`LeftSection`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  MiddleSection

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`MiddleSection`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  RegionGeneric

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`RegionGeneric`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  RightSection

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`RightSection`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  TopSection

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`TopSection`, `MapSection`, `Location`, `EventLike`, `Concept`] 
|keep             | `true` 

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
|keep             | [`false`, `true`] 

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
|keep             | [`false`, `true`] 

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
|keep             | [`false`, `true`] 

_No arguments_

----------------------------------

###  TwoStep

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`TwoStep`, `Sequence`, `Bomb`, `Item`, `Concept`] 
|keep             | [`false`, `true`] 

_No arguments_

----------------------------------

###  ChainBomb

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`ChainBomb`, `Type`, `Bomb`, `Item`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  MarkedBomb

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`MarkedBomb`, `Type`, `Bomb`, `Item`, `Concept`] 
|keep             | `false` 

_No arguments_

----------------------------------

###  VolatileBomb

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`VolatileBomb`, `Type`, `Bomb`, `Item`, `Concept`] 
|keep             | `false` 

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

###  CoordinatedProduct

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`CoordinatedProduct`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_Arguments_

|name        | **label(s)**  | **quantifier(s)** | **required?**|
| :--------  | :----         | :----             | :---- 
| _product2_ | [`Product`] | _none_ | `true`
| _product1_ | [`Product`] | _none_ | `true`

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
|keep             | [`false`, `true`] 

_No arguments_

----------------------------------

###  BombBeacon

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`BombBeacon`, `Beacon`, `Tool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  HazardBeacon

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`HazardBeacon`, `Beacon`, `Tool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  BombDisposer

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`BombDisposer`, `Tool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  BombPPE

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`BombPPE`, `Tool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  BombSensor

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`BombSensor`, `Tool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  FireExtinguisher

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`FireExtinguisher`, `Tool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  WireCutter

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`WireCutter`, `Tool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  BlueTool

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`BlueTool`, `WireCutter`, `Tool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  GreenTool

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`GreenTool`, `WireCutter`, `Tool`, `Product`, `Item`, `Concept`] 
|keep             | `true` 

_No arguments_

----------------------------------

###  RedTool

|Attribute        |  Value | 
| :--------       | :---- |
|label hierarchy  | [`RedTool`, `WireCutter`, `Tool`, `Product`, `Item`, `Concept`] 
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

