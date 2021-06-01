# ODIN Extraction Schemas

## agree_token_match
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Agreement, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@3313334a
## agreement2
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Agreement, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@663de6d6
## alpha
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Alpha, Player, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@41f2329f
## bravo
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Bravo, Player, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@7ea8aeec
## change_priority
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [ChangePriority, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@548b5b10
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@5d890c01
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@38062560
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@2dfacf65
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@5dea29bc
## clear_location
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Clear, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@360189ca
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@3f25974f
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@445fe629
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@48f480d7
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@72011fda
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@610d713a
## close
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Close, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@5ad8bd9c
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@36c66c28
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@4cc35ae5
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@464d66e6
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@596617ee
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@5cc487c6
## delta
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Delta, Player, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@a06fac9
## demonstrative_pronoun
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [DemPron, Inferred, Location, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | false
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@27d1d739
## directions
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@4ee17d4a
## disagree_token_match
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Disagreement, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@36e339e7
## east
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [East, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@b0e4d40
## encouragement1
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Encouragement, Positive, Sentiment, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@224f2b07
## engineer
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Engineer, Role, Entity, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@67011929
## fire_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Fire, Obstacle, EventLike, Concept]
priority | Prority | LowerBoundPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@4882282f
## foe_detection
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Foe, Entity, Concept]
priority | Prority | LowerBoundPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@311f0832
## green
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Green, Victim, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | false
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@6ad31a05
## hammer
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Hammer, Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@5f48e27
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@5c6e0de6
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@1579d0c1
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@6788f359
## lemma_verb_dobj-craft
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Craft, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@462ccc39
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@6a81f827
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@3125c26e
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@1117fd28
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@317aa637
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@54df30ff
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@4b76beb8
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@65a6e443
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@189adde
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@2c7c2dd8
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@5281d591
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@d7a77e
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@4cfc0de7
## location_clumped
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Location, EventLike, Concept]
priority | Prority | LowerBoundPriority(4)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@123fb4d
## location_question
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [LocationQuestion, Question, Communicate, SimpleActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@5d529ba5
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@60833b26
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@4cbc81e4
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@52dbe2ad
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@1b1dc4e0
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@62be86ae
## medkit
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [MedKit, Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@4aa598bc
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@4766c5cf
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@32292307
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@2434481a
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@42b25458
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@330bb97c
## north_east
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [NorthEast, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@48c19501
## north_west
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [NorthWest, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@4e33609b
## numbers
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Number, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@1e73ed91
## obstructing
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Block, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@70dd777a
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@34708dfd
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@61921450
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@600c6296
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@7bf1bf4a
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@4bdecaac
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@3a1f6713
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@3984ba14
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@4f795981
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@724f6168
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@3329b7cd
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@2f61ffa6
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@2ad11e9a
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@239c7e89
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@5d7532e8
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@7dc96a2f
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@5f4e8ec2
## precedence1
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Precedence, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@1fe6cb94
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@6bf058f2
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@5d5ecfb1
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@76cd7246
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@5f5ead60
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@360059b1
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@1d083b87
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@4a2522
## precedence3_token_before
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Precedence, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(7)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@2ad5bde6
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@1e2cb4dc
## prep_need_action
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Need, ComplexActions, Action, EventLike, Concept]
priority | Prority | LowerBoundPriority(3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@42a4709d
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@13963ede
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@14ab9bc7
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@6fda57da
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@e1be224
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@2c595df7
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@64e49e18
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@593d71d9
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@866a218
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@6eb6e006
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@2ee2b796
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@1c63a82a
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@21a8afdc
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@7847cd3f
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@43a29ba6
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@42015900
## relative_location
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Location, EventLike, Concept]
priority | Prority | LowerBoundPriority(4)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@26b3c8d3
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@60dbd70f
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@37730f19
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@7ff289ae
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@47bf33d3
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@7962f784
## search_infra_action
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Search, ComplexActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@56462476
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@3d9278e9
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@30de523
## self_token_capture
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Self, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@6d3dec11
## sight_presence
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Sight, SimpleActions, Action, EventLike, Concept]
priority | Prority | ExactPriority(5)
keep | Boolean | true
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@abf0054
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@24c464da
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@488cb99c
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@4ad389f8
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@389c9d56
## south_east
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [SouthEast, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@3f79dc2b
## south_west
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [SouthWest, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@1214d201
## stretcher
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Stretcher, Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@445e15f2
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@18744602
## thank_you
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Gratitude, Positive, Sentiment, Concept]
priority | Prority | ExactPriority(2)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@699d59a3
## time
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [TimeUnit, Time, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@5d2d0a4b
## time_underspecified
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [TimeUnit, Time, EventLike, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@69687599
## tool_generic
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Tool, Item, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@53967ae0
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@50456170
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@650f5661
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.impl.ActionMirror$$Lambda$5623/1673961174@4f6133e4
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@2fc00192
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@5475bbfb
## we_token_capture
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Team, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@4b56534a
## west
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [West, Cardinal, Direction, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@deea7d7
## yellow
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Yellow, Victim, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | false
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@228deb69
## you_token_capture
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [You, Entity, Concept]
priority | Prority | ExactPriority(1)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@5bde26a0
## zone
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | TokenExtractor
labels | Seq[String] | [Zone, Infrastructure, Location, EventLike, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | TokenPattern | org.clulab.odin.impl.TokenPattern@226bf292
## zone_conj
Attribute  |  Type  |  Value
-----  |  -----  |  ----
type | Extractor | GraphExtractor
labels | Seq[String] | [Zone, Infrastructure, Location, EventLike, Concept]
priority | Prority | IntervalPriority(2,3)
keep | Boolean | true
action | Action | org.clulab.odin.Actions$$Lambda$5151/672981529@5b6c913c
pattern | GraphPattern | org.clulab.odin.impl.TriggerPatternGraphPattern@5a432917
taxonomy | Option[Taxonomy] | Some
graph | String | universal-enhanced
resources | OdinResourceManager | org.clulab.odin.impl.OdinResourceManager@d3fabe5

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

