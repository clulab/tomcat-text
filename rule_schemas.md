# Odin Rule Schemas

--------

#### rule: _room_a1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`A1`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_a2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`A2`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_a3_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`A3`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_a4_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`A4`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_a4a_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`A4A`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

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

#### rule: _room_b1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`B1`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_b2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`B2`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_b3_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`B3`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_b4_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`B4`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_b5_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`B5`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_b6_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`B6`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_b7_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`B7`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_b8_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`B8`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_b9_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`B9`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

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
labels    | [`Blue`, `Player`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_c1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`C1`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_c2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`C2`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_c3_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`C3`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_c4_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`C4`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_c5_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`C5`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_c6_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`C6`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_c7_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`C7`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_c8_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`C8`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _change_priority_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ChangePriority`, `ComplexAction`, `Action`, `EventLike`, `Concept`]
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
labels    | [`ChangePriority`, `ComplexAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Person` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _clear_location_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Clear`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Location` | _none_ | `true` 

&nbsp;

--------

#### rule: _clear_rubble_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Clear`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Rubble` | _none_ | `true` 

&nbsp;

--------

#### rule: _clear_rubble_token_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Clear`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


_No arguments_

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

#### rule: _allTokens_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Concept`]
priority  | `1`
keep      | `false`


_No arguments_

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

#### rule: _critical_marker_token_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`CriticalMarkerBlock`, `MarkerBlock`, `Item`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _critical_injured_victim_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`CriticalVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _critical_solitary_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`CriticalVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _critical_victim_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`CriticalVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _critical_victim_one_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`CriticalVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _critical_victim_one_c_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`CriticalVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _victimA_a_c_type_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`CriticalVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _victimA_a_c_type_keep_false_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`CriticalVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _victimC_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`CriticalVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _victim_critical_only_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`CriticalVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_d1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`D1`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_d2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`D2`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_d3_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`D3`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_d4_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`D4`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _damage_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Damage`, `PuzzleConcept`, `Concept`]
priority  | `5+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 location | `Location` | `?` | `false` 
 type | `Damage` | `?` | `false` 

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
labels    | [`DeliberatePlan`, `Commitment`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
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
labels    | [`DeliberatePlan`, `Commitment`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
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

#### rule: _room_e1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`E1`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_e2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`E2`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_e3_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`E3`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_e4_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`E4`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_e5_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`E5`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
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
keep      | `true`


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

#### rule: _engineer_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Engineer`, `Role`, `Entity`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _engineer_alt_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Engineer`, `Role`, `Entity`, `Concept`]
priority  | `5+`
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

#### rule: _room_f1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`F1`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_f2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`F2`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_f3_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`F3`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_f4_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`F4`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_f5_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`F5`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _fire_detection_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Fire`, `Obstacle`, `EventLike`, `Concept`]
priority  | `3+`
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

#### rule: _room_g1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`G1`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_g2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`G2`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_g3_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`G3`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _GPS_token_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`GPS`, `Tool`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _genericAction_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`GenericAction`, `Action`, `EventLike`, `Concept`]
priority  | `9`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Entity` | `?` | `false` 

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
labels    | [`Green`, `Player`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_h1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`H1`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_h1a_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`H1A`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_h2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`H2`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _hammer_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Hammer`, `Tool`, `Item`, `Concept`]
priority  | `3+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 owner | `Entity` | `?` | `false` 

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

#### rule: _room_i1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`I1`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_i1A_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`I1A`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_ida_A_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`I1A`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_i2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`I2`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_i2A_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`I2A`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_i2A_single_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`I2A`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_ida_2A_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`I2A`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_i3_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`I3`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_i3A_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`I3A`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_ida_3A_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`I3A`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_i4_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`I4`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_i4A_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`I4A`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_ida_4A_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`I4A`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _infrastructure_detection_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `4+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _instruction_command_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Instruction`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 

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

#### rule: _instruction_command_topic_let_

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

#### rule: _instruction_let_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Instruction`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 

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

#### rule: _room_j1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`J1`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_j2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`J2`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_j3_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`J3`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_j4_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`J4`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_k1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`K1`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_k2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`K2`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_k3_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`K3`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_k4_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`K4`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

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

#### rule: _room_l1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`L1`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_l2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`L2`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_l3_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`L3`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
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
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _location_area_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _location_clumped_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _relative_location_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


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

#### rule: _room_m1_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`M1`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_m2_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`M2`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _room_m3_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`M3`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _commit_to_something_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MakeCommitment`, `Commitment`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `9+`
keep      | `true`
action | `Action` | `Some(preventSubjectVerbInversion)` 


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 topic | `Action` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _map_item_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Map`, `Tool`, `Item`, `Concept`]
priority  | `3+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 owner | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _marker_block_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`MarkerBlock`, `Item`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _medkit_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MedKit`, `Tool`, `Item`, `Concept`]
priority  | `3+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 owner | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _medic_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Medic`, `Role`, `Entity`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _medic_strict_token_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Medic`, `Role`, `Entity`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _medical_specialist_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Medic`, `Role`, `Entity`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _lunch_token_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Meeting`, `PuzzleConcept`, `Concept`]
priority  | `5+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _meeting_token_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Meeting`, `PuzzleConcept`, `Concept`]
priority  | `5+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _training_token_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Meeting`, `PuzzleConcept`, `Concept`]
priority  | `5+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 location | `Location` | `?` | `false` 

&nbsp;

--------

#### rule: _mild_damage_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`MildDamage`, `Damage`, `PuzzleConcept`, `Concept`]
priority  | `5+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _moderate_damage_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`ModerateDamage`, `Damage`, `PuzzleConcept`, `Concept`]
priority  | `5+`
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
keep      | `true`


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

#### rule: _move_victim_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MoveVictim`, `ComplexAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 victim | `Victim` | _none_ | `true` 
 target | `Location` | `?` | `false` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _move_victim_bring_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MoveVictim`, `ComplexAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 victim | `Victim` | _none_ | `true` 
 target | `Location` | `?` | `false` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _move_victim_take_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MoveVictim`, `ComplexAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 victim | `Victim` | _none_ | `true` 
 target | `Location` | _none_ | `true` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _move_victim_take_keep_false_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`MoveVictim`, `ComplexAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `false`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 victim | `Victim` | _none_ | `true` 
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

#### rule: _no_victim_one_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`NoVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _no_victim_marker_token_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`NoVictimMarkerBlock`, `MarkerBlock`, `Item`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _north_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`North`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _north_east_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`NorthEast`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _north_west_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`NorthWest`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _numbers_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Number`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

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

#### rule: _placeMarker1_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`PlaceMarker`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `MarkerBlock` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 marker_meaning | `Concept` | `?` | `false` 

&nbsp;

--------

#### rule: _placeMarker_leave_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`PlaceMarker`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `MarkerBlock` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 marker_meaning | `Concept` | `?` | `false` 

&nbsp;

--------

#### rule: _placeMarker_throw_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`PlaceMarker`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `MarkerBlock` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 marker_meaning | `Concept` | `?` | `false` 

&nbsp;

--------

#### rule: _plan_base_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`PlanLanguage`, `Plan`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _precedence1_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Precedence`, `ComplexAction`, `Action`, `EventLike`, `Concept`]
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
labels    | [`Precedence`, `ComplexAction`, `Action`, `EventLike`, `Concept`]
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
labels    | [`Precedence`, `ComplexAction`, `Action`, `EventLike`, `Concept`]
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
labels    | [`Precedence`, `ComplexAction`, `Action`, `EventLike`, `Concept`]
priority  | `9+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _precedence3_token_before_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Precedence`, `ComplexAction`, `Action`, `EventLike`, `Concept`]
priority  | `10+`
keep      | `true`


_No arguments_

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

#### rule: _red_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Red`, `Player`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _regular_marker_token_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`RegularMarkerBlock`, `MarkerBlock`, `Item`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _regular_victim_one_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`RegularVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

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

#### rule: _threatroom_report1_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ReportThreatRoom`, `RoomStatus`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 threat | `ThreatRoom` | _none_ | `true` 
 room | `Room` | _none_ | `true` 

&nbsp;

--------

#### rule: _threatroom_report2_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ReportThreatRoom`, `RoomStatus`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 threat | `ThreatSign` | _none_ | `true` 
 room | `Room` | _none_ | `true` 

&nbsp;

--------

#### rule: _threatroom_report3_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`ReportThreatRoom`, `RoomStatus`, `ReportStatus`, `Communicate`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `6+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 threat | `ThreatRoomMarker` | _none_ | `true` 
 room | `Room` | _none_ | `true` 

&nbsp;

--------

#### rule: _right_direction_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Right`, `Relative`, `Direction`, `Concept`]
priority  | `4+`
keep      | `true`


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
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _rubble_stuff_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Rubble`, `Obstacle`, `EventLike`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _rubble_marker_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`RubbleMarker`, `MarkerBlock`, `Item`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _SOS_marker_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`SOSMarker`, `MarkerBlock`, `Item`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _triage_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Save`, `RescueInteractions`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Victim` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _triage2_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Save`, `RescueInteractions`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`
action | `Action` | `Some(mkVictim)` 


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Person` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _triage3_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Save`, `RescueInteractions`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`
action | `Action` | `Some(mkVictim)` 


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Entity` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _triage_get_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Save`, `RescueInteractions`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Victim` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _i_am_searching_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Search`, `ComplexAction`, `Action`, `EventLike`, `Concept`]
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
labels    | [`Search`, `ComplexAction`, `Action`, `EventLike`, `Concept`]
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
labels    | [`Search`, `ComplexAction`, `Action`, `EventLike`, `Concept`]
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
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _searcher_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Searcher`, `Role`, `Entity`, `Concept`]
priority  | `5+`
keep      | `true`


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

#### rule: _severe_damage_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`SevereDamage`, `Damage`, `PuzzleConcept`, `Concept`]
priority  | `5+`
keep      | `false`


_No arguments_

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

#### rule: _south_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`South`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _south_east_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`SouthEast`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _south_west_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`SouthWest`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _stabilize_person_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Stabilize`, `RescueInteractions`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Person` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _stabilize_victim_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Stabilize`, `RescueInteractions`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Victim` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _stretcher_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Stretcher`, `Tool`, `Item`, `Concept`]
priority  | `3+`
keep      | `true`


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
keep      | `true`


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

#### rule: _threat_sign_token_unspecified_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Threat`, `ThreatSign`, `Map`, `Tool`, `Item`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _threat_room_toke_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`ThreatRoom`, `Room`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `5`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _threatmarker_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`ThreatRoomMarker`, `MarkerBlock`, `Item`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _threat_sign_token_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`ThreatSign`, `Map`, `Tool`, `Item`, `Concept`]
priority  | `3+`
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

#### rule: _lemma_verb_dobj-toggle_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Toggle`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Switch` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 map | `Map` | `?` | `false` 

&nbsp;

--------

#### rule: _passive-toggle_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Toggle`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Switch` | _none_ | `true` 

&nbsp;

--------

#### rule: _prepnom-toggle_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Toggle`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 agent | `Entity` | `?` | `false` 
 target | `Switch` | _none_ | `true` 

&nbsp;

--------

#### rule: _tool_generic_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Tool`, `Item`, `Concept`]
priority  | `3+`
keep      | `true`


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

#### rule: _transporter_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Transporter`, `Role`, `Entity`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _transporter_guy_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Transporter`, `Role`, `Entity`, `Concept`]
priority  | `5+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _victim_type_extraction_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Type`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _victim_type_extraction_kind_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Type`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _type_A_marker_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`TypeAMarker`, `RegularMarkerBlock`, `MarkerBlock`, `Item`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _type_B_marker_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`TypeBMarker`, `RegularMarkerBlock`, `MarkerBlock`, `Item`, `Concept`]
priority  | `3+`
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

#### rule: _victim_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _victimA_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`VictimTypeA`, `RegularVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _victimA_a_type_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`VictimTypeA`, `RegularVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _victimA_an_a_type_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`VictimTypeA`, `RegularVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _victimA_an_a_type_keep_false_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`VictimTypeA`, `RegularVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _victimA_a_b_type_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`VictimTypeB`, `RegularVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _victimA_a_b_type_keep_false_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`VictimTypeB`, `RegularVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _victimB_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`VictimTypeB`, `RegularVictim`, `Victim`, `Entity`, `Concept`]
priority  | `3+`
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _wakeup_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`WakeUp`, `RescueInteractions`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Person` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _wakeup_token_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`WakeUp`, `RescueInteractions`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `false`


_No arguments_

&nbsp;

--------

#### rule: _wakeup_victim_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`WakeUp`, `RescueInteractions`, `SimpleAction`, `Action`, `EventLike`, `Concept`]
priority  | `8`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 target | `Victim` | _none_ | `true` 
 location | `Location` | `?` | `false` 
 agent | `Entity` | `?` | `false` 

&nbsp;

--------

#### rule: _west_

attribute | value
-----  |   ---- 
type |  TokenExtractor
labels    | [`West`, `Cardinal`, `Direction`, `Concept`]
priority  | `4+`
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
keep      | `true`


_No arguments_

&nbsp;

--------

#### rule: _zone_conj_

attribute | value
-----  |   ---- 
type |  GraphExtractor
labels    | [`Zone`, `Infrastructure`, `Location`, `EventLike`, `Concept`]
priority  | `4+`
keep      | `true`


**argument name** | **label(s)** | **quantifier** | **required?**
:---- | :---- | :---- | :----
 num | `Number` | _none_ | `true` 

&nbsp;

