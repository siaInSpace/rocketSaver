#!/bin/bash
rm -rf ./data
git pull
java -jar out/jars/rocketSaver.jar -cp lib/*