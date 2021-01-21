from mozilla/sbt

copy . /tomcat-text
workdir tomcat-text
run sbt compile
entrypoint sbt "runMain org.clulab.asist.RunDialogAgent -h 172.17.0.1 -p 1883"
