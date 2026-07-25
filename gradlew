#!/usr/bin/env bash

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
# Resolve links: $0 may be a symlink
PRG="$0"
# Need this for relative symlinks.
while [ -h "$PRG" ] ; do
    ls -ld "$PRG"
    link=$(expr "$PRG" : '.*-> \(.*\)$')
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=$(dirname "$PRG")"/"$link"
    fi
done
SAVEPWD=$(pwd)
cd "$(dirname \"$PRG\")/.." >/dev/null
APP_HOME=$(pwd -P)
cd "$SAVEPWD" >/dev/null

APP_HOME=$(expr "$APP_HOME" : '\(.*\)/gradle$' \| "$APP_HOME")
export APP_HOME
exec java -Xmx2048m -XX:MaxPermSize=512m -classpath "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"