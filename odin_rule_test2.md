
# PREAMBLE
# grammars/actions.yml rules:
## clear_location:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | clear_location
labels | Seq[String] | "Clear"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## clear_rubble:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | clear_rubble
labels | Seq[String] | "Clear"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## clear_rubble_token:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | clear_rubble_token
labels | Seq[String] | "Clear"
ruleType | String | token
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## close:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | close
labels | Seq[String] | "Close"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## i_am_searching:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | i_am_searching
labels | Seq[String] | "Search"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | mkVictim
## lemma_verb_dobj-craft:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | lemma_verb_dobj-craft
labels | Seq[String] | "Craft"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## lemma_verb_dobj-defeat:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | lemma_verb_dobj-defeat
labels | Seq[String] | "Defeat"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## lemma_verb_dobj-move:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | lemma_verb_dobj-move
labels | Seq[String] | "Move"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## lemma_verb_dobj-search:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | lemma_verb_dobj-search
labels | Seq[String] | "Search"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## lemma_verb_dobj-sight:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | lemma_verb_dobj-sight
labels | Seq[String] | "Sight"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## lemma_verb_dobj-toggle:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | lemma_verb_dobj-toggle
labels | Seq[String] | "Toggle"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## move_deixis_action:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | move_deixis_action
labels | Seq[String] | "Move"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## move_nmod_action:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | move_nmod_action
labels | Seq[String] | "Move"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## obstructing:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | obstructing
labels | Seq[String] | "Block"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## obstructing_in_the_way:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | obstructing_in_the_way
labels | Seq[String] | "Block"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## passive-craft:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | passive-craft
labels | Seq[String] | "Craft"
ruleType | String | dependency
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## passive-defeat:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | passive-defeat
labels | Seq[String] | "Defeat"
ruleType | String | dependency
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## passive-move:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | passive-move
labels | Seq[String] | "Move"
ruleType | String | dependency
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## passive-search:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | passive-search
labels | Seq[String] | "Search"
ruleType | String | dependency
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## passive-sight:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | passive-sight
labels | Seq[String] | "Sight"
ruleType | String | dependency
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## passive-toggle:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | passive-toggle
labels | Seq[String] | "Toggle"
ruleType | String | dependency
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## prepnom-craft:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | prepnom-craft
labels | Seq[String] | "Craft"
ruleType | String | dependency
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## prepnom-defeat:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | prepnom-defeat
labels | Seq[String] | "Defeat"
ruleType | String | dependency
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## prepnom-move:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | prepnom-move
labels | Seq[String] | "Move"
ruleType | String | dependency
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## prepnom-search:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | prepnom-search
labels | Seq[String] | "Search"
ruleType | String | dependency
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## prepnom-sight:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | prepnom-sight
labels | Seq[String] | "Sight"
ruleType | String | dependency
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## prepnom-toggle:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | prepnom-toggle
labels | Seq[String] | "Toggle"
ruleType | String | dependency
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## search_infra_action:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | search_infra_action
labels | Seq[String] | "Search"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | mkVictim
## sight_presence:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | sight_presence
labels | Seq[String] | "Sight"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## sight_presence2:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | sight_presence2
labels | Seq[String] | "Sight"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## triage:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | triage
labels | Seq[String] | "Save"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | removeResearcher
## triage2:
Key  |  Type  |  Value
-----  |  -----  |  ----
name | String | triage2
labels | Seq[String] | "Save"
ruleType | String | graph
unit | String | word
priority | String | ${rulepriority}
keep | String | true
action | String | mkVictim
