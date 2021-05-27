
 ## RULE close
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | close
 labels | String | List(Close)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE lemma_verb_dobj-move
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | lemma_verb_dobj-move
 labels | String | List(Move)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE passive-move
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | passive-move
 labels | String | List(Move)
 ruleType | String | dependency
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE prepnom-move
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | prepnom-move
 labels | String | List(Move)
 ruleType | String | dependency
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE lemma_verb_dobj-toggle
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | lemma_verb_dobj-toggle
 labels | String | List(Toggle)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE passive-toggle
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | passive-toggle
 labels | String | List(Toggle)
 ruleType | String | dependency
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE prepnom-toggle
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | prepnom-toggle
 labels | String | List(Toggle)
 ruleType | String | dependency
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE triage
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | triage
 labels | String | List(Save)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE triage2
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | triage2
 labels | String | List(Save)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | mkVictim

 ## RULE lemma_verb_dobj-sight
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | lemma_verb_dobj-sight
 labels | String | List(Sight)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE passive-sight
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | passive-sight
 labels | String | List(Sight)
 ruleType | String | dependency
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE prepnom-sight
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | prepnom-sight
 labels | String | List(Sight)
 ruleType | String | dependency
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE lemma_verb_dobj-craft
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | lemma_verb_dobj-craft
 labels | String | List(Craft)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE passive-craft
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | passive-craft
 labels | String | List(Craft)
 ruleType | String | dependency
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE prepnom-craft
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | prepnom-craft
 labels | String | List(Craft)
 ruleType | String | dependency
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE lemma_verb_dobj-defeat
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | lemma_verb_dobj-defeat
 labels | String | List(Defeat)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE passive-defeat
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | passive-defeat
 labels | String | List(Defeat)
 ruleType | String | dependency
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE prepnom-defeat
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | prepnom-defeat
 labels | String | List(Defeat)
 ruleType | String | dependency
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE lemma_verb_dobj-search
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | lemma_verb_dobj-search
 labels | String | List(Search)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE passive-search
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | passive-search
 labels | String | List(Search)
 ruleType | String | dependency
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE prepnom-search
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | prepnom-search
 labels | String | List(Search)
 ruleType | String | dependency
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE search_infra_action
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | search_infra_action
 labels | String | List(Search)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | mkVictim

 ## RULE i_am_searching
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | i_am_searching
 labels | String | List(Search)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | mkVictim

 ## RULE move_nmod_action
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | move_nmod_action
 labels | String | List(Move)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE move_deixis_action
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | move_deixis_action
 labels | String | List(Move)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE sight_presence
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | sight_presence
 labels | String | List(Sight)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE sight_presence2
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | sight_presence2
 labels | String | List(Sight)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE clear_rubble
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | clear_rubble
 labels | String | List(Clear)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE clear_rubble_token
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | clear_rubble_token
 labels | String | List(Clear)
 ruleType | String | token
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE clear_location
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | clear_location
 labels | String | List(Clear)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE obstructing
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | obstructing
 labels | String | List(Block)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher

 ## RULE obstructing_in_the_way
Key  |  Type  |  Value
-----  |  -----  |  ----
 name | String | obstructing_in_the_way
 labels | String | List(Block)
 ruleType | String | graph
 unit | String | word
 priority | String | ${rulepriority}
 keep | String | true
 action | String | removeResearcher
