#!/bin/sh
mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file  \
    -Dfile=/home/harry/workspace-sts-3.6.4.RELEASE/pwm/src/main/resources/lib/junidecode-0.2.jar \
    -DgroupId=gcardone -DartifactId=gcardone.junidecode \
    -Dversion=0.2 -Dpackaging=jar \
    -DlocalRepositoryPath=/home/harry/workspace-sts-3.6.4.RELEASE/pwm/local-maven-repo
