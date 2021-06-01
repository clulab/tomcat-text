# ODIN Extraction Schemas

## agree_token_match
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Agreement, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## agreement2
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Agreement, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## alpha
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Alpha, Player, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## bravo
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Bravo, Player, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## change_priority
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [ChangePriority, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@1417c6c1

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
rulepriority | String | 3+
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## change_priority2
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [ChangePriority, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@3b9e9d61

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
rulepriority | String | 3+
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## charlie
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Charlie, Player, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## clear_location
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Clear, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@4abf42b4

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## clear_rubble
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Clear, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@6034b4ec

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## clear_rubble_token
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Clear, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@5b111530
## close
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Close, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@142beea6

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## commit_to_something
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [MakeCommitment, Commitment, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@24b1b475

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
rulepriority | String | 7+
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## continue_action
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Continue, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
rulepriority | String | 3+
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## deictic_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Deictic, Inferred, Location, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## delta
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Delta, Player, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## demonstrative_pronoun
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [DemPron, Inferred, Location, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | false
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## directions
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## disagree_token_match
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Disagreement, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## east
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [East, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## encouragement1
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Encouragement, Positive, Sentiment, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## engineer
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Engineer, Role, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## fire_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Fire, Obstacle, EventLike, Concept]
priority | Prority | LowerBoundPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## foe_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Foe, Entity, Concept]
priority | Prority | LowerBoundPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## green
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Green, Victim, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | false
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## hammer
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Hammer, Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
rulepriority | String | 1
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## i_am_searching
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Search, ComplexActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@61e7e7db

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## infrastructure_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Infrastructure, Location, EventLike, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## lemma_verb_dobj-craft
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Craft, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@320cc642

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
label | String | Craft
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
trigger | String | ${craft_triggers}
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
name | String | craft
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
object_type | String | Concept?
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## lemma_verb_dobj-defeat
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Defeat, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@7999407b

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
object_type | String | Foe
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
label | String | Defeat
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
trigger | String | ${attack_triggers}
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
name | String | defeat
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## lemma_verb_dobj-move
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Move, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@50a25121

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
name | String | move
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
object_type | String | Location
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
trigger | String | ${moveto_triggers}
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
label | String | Move
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## lemma_verb_dobj-search
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Search, ComplexActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@e25add1

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
label | String | Search
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
name | String | search
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
object_type | String | Location
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
trigger | String | ${search_triggers}
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## lemma_verb_dobj-sight
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Sight, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@5a4f5481

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
label | String | Sight
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
name | String | sight
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
trigger | String | ${sight_triggers}
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
object_type | String | EntityTypes
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## lemma_verb_dobj-toggle
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Toggle, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@69e64d2b

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
name | String | toggle
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
trigger | String | ${toggle_triggers}
sight_triggers | String | see|observe|glimpse
object_type | String | Switch
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
label | String | Toggle
before_precedence_triggers | String | before


</details>

## location_area
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Location, EventLike, Concept]
priority | Prority | LowerBoundPriority(4)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## location_clumped
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Location, EventLike, Concept]
priority | Prority | LowerBoundPriority(4)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## location_question
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [LocationQuestion, Question, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@2b0f226f

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
rulepriority | String | 3+
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## location_report
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [ReportLocation, SimpleActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@8e749fa

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
rulepriority | String | 3+
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## map_item
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Map, Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
rulepriority | String | 1
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## medic
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Medic, Role, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## medkit
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [MedKit, Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
rulepriority | String | 1
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## move_deixis_action
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Move, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@28acef3b

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## move_nmod_action
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Move, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@73f87010

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## north
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [North, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## north_east
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [NorthEast, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## north_west
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [NorthWest, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## numbers
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Number, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## obstructing
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Block, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@21b6285a

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## obstructing_in_the_way
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Block, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@13ac0d0f

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## passive-craft
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Craft, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@5c36ed76

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
label | String | Craft
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
trigger | String | ${craft_triggers}
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
name | String | craft
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
object_type | String | Concept?
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## passive-defeat
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Defeat, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@6c7e6e10

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
object_type | String | Foe
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
label | String | Defeat
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
trigger | String | ${attack_triggers}
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
name | String | defeat
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## passive-move
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Move, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@7c9a6f3d

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
name | String | move
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
object_type | String | Location
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
trigger | String | ${moveto_triggers}
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
label | String | Move
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## passive-search
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Search, ComplexActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@f2dd1d4

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
label | String | Search
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
name | String | search
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
object_type | String | Location
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
trigger | String | ${search_triggers}
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## passive-sight
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Sight, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@649bd01a

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
label | String | Sight
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
name | String | sight
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
trigger | String | ${sight_triggers}
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
object_type | String | EntityTypes
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## passive-toggle
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Toggle, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@7b0a2c77

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
name | String | toggle
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
trigger | String | ${toggle_triggers}
sight_triggers | String | see|observe|glimpse
object_type | String | Switch
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
label | String | Toggle
before_precedence_triggers | String | before


</details>

## person_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Person, Entity, Concept]
priority | Prority | LowerBoundPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## precedence1
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Precedence, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@544647ac

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
rulepriority | String | 7+
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## precedence2
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Precedence, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@35848f81

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
rulepriority | String | 7+
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## precedence2_after
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Precedence, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@fa5822b

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
rulepriority | String | 7+
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## precedence3_token_after
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Precedence, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@25e66e3c
## precedence3_token_before
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Precedence, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@437b260b
## prep_need_action
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Need, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
rulepriority | String | 3+
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## prep_need_item
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Need, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
rulepriority | String | 3+
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## prep_need_role
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Need, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
rulepriority | String | 3+
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## prepnom-craft
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Craft, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@52125162

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
label | String | Craft
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
trigger | String | ${craft_triggers}
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
name | String | craft
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
object_type | String | Concept?
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## prepnom-defeat
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Defeat, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@710525d1

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
object_type | String | Foe
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
label | String | Defeat
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
trigger | String | ${attack_triggers}
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
name | String | defeat
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## prepnom-move
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Move, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@67043243

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
name | String | move
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
object_type | String | Location
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
trigger | String | ${moveto_triggers}
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
label | String | Move
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## prepnom-search
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Search, ComplexActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@7dd51bd6

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
label | String | Search
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
name | String | search
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
object_type | String | Location
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
trigger | String | ${search_triggers}
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## prepnom-sight
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Sight, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@3d0ccc4f

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
label | String | Sight
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
name | String | sight
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
trigger | String | ${sight_triggers}
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
object_type | String | EntityTypes
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## prepnom-toggle
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Toggle, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@657f4d92

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
name | String | toggle
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
trigger | String | ${toggle_triggers}
sight_triggers | String | see|observe|glimpse
object_type | String | Switch
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
template_priority | String | ${rulepriority}
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
label | String | Toggle
before_precedence_triggers | String | before


</details>

## question_particle
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [QuestionParticle, Question, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## relative_location
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Location, EventLike, Concept]
priority | Prority | LowerBoundPriority(4)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
second_priority | String | 4+
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
first_priority | String | 2-3
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## role_switch
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [RoleSwitch, SimpleActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@232ffd54

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
rulepriority | String | 3+
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## role_switch_change_to
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [RoleSwitch, SimpleActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@76f1494c

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
rulepriority | String | 3+
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## rubble_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Rubble, Obstacle, EventLike, Concept]
priority | Prority | LowerBoundPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## search_infra_action
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Search, ComplexActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@2dc49d69

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## searcher
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Searcher, Role, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## self_token_capture
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Self, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## sight_presence
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Sight, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@3248569e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## sight_presence2
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Sight, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@6e51d8de

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## south
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [South, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## south_east
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [SouthEast, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## south_west
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [SouthWest, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## stretcher
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Stretcher, Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
rulepriority | String | 1
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## switch
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Switch, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## thank_you
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Gratitude, Positive, Sentiment, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## time
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [TimeUnit, Time, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## time_underspecified
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [TimeUnit, Time, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## tool_generic
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
rulepriority | String | 1
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## triage
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Save, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@12164771

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## triage2
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Save, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5680/1788324490@1b30af8f

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
rulepriority | String | 5
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

## victim_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Victim, Entity, Concept]
priority | Prority | LowerBoundPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## we_token_capture
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Team, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## west
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [West, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## yellow
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Yellow, Victim, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | false
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## you_token_capture
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [You, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## zone
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Zone, Infrastructure, Location, EventLike, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e
## zone_conj
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Zone, Infrastructure, Location, EventLike, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5208/781188475@7a11683e

<details>
<summary>Config variables</summary>

Atribute | Type | Value
----|----|----
passive_mark_verbs | String | marked|defined|indicated|characterized
search_spec_triggers | String | search|service|searcher
reasons | String | cause|factor|motivation|reason
triage_triggers | String | save|triage|heal|help|get|rescue
specialist_triggers | String | specialist
attack_triggers | String | kill|fight|beat|defeat|hit|attack
moveto_triggers | String | go|move|run|walk|come|scoot|climb|jump
zone_triggers | String | zone|area|side|section
dem_pron_triggers | String | this|that|those|these
plan_switch_triggers | String | start|begin|switch
search_triggers | String | find|hunt|look|search|check
encouragement_triggers1 | String | good|well|perfect|amazing|excellent|great|awesome
north_triggers | String | north
close_triggers | String | close|shut
west_triggers | String | west
after_precedence_triggers | String | after
you_triggers | String | you
infrastructure_triggers | String | room|door|building|stairs|window|floor|ceiling|house|roof|bathroom|hall|entrance|library|balcony
change_priority_triggers | String | target|prioritize|focus
alpha_triggers | String | alpha
open_triggers | String | open
continue_triggers | String | going
switch_triggers | String | switch|toggle|lever|door
negTriggers | String | not
second_priority | String | 4+
noun_modifiers | String | amod|compound|name
foe_triggers | String | zombie|mob|enemy|skeleton
sight_triggers | String | see|observe|glimpse
agree_triggers | String | yes|yeah|roger|acknowledge|ok|okay|agree
quant_modifiers | String | amod|advmod
team_triggers | String | we|us
complements | String | xcomp|ccomp
victim_triggers | String | victim|patient
charlie_triggers | String | charlie
clear_triggers | String | clear|remove|clear|break
self_triggers | String | i|me
medkit_triggers | String | medkit|medical kit|med kit|medicalkit|medical kid|med kid
fire_triggers | String | fire|blaze|flame
transpose_triggers | String | move|shift
hammer_triggers | String | hammer|hummer|mallet|sledgehammer|sledge hammer|sledge-hammer
engineer_triggers | String | hazmat|engineer
agents | String | nsubj|'nsubj:xsubj'|'nsubjpass:xsubj'|nsubjpass|csubj|csubjpass|<acl|nmod_along_with
thanking_triggers | String | thank|thanks
block_triggers | String | block|obstruct
deixis_triggers | String | here|there
passive_agents | String | nmod_by|nmod_agent|agent
extinguish_triggers | String | extinguish|put|stop
adverbial_clause | String | advcl
person_triggers | String | person|guy|villager|dude
toggle_triggers | String | flip|toggle|turn|switch|pull|open
search_relations | String | >nmod_in|>nmod_within|>nmod_around|>nmod_beneath
disagree_triggers | String | no|nah|nope|negative
south_triggers | String | south
craft_triggers | String | craft|make|produce|brew|create
east_triggers | String | east
stretcher_triggers | String | stretcher|carrying bed
prep_dobjs | String | nmod
rubble_triggers | String | rubble|blockage|pile|stuff|obstacle|barricade|rebel
report | String | cite|give|mention|provide|report
where_triggers | String | where
preps | String | nmod_of|nmod_in|nmod_to|nmod_over|nmod_for|nmod_than|acl_of
encouragement_triggers2 | String | job|done|work
medic_triggers | String | medic|paramedic|nurse|doctor|medical
conjunctions | String | appos|conj|conj_|cc
transparent_nouns | String | level|amount|quantit
first_priority | String | 2-3
delta_triggers | String | delta
then_precedence_triggers | String | then
objects | String | dobj
role_switch_triggers | String | switch|become
bravo_triggers | String | bravo|brother
exist_triggers | String | be
before_precedence_triggers | String | before


</details>

