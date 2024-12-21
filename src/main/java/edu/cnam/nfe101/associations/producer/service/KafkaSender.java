package edu.cnam.nfe101.associations.producer.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.cnam.nfe101.associations.producer.model.AssociationJson;

@Service
public class KafkaSender {
    
    private static final Logger log = LoggerFactory.getLogger(KafkaSender.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final String associationsTopicName;
    private final Boolean syncSending;
    private final KafkaProducer<String, String> kafkaProducer;


    public KafkaSender(@Value("${associations.kafka-server}") String kafkaServers, 
                @Value("${associations.raw-kafka-topic}") String associationsTopicName,
                @Value("${associations.sync-sending:false}") Boolean syncSending) {
        this.associationsTopicName = associationsTopicName;
        this.syncSending = syncSending;

        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        
        this.kafkaProducer = new KafkaProducer<>(properties);
    }


    public void sendAssociation(AssociationJson association) {
        
        String json;
        try {
            log.debug("Serializing java object to json");
            json = objectMapper.writeValueAsString(association);
            log.trace("Resultant Json: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Could not serialize association object {} to json", association, e);
            throw new RuntimeException("Error serializing json from object: " + association + ". Cause: " + e, e);
        }
        String key = "raw-association-" + association.getExternalId();
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(associationsTopicName, key, json);
        try {
            
            if(syncSending) {
                Future<RecordMetadata> sendingRecord = kafkaProducer.send(producerRecord);
                RecordMetadata sentRecord = sendingRecord.get();
                log.debug("Json was sent successfully to kafka topic: {}", sentRecord);
            } else {
                kafkaProducer.send(producerRecord, (sentRecord, error) -> {
                    if(error != null) {
                        log.error("Could not send json to kafka topic: {}", associationsTopicName, error);
                    } else {
                        log.debug("Json was sent successfully to kafka topic: {}", sentRecord);
                    }
                });
            }
            
        } catch (Exception e) {
            log.error("Could not send json to kafka topic: {}", associationsTopicName, e);
            throw new RuntimeException("Could not send json to kafka topic: " + associationsTopicName + ". Cause: " + e, e);
        } 

    }
}
