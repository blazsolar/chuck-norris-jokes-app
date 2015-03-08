#!/bin/bash

if [ "CIRCLE_NODE_INDEX" = "0" ]
then
    ./gradlew check
elif [ "CIRCLE_NODE_INDEX" = "1" ]
then
    ./gradlew connectedCheck
fi
