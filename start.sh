#!/bin/bash
pid=`netstat -tlunp | grep 9999`
if [ "$pid" != "" ];then 
kill -9 `netstat -tlunp | grep 9999 | awk '{print $7}' | awk -F "/" '{print $1}'`
fi 
java -jar ./target/demo-0.0.1-SNAPSHOT.jar >> demo.log 2>&1 &
