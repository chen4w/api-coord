#!/bin/sh

APP_MAIN=Middleware
tradePortalPID=0
PWD="$( cd "$( dirname "$0"  )" && pwd  )"
PIDPATH=$PWD'/process.pid'
param=`cat $PWD'/process.pid'`
getTradeProtalPID(){
 if [  ! -n "$param" ]; then
  tradePortalPID=0
else
  tradePortalPID="$param"
fi
}

shutdown(){
    getTradeProtalPID
    if [ $tradePortalPID -ne 0 ]; then
        echo "Stopping $APP_MAIN(PID=$tradePortalPID)..."
        kill "$tradePortalPID"
        if [ $? -eq 0 ]; then
            echo "[shutdown Success]"
            rm "$PIDPATH"
        else
            echo "[shutdown Failed]"
        fi
    else
        echo "$APP_MAIN is not running"
    fi
}

shutdown
