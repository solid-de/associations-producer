bin/kafka-server-start.sh config/kraft/reconfig-server.properties

bin/kafka-topics.sh --create --topic associations.raw --bootstrap-server localhost:9092 --partitions 3

bin/kafka-topics.sh --describe --topic associations.raw --bootstrap-server localhost:9092

bin/kafka-topics.sh --bootstrap-server localhost:9092 --list

bin/kafka-console-consumer.sh --topic associations.raw  --from-beginning --bootstrap-server localhost:9092