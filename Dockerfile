# clone a git repo into an sbt-based image

from sbt_image

workdir /tomcat-text

copy * /tomcat-text/

#run java -version
#run cat build.sbt


run sbt compile


entrypoint sbt "runMain org.clulab.asist.DialogAgentTest"

