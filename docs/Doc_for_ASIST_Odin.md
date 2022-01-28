# Documentation for the ASIST TomcatRuleEngine (Odin)

The ASIST TomcatRuleEngine is based on the Odin framework. For documentation of Odin, please refer to the [Processors page](https://clulab.github.io/processors/odin.html).
This file describes the locations and contents of files necessary for the ASIST Odin. It describes the locations of relevant files and their specific contributions to the Engine.

### Rule files
**path:** src/main/resources/org/clulab/asist/grammars <br>
**contents:**
These files contain all ASIST Odin rules, the master.yml file and the taxonomy.yml file

### Config file
**path:** src/main/resources/org/clulab/asist<br>
**contents:**
Contains configurations for Odin(TomcatRuleEngine) and other.

### Test files
**path:** src/test/scala/org/clulab/asist/rules<br>
**contents:**
These are the unit tests for Odin rules. The files are sorted by rule association. You can run the tests with "sbt test". These tests are run every time we push to github.

### Attachment definitions
**path:** src/main/scala/org/clulab/asist/attachments<br>
**contents:**
These files contain the definitions of our attachments. They do not contain the conditions for when attachments apply.

### Actions (conditions for attachments)
**path:** src/main/scala/org/clulab/asist/extraction/TomcatActions.scala<br>
**contents:**
This file contains the Actions. Actions are essentially custom methods we execute while Odin is running. These include the conditions for when we export attachments.

### TomcatRuleEngine (Odin main file)
**path:** src/main/scala/org/clulab/asist/extraction/TomcatRuleEngine.scala<br>
**contents:**
This is where the definition of the TomcatRuleEngine (ASIST Odin) is housed.
