# ODIN Extraction Schemas

## agree_token_match
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Agreement, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## agreement2
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Agreement, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## alpha
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Alpha, Player, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## bravo
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Bravo, Player, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## change_priority
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [ChangePriority, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@5a1e3c31

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 3+
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## change_priority2
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [ChangePriority, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@1a36a20e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 3+
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## charlie
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Charlie, Player, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## clear_location
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Clear, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@16178c66

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## clear_rubble
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Clear, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@135c062c

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## clear_rubble_token
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Clear, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@5af9c38a
## close
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Close, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@75f02c09

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## commit_to_something
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [MakeCommitment, Commitment, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@5faefa6d

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 7+
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## continue_action
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Continue, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 3+
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## deictic_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Deictic, Inferred, Location, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## delta
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Delta, Player, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## demonstrative_pronoun
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [DemPron, Inferred, Location, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | false
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## directions
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## disagree_token_match
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Disagreement, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## east
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [East, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## encouragement1
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Encouragement, Positive, Sentiment, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## engineer
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Engineer, Role, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## fire_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Fire, Obstacle, EventLike, Concept]
priority | Prority | LowerBoundPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## foe_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Foe, Entity, Concept]
priority | Prority | LowerBoundPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## green
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Green, Victim, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | false
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## hammer
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Hammer, Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 1
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## i_am_searching
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Search, ComplexActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@2c30ead2

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## infrastructure_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Infrastructure, Location, EventLike, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## lemma_verb_dobj-craft
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Craft, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@38c1994d

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Craft
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | craft
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Concept?
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${craft_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## lemma_verb_dobj-defeat
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Defeat, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@4c256a38

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Defeat
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | defeat
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Foe
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${attack_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## lemma_verb_dobj-move
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Move, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@64f08c43

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Move
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | move
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Location
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${moveto_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## lemma_verb_dobj-search
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Search, ComplexActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@4f3d8c75

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Search
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | search
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Location
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${search_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## lemma_verb_dobj-sight
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Sight, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@7b027f2e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Sight
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | sight
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | EntityTypes
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${sight_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## lemma_verb_dobj-toggle
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Toggle, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@1c11aedc

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Toggle
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | toggle
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Switch
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${toggle_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## location_area
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Location, EventLike, Concept]
priority | Prority | LowerBoundPriority(4)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## location_clumped
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Location, EventLike, Concept]
priority | Prority | LowerBoundPriority(4)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## location_question
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [LocationQuestion, Question, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@7ce3ab89

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 3+
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## location_report
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [ReportLocation, SimpleActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@5f1ede44

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 3+
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## map_item
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Map, Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 1
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## medic
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Medic, Role, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## medkit
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [MedKit, Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 1
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## move_deixis_action
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Move, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@378d3186

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## move_nmod_action
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Move, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@267b0ee5

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## north
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [North, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## north_east
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [NorthEast, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## north_west
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [NorthWest, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## numbers
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Number, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## obstructing
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Block, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@47a55640

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## obstructing_in_the_way
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Block, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@6e604c0e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## passive-craft
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Craft, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@30bf4145

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Craft
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | craft
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Concept?
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${craft_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## passive-defeat
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Defeat, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@4e4d7d91

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Defeat
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | defeat
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Foe
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${attack_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## passive-move
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Move, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@2337354e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Move
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | move
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Location
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${moveto_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## passive-search
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Search, ComplexActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@4f5b0d1a

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Search
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | search
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Location
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${search_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## passive-sight
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Sight, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@5a4d8dd

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Sight
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | sight
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | EntityTypes
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${sight_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## passive-toggle
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Toggle, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@20208c0d

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Toggle
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | toggle
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Switch
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${toggle_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## person_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Person, Entity, Concept]
priority | Prority | LowerBoundPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## precedence1
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Precedence, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@3a275002

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 7+
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## precedence2
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Precedence, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@2d83a981

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 7+
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## precedence2_after
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Precedence, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@ec2fedc

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 7+
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## precedence3_token_after
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Precedence, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@2aa65bed
## precedence3_token_before
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Precedence, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@2a81b9fb
## prep_need_action
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Need, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 3+
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## prep_need_item
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Need, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 3+
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## prep_need_role
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Need, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 3+
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## prepnom-craft
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Craft, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@1a7ec28b

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Craft
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | craft
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Concept?
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${craft_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## prepnom-defeat
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Defeat, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@353fb06

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Defeat
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | defeat
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Foe
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${attack_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## prepnom-move
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Move, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@1b32a1b2

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Move
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | move
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Location
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${moveto_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## prepnom-search
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Search, ComplexActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@3cdb9d4e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Search
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | search
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Location
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${search_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## prepnom-sight
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Sight, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@50dd6ac4

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Sight
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | sight
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | EntityTypes
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${sight_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## prepnom-toggle
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Toggle, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@4c4a2012

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
label | String | Toggle
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
name | String | toggle
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
object_type | String | Switch
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
template_priority | String | ${rulepriority}
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
trigger | String | ${toggle_triggers}
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## question_particle
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [QuestionParticle, Question, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## relative_location
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Location, EventLike, Concept]
priority | Prority | LowerBoundPriority(4)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
first_priority | String | 2-3
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
second_priority | String | 4+
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## role_switch
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [RoleSwitch, SimpleActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@427b2718

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 3+
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## role_switch_change_to
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [RoleSwitch, SimpleActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@22aaf3f4

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 3+
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## rubble_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Rubble, Obstacle, EventLike, Concept]
priority | Prority | LowerBoundPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## search_infra_action
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Search, ComplexActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@199a262f

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## searcher
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Searcher, Role, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## self_token_capture
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Self, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## sight_presence
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Sight, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@4cddfbe

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## sight_presence2
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Sight, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@1f157bd8

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## south
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [South, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## south_east
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [SouthEast, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## south_west
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [SouthWest, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## stretcher
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Stretcher, Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 1
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## switch
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Switch, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## thank_you
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Gratitude, Positive, Sentiment, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## time
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [TimeUnit, Time, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## time_underspecified
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [TimeUnit, Time, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## tool_generic
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 1
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## triage
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Save, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@6ad50e9d

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## triage2
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Save, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4025/569920092@22b6f6b6

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
rulepriority | String | 5
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

## victim_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Victim, Entity, Concept]
priority | Prority | LowerBoundPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## we_token_capture
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Team, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## west
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [West, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## yellow
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Yellow, Victim, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | false
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## you_token_capture
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [You, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## zone
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Zone, Infrastructure, Location, EventLike, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772
## zone_conj
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Zone, Infrastructure, Location, EventLike, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3553/1944986234@162d772

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
adverbial_clause | String | advcl
after_precedence_triggers | String | after
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
alpha_triggers | String | alpha
attack_triggers | String | kill|fight|beat|defeat|hit|attack
before_precedence_triggers | String | before
block_triggers | String | block|obstruct
bravo_triggers | String | bravo|brother
change_priority_triggers | String | target|prioritize|focus
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
close_triggers | String | close|shut
complements | String | xcomp|ccomp
conjunctions | String | appos|conj|conj_|cc
continue_triggers | String | going
craft_triggers | String | craft|make|produce|brew|create
deixis_triggers | String | here|there
delta_triggers | String | delta
dem_pron_triggers | String | this|that|those|these
disagree_triggers | String | no|nah|nope|negative
east_triggers | String | east
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
encouragement_triggers2 | String | job|done|work
engineer_triggers | String | hazmat|engineer
exist_triggers | String | be
extinguish_triggers | String | extinguish|put|stop
fire_triggers | String | fire|blaze|flame
first_priority | String | 2-3
foe_triggers | String | zombie|mob|enemy|skeleton
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
medic_triggers | String | medic|paramedic|nurse|doctor|medical
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
negTriggers | String | not
north_triggers | String | north
noun_modifiers | String | amod|compound|name
objects | String | dobj
open_triggers | String | open
passive_agents | String | nmod_by|nmod_agent|agent
passive_mark_verbs | String | marked|defined|indicated|characterized
person_triggers | String | person|guy|villager|dude
plan_switch_triggers | String | start|begin|switch
prep_dobjs | String | nmod
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
quant_modifiers | String | amod|advmod
reasons | String | cause|factor|motivation|reason
report | String | cite|give|mention|provide|report
role_switch_triggers | String | switch|become
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
search_spec_triggers | String | search|service|searcher
search_triggers | String | find|hunt|look|search|check
second_priority | String | 4+
self_triggers | String | i|me
sight_triggers | String | see|observe|glimpse
south_triggers | String | south
specialist_triggers | String | specialist
stretcher_triggers | String | stretcher|carrying bed
switch_triggers | String | switch|toggle|lever|door
team_triggers | String | we|us
thanking_triggers | String | thank|thanks
then_precedence_triggers | String | then
toggle_triggers | String | flip|toggle|turn|switch|pull|open
transparent_nouns | String | level|amount|quantit
transpose_triggers | String | move|shift
triage_triggers | String | save|triage|heal|help|get|rescue
victim_triggers | String | victim|patient
west_triggers | String | west
where_triggers | String | where
you_triggers | String | you
zone_triggers | String | zone|area|side|section


</details>

