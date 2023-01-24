# Odin Rule Schemas

--------

#### rule: _shop_afford_buy_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Afford`, `Shop`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 for | `Entity` | `?` | `false` 
 target | `Product` | `?` | `false` 

&nbsp;

--------

#### rule: _shop_afford_item_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Afford`, `Shop`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Product` | `?` | `false` 

&nbsp;

--------

#### rule: _agree_token_match_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Agreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `4`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _agree_token_match_adjectives_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Agreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `4`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _agree_token_sure_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Agreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `4`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _agree_token_sure2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Agreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `4`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _agreement2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Agreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `4`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _agreement3_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Agreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `4`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _thing_is_good_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Agreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `4`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _agreement_good_plan_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`AgreementToIdea`, `Agreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `4`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _i_am_stuck_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`AmTrapped`, `HelpRequest`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _trapped_A_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`AmTrapped`, `HelpRequest`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Self` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _trapped_no_agent_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`AmTrapped`, `HelpRequest`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _obstructing_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Block`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 source | `Concept` | `?` | `false` 
 target | `Concept` | `?` | `false` 

&nbsp;

--------

#### rule: _obstructing2_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Block`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 source | `Obstacle` | `?` | `false` 
 target | `Concept` | `?` | `false` 

&nbsp;

--------

#### rule: _obstructing_in_the_way_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Block`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 source | `Obstacle` | `?` | `false` 
 target | `Concept` | `?` | `false` 

&nbsp;

--------

#### rule: _obstructing_in_the_way2_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Block`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 source | `Concept` | `?` | `false` 
 target | `Concept` | `?` | `false` 

&nbsp;

--------

#### rule: _blue_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Blue`, `Color`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _blue_single_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Blue`, `Color`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _bluetool_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`BlueTool`, `OneUseTool`, `Tool`, `Product`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _bomb1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Bomb`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _bomb_is_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`BombIsType`, `BombAttributes`, `Bomb`, `Item`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 type | `Sequence` | _none_ | `true` 

&nbsp;

--------

#### rule: _bomb_with_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`BombIsType`, `BombAttributes`, `Bomb`, `Item`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 type | `Sequence` | _none_ | `true` 

&nbsp;

--------

#### rule: _change_priority_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ChangePriority`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Victim` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _change_priority2_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ChangePriority`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Person` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _close_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Close`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Infrastructure` | `?` | `false` 

&nbsp;

--------

#### rule: _close_dem_pronoun_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Close`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `DemPron` | `?` | `false` 

&nbsp;

--------

#### rule: _coin_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Coin`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _communicate_no_object_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 topic | `Action` | `?` | `false` 

&nbsp;

--------

#### rule: _all_nps_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _ConditionalIFGALLUP_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ContingentPlan`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `10+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 condition | `Action` | _none_ | `true` 
 solution | `Action` | _none_ | `true` 

&nbsp;

--------

#### rule: _coordinate_AND_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`CoordinateAnd`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 action1 | `Action` | _none_ | `true` 
 action2 | `Action` | _none_ | `true` 

&nbsp;

--------

#### rule: _I_am_color_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`DeclareColor`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Self` | _none_ | `true` 
 color | `Color` | _none_ | `true` 

&nbsp;

--------

#### rule: _defuse1_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Defuse`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Bomb` | _none_ | `true` 

&nbsp;

--------

#### rule: _deictic_detection_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Deictic`, `Inferred`, `Location`, `EventLike`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _DeliberateFutGALLUP_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`DeliberatePlan`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `10+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | _none_ | `true` 
 target | `Action` | _none_ | `true` 

&nbsp;

--------

#### rule: _commit_to_something_plan-type_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`DeliberatePlan`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `9+`
keep      | `true`
action | `Action` | `Some(preventSubjectVerbInversion)` 


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Action` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _demonstrative_pronoun_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`DemPron`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _direction_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Direction`, `Concept`]
priority  | `5+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _disagree_token_match_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Disagreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `4`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _no_token_match_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Disagreement`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `4`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _report_door_closed_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`DoorClosed`, `ReportItemStatus`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 status | `Concept` | _none_ | `true` 

&nbsp;

--------

#### rule: _report_door_open_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`DoorOpen`, `ReportItemStatus`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 status | `Concept` | _none_ | `true` 

&nbsp;

--------

#### rule: _down_directions_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Down`, `Relative`, `Direction`, `Concept`]
priority  | `4+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _for_each_player_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`EachPlayer`, `Entity`, `Concept`]
priority  | `7`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _east_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`East`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _encouragement1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Encouragement`, `Positive`, `Sentiment`, `Concept`]
priority  | `4`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _enter_action_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Enter`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Location` | `?` | `false` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _enter_into_action_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Enter`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Location` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _enter_into_deixis_action_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Enter`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Deictic` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _pro-form-etc_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _them_token_capture_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _found_bomb_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`FindBomb`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 bomb | `Bomb` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 
 location | `Location` | `?` | `false` 
 map | `Map` | `?` | `false` 

&nbsp;

--------

#### rule: _fire_detection_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Fire`, `Obstacle`, `EventLike`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _4step_sequence_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`FourStep`, `Sequence`, `Bomb`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _abbreviated_fourstep_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`FourStep`, `Sequence`, `Bomb`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _future-going-to_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`FutureTense`, `Tense`, `Concept`]
priority  | `2`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 tense | `Concept` | _none_ | `true` 

&nbsp;

--------

#### rule: _future-modal_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`FutureTense`, `Tense`, `Concept`]
priority  | `2`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 tense | `Concept` | _none_ | `true` 

&nbsp;

--------

#### rule: _GPS_token_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`GPS`, `Tools`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _thank_you_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Gratitude`, `Positive`, `Sentiment`, `Concept`]
priority  | `4`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _green_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Green`, `Color`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _green_single_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Green`, `Color`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _greentool_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`GreenTool`, `OneUseTool`, `Tool`, `Product`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _hammer_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Hammer`, `Tools`, `Item`, `Concept`]
priority  | `4+`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 owner | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _health_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Health`, `Product`, `Item`, `Concept`]
priority  | `5+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _Help_command_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`HelpCommand`, `Instruction`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | _none_ | `true` 
 helpee | `Entity` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _Help_command_no_agent_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`HelpCommand`, `Instruction`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `7+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 helpee | `Entity` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _HelpOffer_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`HelpOffer`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Self` | _none_ | `true` 
 helpee | `Entity` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _HelpOffer_no_helpee_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`HelpOffer`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `7+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Self` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _HelpRequests_complex_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`HelpRequest`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 helper | `Entity` | `?` | `false` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _HelpRequests_simple_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`HelpRequest`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Self` | `?` | `false` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _HelpRequests_simple_action_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`HelpRequest`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Self` | `?` | `false` 
 action | `Action` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _mannerquestion_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`HowQuestion`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Action` | _none_ | `true` 

&nbsp;

--------

#### rule: _infrastructure_detection_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `4+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _instruction_command_topic_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Instruction`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 topic | `Action` | _none_ | `true` 

&nbsp;

--------

#### rule: _instruction_take_care_of_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Instruction`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 topic | `Action` | _none_ | `true` 

&nbsp;

--------

#### rule: _existential_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 exists | `Victim` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 obstacle | `Obstacle` | `?` | `false` 
 map | `Map` | `?` | `false` 

&nbsp;

--------

#### rule: _existential2_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 exists | `Person` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 obstacle | `Obstacle` | `?` | `false` 
 map | `Map` | `?` | `false` 

&nbsp;

--------

#### rule: _existential3_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 exists | `EventLike` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 map | `Map` | `?` | `false` 

&nbsp;

--------

#### rule: _existential4_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 exists | `Item` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 obstacle | `Obstacle` | `?` | `false` 
 map | `Map` | `?` | `false` 

&nbsp;

--------

#### rule: _existential5_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 exists | `PuzzleConcept` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 obstacle | `Obstacle` | `?` | `false` 
 map | `Map` | `?` | `false` 

&nbsp;

--------

#### rule: _existential_victim_only_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 exists | `Victim` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 obstacle | `Obstacle` | `?` | `false` 
 map | `Map` | `?` | `false` 

&nbsp;

--------

#### rule: _found_thing_got_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 exists | `EventLike` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _found_thing_have_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 exists | `EventLike` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _found_victim_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 exists | `Victim` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _found_victim_got_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 exists | `Victim` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _found_victim_have_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 exists | `Victim` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _found_victim_reverse_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 exists | `Victim` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _got_victim_token_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`KnowledgeSharing`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _left_direction_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Left`, `Relative`, `Direction`, `Concept`]
priority  | `4+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _less_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Less`, `Number`, `Concept`]
priority  | `3`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _location_area_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _location_clumped_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _relative_location_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Concept` | _none_ | `true` 

&nbsp;

--------

#### rule: _location_moving_question_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`LocationQuestion`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Move` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _location_question_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`LocationQuestion`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Entity` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _map_item_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Map`, `Tools`, `Item`, `Concept`]
priority  | `4+`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 owner | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _map_sectors_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`MapSector`, `Map`, `Tools`, `Item`, `Concept`]
priority  | `5+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _medkit_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MedKit`, `Tools`, `Item`, `Concept`]
priority  | `4+`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 owner | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _more_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`More`, `Number`, `Concept`]
priority  | `3`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _move_keep_false_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _move_nmod_direction_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 direction | `Direction` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _move_entity_action_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MoveEntity`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Entity` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _leave_deixis_action_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MoveFrom`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Deictic` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _leave_nmod_action_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MoveFrom`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Location` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _leave_no_location_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MoveFrom`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _move_deixis_action_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MoveTo`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Deictic` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _move_explicit_1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`MoveTo`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _move_go_to_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MoveTo`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Location` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _move_nmod_action_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MoveTo`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Location` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _move_toward_entity_action_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MoveTo`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Entity` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _prep_need_action_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`NeedAction`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 action | `Action` | _none_ | `true` 

&nbsp;

--------

#### rule: _prep_need_item_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`NeedItem`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 item | `Item` | _none_ | `true` 

&nbsp;

--------

#### rule: _HelpRequests_Presence_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`NeedPresence`, `HelpRequest`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 helper | `Entity` | `?` | `false` 
 agent | `Self` | `?` | `false` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _prep_need_role_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`NeedRole`, `Need`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 role | `Role` | _none_ | `true` 

&nbsp;

--------

#### rule: _north_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`North`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _north_east_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`NorthEast`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _north_west_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`NorthWest`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _numbers_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Number`, `Concept`]
priority  | `3`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _how_many_left_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`NumberQuestion`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 item | `Product` | _none_ | `true` 
 topic | `Remaining` | `?` | `false` 

&nbsp;

--------

#### rule: _number_question_produc_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`NumberQuestion`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 item | `Product` | _none_ | `true` 
 topic | `Action` | _none_ | `true` 

&nbsp;

--------

#### rule: _unknown_prod_numbered_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`OfEachTool`, `Product`, `Item`, `Concept`]
priority  | `7`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 number | `Number` | _none_ | `true` 

&nbsp;

--------

#### rule: _coming_on_my_way_token_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`OnMyWay`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _on_my_way_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`OnMyWay`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _on_my_way_to_you_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`OnMyWay`, `Move`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _easy_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`OneStep`, `Sequence`, `Bomb`, `Item`, `Concept`]
priority  | `5+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _single_use_tool_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`OneUseTool`, `Tool`, `Product`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _lemma_verb_dobj-open_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Open`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Infrastructure` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 map | `Map` | `?` | `false` 

&nbsp;

--------

#### rule: _passive-open_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Open`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Infrastructure` | _none_ | `true` 

&nbsp;

--------

#### rule: _prepnom-open_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Open`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Infrastructure` | _none_ | `true` 

&nbsp;

--------

#### rule: _possesive_pronoun_other_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Other`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _third_person_token_capture_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Other`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _out_of_coin_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`OutOfThing`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 object | `Coin` | _none_ | `true` 

&nbsp;

--------

#### rule: _out_of_product_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`OutOfThing`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 object | `Product` | _none_ | `true` 

&nbsp;

--------

#### rule: _past-aux_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`PastTense`, `Tense`, `Concept`]
priority  | `2`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 tense | `Concept` | _none_ | `true` 

&nbsp;

--------

#### rule: _past-simple_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`PastTense`, `Tense`, `Concept`]
priority  | `2`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _generic_person_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Person`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _person_detection_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Person`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _plan_base_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`PlanLanguage`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `5+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _precedence1_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Precedence`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `10+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 first | `EventLike` | `?` | `false` 
 second | `EventLike` | `?` | `false` 

&nbsp;

--------

#### rule: _precedence2_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Precedence`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `10+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 first | `EventLike` | `?` | `false` 
 second | `EventLike` | `?` | `false` 

&nbsp;

--------

#### rule: _precedence2_after_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Precedence`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `9+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 second | `EventLike` | `?` | `false` 
 first | `EventLike` | `?` | `false` 

&nbsp;

--------

#### rule: _precedence3_token_after_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Precedence`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `9+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _precedence3_token_before_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Precedence`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `10+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _price_question_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`PriceQuestion`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Product` | _none_ | `true` 

&nbsp;

--------

#### rule: _product_number_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Product`, `Item`, `Concept`]
priority  | `5+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _commit_to_something_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Proposal`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `9+`
keep      | `true`
action | `Action` | `Some(preventSubjectVerbInversion)` 


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Action` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _instruction_command_topic_let_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Proposal`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 topic | `Action` | _none_ | `true` 

&nbsp;

--------

#### rule: _suggest_propose_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Proposal`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Action` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _divergent_question_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Action` | _none_ | `true` 

&nbsp;

--------

#### rule: _information_gathering_question_clarification_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Concept` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _information_gathering_question_that_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Concept` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _information_gathering_question_that2_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Concept` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _information_gathering_question_that3_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Concept` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _information_gathering_token_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _what_buy_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _what_plan_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _which_question_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Concept` | _none_ | `true` 

&nbsp;

--------

#### rule: _question_particle_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`QuestionParticle`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _shop_rebuy_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ReBuy`, `Shop`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Product` | `?` | `false` 
 for | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _red_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Red`, `Color`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _red_single_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Red`, `Color`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _redtool_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`RedTool`, `OneUseTool`, `Tool`, `Product`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _is_left2_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Remaining`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 item | `Item` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _is_left_obj_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Remaining`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 item | `Item` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _is_left_of_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Remaining`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 item | `Item` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 
 number | `Number` | _none_ | `true` 

&nbsp;

--------

#### rule: _is_left_of_color_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Remaining`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 item | `Color` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 
 number | `Number` | _none_ | `true` 

&nbsp;

--------

#### rule: _got_bomb_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ReportBomb`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 bomb | `Bomb` | _none_ | `true` 

&nbsp;

--------

#### rule: _location_report_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ReportLocation`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 location | `Location` | _none_ | `true` 
 agent | `Entity` | _none_ | `true` 

&nbsp;

--------

#### rule: _possess_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ReportPossession`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 object | `Product` | _none_ | `true` 

&nbsp;

--------

#### rule: _possess_coin_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ReportPossession`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 object | `Coin` | _none_ | `true` 

&nbsp;

--------

#### rule: _possess_number_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ReportPossession`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 number | `Number` | _none_ | `true` 
 object | `Item` | `?` | `false` 

&nbsp;

--------

#### rule: _right_direction_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Right`, `Relative`, `Direction`, `Concept`]
priority  | `4+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _role_declare_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`RoleDeclare`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Role` | _none_ | `true` 
 agent | `Entity` | _none_ | `true` 

&nbsp;

--------

#### rule: _role_switch_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`RoleSwitch`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Role` | _none_ | `true` 

&nbsp;

--------

#### rule: _role_switch_change_to_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`RoleSwitch`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Role` | _none_ | `true` 

&nbsp;

--------

#### rule: _room_detection_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _room_clear_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`RoomClear`, `RoomStatus`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_tags_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`RoomTag`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _rubble_detection_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Rubble`, `Obstacle`, `EventLike`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _scout_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Scout`, `TeamStrategies`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Location` | `?` | `false` 
 map | `Map` | `?` | `false` 

&nbsp;

--------

#### rule: _i_am_searching_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Search`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`
action | `Action` | `Some(mkVictim)` 


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | _none_ | `true` 
 target | `Concept` | `?` | `false` 

&nbsp;

--------

#### rule: _lemma_verb_dobj-search_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Search`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Location` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _search_infra_action_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Search`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`
action | `Action` | `Some(mkVictim)` 


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 area | `Location` | _none_ | `true` 
 target | `Concept` | `?` | `false` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _evacuator_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Searcher`, `Role`, `Entity`, `Concept`]
priority  | `5+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _searcher_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Searcher`, `Role`, `Entity`, `Concept`]
priority  | `5+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _possesive_pronoun_my_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Self`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _self_token_capture_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Self`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _sensor_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`SensorTool`, `Product`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _sensor2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`SensorTool`, `Product`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _sequence_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Sequence`, `Bomb`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _shop1_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Shop`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Product` | `?` | `false` 
 for | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _shop_get_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Shop`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Product` | _none_ | `true` 
 for | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _shall_should_plan_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ShouldPlan`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`
action | `Action` | `Some(preventSubjectVerbInversion)` 


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Action` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _lemma_verb_dobj-sight_entity_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Sight`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Entity` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 map | `Map` | `?` | `false` 

&nbsp;

--------

#### rule: _lemma_verb_dobj-sight_event_like_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Sight`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `EventLike` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 map | `Map` | `?` | `false` 

&nbsp;

--------

#### rule: _lemma_verb_dobj-sight_item_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Sight`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Item` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 map | `Map` | `?` | `false` 

&nbsp;

--------

#### rule: _passive-sight_entity_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Sight`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Entity` | _none_ | `true` 

&nbsp;

--------

#### rule: _passive-sight_event_like_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Sight`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `EventLike` | _none_ | `true` 

&nbsp;

--------

#### rule: _passive-sight_item_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Sight`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Item` | _none_ | `true` 

&nbsp;

--------

#### rule: _prepnom-sight_entity_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Sight`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Entity` | _none_ | `true` 

&nbsp;

--------

#### rule: _prepnom-sight_event_like_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Sight`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `EventLike` | _none_ | `true` 

&nbsp;

--------

#### rule: _prepnom-sight_item_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Sight`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Item` | _none_ | `true` 

&nbsp;

--------

#### rule: _seeOnMap_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Sight`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 item | `Item` | _none_ | `true` 
 map | `Map` | _none_ | `true` 

&nbsp;

--------

#### rule: _few_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Some`, `Number`, `Concept`]
priority  | `3`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _south_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`South`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _south_east_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`SouthEast`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _south_west_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`SouthWest`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _split_up_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`SpreadOut`, `TeamStrategies`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _spread_out_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`SpreadOut`, `TeamStrategies`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _stick_together_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`StickTogether`, `TeamStrategies`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _stretcher_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Stretcher`, `Tools`, `Item`, `Concept`]
priority  | `4+`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 owner | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _cant_move_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Stuck`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _switch_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Switch`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _we_token_capture_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Team`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _medium_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`ThreeStep`, `Sequence`, `Bomb`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _threestep_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`ThreeStep`, `Sequence`, `Bomb`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _time_mention_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Time`, `EventLike`, `Concept`]
priority  | `1`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _time_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`TimeUnit`, `Time`, `EventLike`, `Concept`]
priority  | `1`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _time_underspecified_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`TimeUnit`, `Time`, `EventLike`, `Concept`]
priority  | `1`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _allTokens_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Token`]
priority  | `1`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _tool_generic_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Tool`, `Product`, `Item`, `Concept`]
priority  | `7`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _tool_generic_number_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Tool`, `Product`, `Item`, `Concept`]
priority  | `7`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _tool_generic_owner_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Tool`, `Product`, `Item`, `Concept`]
priority  | `7`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _tool_wire_snip_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Tool`, `Product`, `Item`, `Concept`]
priority  | `7`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 owner | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _report_broken_item_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ToolBroken`, `ReportItemStatus`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 tool | `Tool` | _none_ | `true` 

&nbsp;

--------

#### rule: _give_Product_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Transfer`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 beneficiary | `Color` | `?` | `false` 
 target | `Product` | _none_ | `true` 

&nbsp;

--------

#### rule: _give_coins_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Transfer`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 beneficiary | `Color` | `?` | `false` 
 target | `Coin` | _none_ | `true` 

&nbsp;

--------

#### rule: _2step_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`TwoStep`, `Sequence`, `Bomb`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _twostep_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`TwoStep`, `Sequence`, `Bomb`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _up_directions_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Up`, `Relative`, `Direction`, `Concept`]
priority  | `4+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _use_generic_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Use`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Item` | _none_ | `true` 
 for | `Action` | _none_ | `true` 

&nbsp;

--------

#### rule: _use_tool_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`UseTool`, `Use`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Product` | _none_ | `true` 
 for | `Action` | `?` | `false` 

&nbsp;

--------

#### rule: _west_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`West`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _what_remains_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`WhatRemaining`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _what_remains_we_got_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`WhatRemaining`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _type_question_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`WhichVictimType`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Type` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _type_question_what_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`WhichVictimType`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Type` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _yellow_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Yellow`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _binary_question_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`YesNoQuestion`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`
action | `Action` | `Some(requireSubjectVerbInversion)` 


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Action` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _binary_question_confirm_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`YesNoQuestion`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Entity` | _none_ | `true` 
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _binary_question_help_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`YesNoQuestion`, `Question`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`
action | `Action` | `Some(requireSubjectVerbInversion)` 


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Need` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _possesive_pronoun_you_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`You`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _you_token_capture_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`You`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _you_ya_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`You`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _zone_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Zone`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `4+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _zone_conj_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Zone`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `4+`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 num | `Number` | _none_ | `true` 

&nbsp;

