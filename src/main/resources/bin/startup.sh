#!/bin/bash

current_path=$(pwd)
case "$(uname)" in
Linux)
  bin_abs_path=$(readlink -f $(dirname $0))
  ;;
*)
  bin_abs_path=$(
    cd $(dirname $0)
    pwd
  )
  ;;
esac
base=${bin_abs_path}/..
export LANG=en_US.UTF-8
export BASE=$base

if [ -f $base/bin/process.pid ]; then
  echo "found process.pid , Please run stop.sh first ,then startup.sh" 2>&2
  exit 1
fi

### set java path
if [ -z "$JAVA" ]; then
  JAVA=$(which java)
  if [ -z "$JAVA" ]; then
    echo "Cannot find a Java JDK. Please set either set JAVA or put java (>=1.8) in your PATH." 2>&2
    exit 1
  fi
fi


str=`file -L $JAVA | grep 64-bit`
if [ -n "$str" ]; then
	JAVA_OPTS="-server -Xms2048m -Xmx3072m -Xmn1024m -XX:SurvivorRatio=2 -XX:PermSize=96m -XX:MaxPermSize=256m -Xss256k -XX:-UseAdaptiveSizePolicy -XX:MaxTenuringThreshold=15 -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:+HeapDumpOnOutOfMemoryError"
else
	JAVA_OPTS="-server -Xms1024m -Xmx1024m -XX:NewSize=256m -XX:MaxNewSize=256m -XX:MaxPermSize=128m "
fi


JAVA_OPTS=" $JAVA_OPTS -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dfile.encoding=UTF-8"
ADAPTER_OPTS="-DappName=Interface-Cooperation-Middleware"

#echo "cd to $bin_abs_path for workaround relative path"
cd $bin_abs_path
echo "Interface-Cooperation-Middleware is starting..."
$JAVA $JAVA_OPTS  $ADAPTER_OPTS -jar ../lib/Interface-Cooperation-Middleware-1.0-SNAPSHOT.jar >/dev/null 2>&1 &
echo $! > $base/bin/process.pid

#echo "cd to $current_path for continue"
cd $current_path
