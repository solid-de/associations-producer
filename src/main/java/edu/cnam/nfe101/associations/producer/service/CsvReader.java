package edu.cnam.nfe101.associations.producer.service;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;

import edu.cnam.nfe101.associations.producer.model.AssociationCsv;

@Service
public class CsvReader {

    private static final Logger log = LoggerFactory.getLogger(CsvReader.class);
    
    private final String dataFolder;

    public CsvReader(@Value("${associations.data-folder}")String dataFolder) {
        this.dataFolder = dataFolder;
    }

    public List<AssociationCsv> readAssociations(String fileName) {
        String filePath = dataFolder + File.separator + fileName;
        log.debug("Parsing file: {}", filePath);
        try {
            return new CsvToBeanBuilder<AssociationCsv>(new FileReader(filePath))
                    .withType(AssociationCsv.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            log.error("Could not parse file: {}", filePath, e);
            throw new RuntimeException("Could not parse Csv file: " + fileName + ". Cause by: " + e, e);
        }
    }
}
