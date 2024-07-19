#!/bin/bash

cd ~
cd /Users/thakre/kafka

echo "Current working directory is $(pwd)"

echo "Generating random-uuid"
random_uuid=$(./bin/kafka-storage.sh random-uuid)

echo "The random UUID is ${random_uuid}"

./bin/kafka-storage.sh format -t ${random_uuid} -c config/kraft/server1.properties
./bin/kafka-storage.sh format -t ${random_uuid} -c config/kraft/server2.properties
./bin/kafka-storage.sh format -t ${random_uuid} -c config/kraft/server3.properties

#//./bin/kafka-server-start.sh config/kraft/server2.properties
#//./bin/kafka-server-start.sh config/kraft/server3.properties
#//./bin/kafka-server-start.sh config/kraft/server1.properties