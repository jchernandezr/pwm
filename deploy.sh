#!/bin/sh -x
NOCHECKSTYLE=-Dcheckstyle.skip

SELF=`readlink -f $0`
BASEDIR=`dirname $SELF`
BASEDIR=`dirname $BASEDIR`
#export JAVA_HOME=/usr 
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/
export PATH=/usr/lib/jvm/java-8-openjdk-amd64/jre/bin/:$PATH
#delete eclipse css/jpg/images
mvn -DskipTests -Dmaven.javadoc.disable=1 clean install

if [ -n "$1" ]; then
	#scp -P 38556 target/pwm-1.8.0-SNAPSHOT.war root@shi-idp-test1.iubh.de://usr/share/tomcat/webapps/pwm.war
	scp -P 38556 target/pwm-1.8.0-SNAPSHOT.war root@shi-idp-test1.iubh.de:/usr/share/apache-tomcat/webapps/pwm.war
fi
