package com.mani;

import com.mani.exception.TransactionDuplicateAppException;
import com.mani.processor.DuplicateFinderProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DuplicateRecordIdentifierApplication implements CommandLineRunner {

    @Autowired
    private DuplicateFinderProcessor duplicateFinderProcessor;

    public static final String FILE_PATH = "src/main/resources/test/";

    public static void main(String[] args) {
        SpringApplication.run(DuplicateRecordIdentifierApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            duplicateFinderProcessor.findDuplicateFromFile(FILE_PATH);
        } catch (TransactionDuplicateAppException e) {
            log.error(e.getMessage());
        }
    }
}
