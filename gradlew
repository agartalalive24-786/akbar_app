#!/bin/sh

#
# Copyright 2015 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

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
SAVED="$(pwd)"
cd "$(dirname "$PRG")/.." >/dev/null
APP_HOME="$(pwd -P)"
cd "$SAVED" >/dev/null

MODULE_OPTS=""

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

# Collect all arguments for the java command;
#   * $DEFAULT_JVM_OPTS, $JAVA_OPTS, and $GRADLE_OPTS can contain fragments of shell commands, so put them in
#     double quotes to make sure that they get re-expanded; and
#   * put everything else in single quotes, so that it's not re-expanded.

set -- \
        "-Dorg.gradle.appname=$APP_BASE_NAME" \
        -classpath "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" \
        org.gradle.wrapper.GradleWrapperMain \
        "$@"

# Stop when "xargs" is not available.
if ! command -v xargs >/dev/null 2>&1
then
    die "xargs not found - unable to start Gradle"
fi

# Use "xargs" to parse quoted args.
#
# With -n1 it outputs one arg per line, when -0 it outputs one arg per NUL character.
# This is to circumvent both the shell and xargs parsing of both arguments.
#
eval "set -- $(printf '%s\n' "$DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS" | xargs printf '%s\0')" \
    "-Dorg.gradle.appname=$APP_BASE_NAME" \
    -classpath "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" \
    org.gradle.wrapper.GradleWrapperMain \
    "$@"

# by default we should be in the correct project dir, but when run from Gradle we
# might be in the subdir of the actual project dir. Let's try parent dir in case it is a subdir
if [ ! -d "$APP_HOME" ]; then
    APP_HOME="$( cd "${APP_HOME%/*}" && pwd -P )"
fi

APP_HOME=$( cd "$APP_HOME" && pwd -P ) || exit

# Escaping for Windows has to be applied here.
#
# If there are spaces in the current directory, Windows will fail when
# applying the needed escaping before invoking java.

# Collect all arguments for the java command.
#  * Variables with large strings and many parameters get in trouble
#    with lots of other commands, so let Gradle's build script do the heavy
#    lifting by putting everything in an array and passing it to Java.

set +e
IFS=$'\n'
for line in $(cat "$APP_HOME"/gradle/wrapper/gradle-wrapper.properties); do
    case "$line" in *=*) eval "export $(echo $line | sed 's/ /\\ /g')" ;; esac
done
set -e
IFS=$' \t\n'

# Determine the Java command to use in order to perform the gradle invocation.

if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD="java"
    if ! command -v java >/dev/null 2>&1
    then
        die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
fi

# Increase the maximum file descriptors if we can.
if ! "$cygwin" ; then
    MAX_FD_LIMIT=$(ulimit -H -n)
    if [ $? -eq 0 ] ; then
        if [ "$MAX_FD_LIMIT" != 'unlimited' ] ; then
            ulimit -n $MAX_FD_LIMIT
        fi
    fi
fi

# For Darwin, add options to specify how the application appears in the dock
if $darwin; then
    DEFAULT_JVM_OPTS="$DEFAULT_JVM_OPTS \"-XX:+UseStringDeduplication\""
fi

# Append GRADLE_OPTS to JAVA_OPTS
# JAVA_OPTS="$JAVA_OPTS $GRADLE_OPTS"

exec "$JAVACMD" $DEFAULT_JVM_OPTS -classpath "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
