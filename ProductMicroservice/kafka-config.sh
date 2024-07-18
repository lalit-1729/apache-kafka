#!/bin/bash

cd ~
cd /Users/thakre/kafka

echo "Current working directory is ${pwd}"

echo "Generating random-uuid"
randomUUID = $(./bin/kafka-storage.sh)


./bin/kafka-storage.sh format -t ${randomUUID} -c config/kraft/server1.properties
./bin/kafka-storage.sh format -t ${randomUUID} -c config/kraft/server2.properties
./bin/kafka-storage.sh format -t ${randomUUID} -c config/kraft/server3.properties
