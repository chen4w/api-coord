#!/bin/sh

PWD="$( cd "$( dirname "$0"  )" && pwd  )"
cd "$PWD"
nohup java  -jar ../lib/Interface-Cooperation-Middleware-1.0-SNAPSHOT.jar >/dev/null 2>&1 &
echo $! > $PWD'/process.pid'
echo "Middleware is starting..."
