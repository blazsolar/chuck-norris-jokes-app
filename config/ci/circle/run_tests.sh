#!/bin/bash

if [ $CIRCLE_NODE_INDEX -eq 0 ]
then
    echo "Check"
    ./gradlew check
elif [ $CIRCLE_NODE_INDEX = 1 ]
then
    echo "Connected check"
    ./gradlew connectedCheck
fi
