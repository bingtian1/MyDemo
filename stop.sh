#!/bin/bash
pid=`netstat -tlunp | grep 9999`
if [ "$pid" != "" ];then
kill -9 `netstat -tlunp | grep 9999 | awk '{print $7}' | awk -F "/" '{print $1}'`
fi
