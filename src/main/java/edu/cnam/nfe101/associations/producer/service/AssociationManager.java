package edu.cnam.nfe101.associations.producer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.cnam.nfe101.associations.producer.model.AssociationCsv;

@Service
public class AssociationManager {

    private static final Logger log = LoggerFactory.getLogger(AssociationManager.class);

    private final CsvReader csvReader;
    private final KafkaSender kafkaSender;
    
    public AssociationManager(CsvReader csvReader,
                                KafkaSender kafkaSender) {
        this.csvReader = csvReader;
        this.kafkaSender = kafkaSender;
    }

    public void transfer(String csvFileName) {
        log.info("Transferring data from CSV file: {} to Kafka", csvFileName);

        List<AssociationCsv> csvAssociations = csvReader.readAssociations(csvFileName);
        log.info("Sending csv associations to kafka");
        
        csvAssociations
        .stream()
        .map(csv -> AssociationMapper.toJson(csv))
        .forEach(association -> {
            kafkaSender.sendAssociation(association);
        });
        log.info("Finished transferring file: {}", csvFileName);
    }
}
