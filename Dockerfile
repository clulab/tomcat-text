from mozilla/sbt
  
copy . /tomcat-text
workdir tomcat-text
run sbt webapp/compile
EXPOSE 9000
