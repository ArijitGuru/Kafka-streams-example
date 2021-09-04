# Kafka-streams-example
# Kafka Commands to run

# Start Kafka instance
.\bin\windows\kafka-topics.bat --list --zookeeper localhost:2181

# Create Topic 1
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic web-domains

# Create Topic 2
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic active.web-domains


# Producer Microservice endpoint URI in browser:
http://localhost:8081/domain/lookup/facebook
