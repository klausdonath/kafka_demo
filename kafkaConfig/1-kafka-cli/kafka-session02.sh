# Anlegen eines zweiten Brokers
# - Logverzeichnis für den zweiten Broker anlegen -> \data\kafka02
# - Kopie von server.properties anlegen -> server02.properties
# - Propertie-Datei editieren:
# -- broker.id=1 -> muss eindeutig sein
# -- listeners=PLAINTEXT://:9093 -> Default ist listeners=PLAINTEXT://:9092, falls nichts angegeben ist
# -- log.dirs=\data\kafka02
#
# Dann Broker starten:
kafka-server-start.bat config\server02.properties

# Anlegen eines Topics
kafka-topics.bat --bootstrap-server 127.0.0.1:9093 --topic some_topic --create --partitions 3 --replication-factor 2

# Producing von Messages innerhalb eines Topics mit Keys
# Hinweis: IP des Brokers ist egal. Auch wenn das Topic auf dem zweiten Broker anlegt wurde, kann es über den ersten Broker abgefragt werden.
kafka-console-producer.bat --broker-list 127.0.0.1:9092 --topic some_topic --property "parse.key=true" --property "key.separator=:"
>1:{"content": "Next try"}
>2:{"content": "Same here"}
>1:{"content": "Updatttiy"}
>1:{"content": "Und noch eines"}
>A:{"content": "Mal sehen, wo es landet"}
>A:{"content": "Wieder in die selbe Partition"}

# Consuming von Messages - Broker IP ist wieder egal
# Live - alle Partitionen
kafka-console-consumer.bat --bootstrap-server 127.0.0.1:9093 --topic some_topic
# Live - nur Partition 0
kafka-console-consumer.bat --bootstrap-server 127.0.0.1:9093 --topic some_topic --partition 0
# Vom Beginn an
kafka-console-consumer.bat --bootstrap-server 127.0.0.1:9093 --topic some_topic --from-beginning
# Vom Beginn an - nur Partition 0
kafka-console-consumer.bat --bootstrap-server 127.0.0.1:9093 --topic some_topic --from-beginning --partition 0
