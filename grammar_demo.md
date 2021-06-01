# ODIN Extraction Schemas

## agree_token_match
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Agreement, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@5b5dde32
## agreement2
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Agreement, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@71d46d6b
## alpha
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Alpha, Player, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@3bbd58d6
## bravo
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Bravo, Player, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@4e51f814
## change_priority
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [ChangePriority, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@7b4813a2
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@29258695
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@2311748
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@72528b5
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@7aecbb6
## clear_location
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Clear, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@4000e447
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@4a013058
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@644fc92d
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@15cd5d69
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@52a8b14b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@11e8d8b6
## close
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Close, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@b747d07
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@74ad5f15
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@63189a22
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@3462c39f
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@1cd60c51
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@29dbd29
## delta
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Delta, Player, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@2573eed2
## demonstrative_pronoun
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [DemPron, Inferred, Location, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | false
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@7afbfd8
## directions
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@2b378ff8
## disagree_token_match
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Disagreement, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@442c0b42
## east
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [East, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@7e13786b
## encouragement1
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Encouragement, Positive, Sentiment, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@438590e
## engineer
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Engineer, Role, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@13e75e3e
## fire_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Fire, Obstacle, EventLike, Concept]
priority | Prority | LowerBoundPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@37b5211b
## foe_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Foe, Entity, Concept]
priority | Prority | LowerBoundPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@5280c7a6
## green
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Green, Victim, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | false
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@14b614d1
## hammer
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Hammer, Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@3d09bed7
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@23aafd94
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@7743b3b0
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@53773115
## lemma_verb_dobj-craft
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Craft, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@23f8c2aa
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@57cadd1c
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@3d7e3a66
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@576b5b45
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@32633e90
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@334027fd
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@7f2186f9
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@721d5b2b
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@59a17c46
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@6becc740
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@1809928a
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@5546a3
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@524449a3
## location_clumped
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Location, EventLike, Concept]
priority | Prority | LowerBoundPriority(4)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@5f9f526c
## location_question
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [LocationQuestion, Question, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@bd606d7
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@211c2950
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@51657581
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@74cc41f3
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@4f170103
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@f81e718
## medkit
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [MedKit, Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@75325214
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@63674698
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@140f5cee
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@14b6d050
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@73f39220
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@2a794940
## north_east
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [NorthEast, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@447f98cd
## north_west
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [NorthWest, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@6669f442
## numbers
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Number, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@9e4083d
## obstructing
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Block, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@69b76308
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@51b63703
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@78541293
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@51585844
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@3a5ce44
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@634bff1c
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@3b53b96c
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@28da4c3a
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@5a0608b
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@392fcc3c
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@1f918aac
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@6550aef8
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@63f23ee1
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@24fb508f
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@37ac7a56
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@576b2535
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@259c1c0f
## precedence1
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Precedence, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@5b1d5f1b
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@fb7e0e4
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@1fb3eaa
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@44c236fe
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@75964cfa
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@4516326c
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@1d405a0a
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@48e0ea9a
## precedence3_token_before
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Precedence, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@fd69ee0
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@7ba74824
## prep_need_action
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Need, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@6428cd50
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@2b535f75
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@10c9e561
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@51ee7934
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@730e7c14
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@42173df7
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@1d2a3a39
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@3f2fd771
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@3f41f25c
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@444288c6
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@8759985
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@492053a5
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@79c38bcf
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@29172889
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@d0a259f
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@467cf2e0
## relative_location
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Location, EventLike, Concept]
priority | Prority | LowerBoundPriority(4)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@23049413
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@33c7b39a
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@6f3d4ea0
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@4e774a55
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@4954eb84
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@4701ca57
## search_infra_action
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Search, ComplexActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@424cd459
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@369d0ba7
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@58fbce3b
## self_token_capture
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Self, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@1161e401
## sight_presence
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Sight, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@352b0468
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@6b35f159
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@6d397012
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@73e6d058
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@1e838208
## south_east
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [SouthEast, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@5a7817b8
## south_west
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [SouthWest, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@7c675505
## stretcher
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Stretcher, Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@39c62ec1
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@3bd76833
## thank_you
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Gratitude, Positive, Sentiment, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@21087d26
## time
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [TimeUnit, Time, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@6781cb7f
## time_underspecified
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [TimeUnit, Time, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@6fb95168
## tool_generic
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@6faf2a5c
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@d2c8bc2
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@649f632b
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$4017/74314172@41884cc1
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@57637366
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@3e7890d3
## we_token_capture
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Team, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@21612a0c
## west
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [West, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@228c94a5
## yellow
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Yellow, Victim, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | false
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@6e383bd1
## you_token_capture
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [You, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@143cd0cd
## zone
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Zone, Infrastructure, Location, EventLike, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@3ca5a90c
## zone_conj
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Zone, Infrastructure, Location, EventLike, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$3545/1037787640@3ea9761b
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@7a4a2b81
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@780f108f

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

