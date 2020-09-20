package com.mani.processor;

import com.mani.core.DataLoaderInstanceFactory;
import com.mani.core.DataLoaderService;
import com.mani.exception.TransactionDuplicateAppException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DuplicateFinderProcessorTest {

    @Autowired
    private DuplicateFinderProcessor duplicateFinderProcessor;

    private DataLoaderInstanceFactory dataLoaderInstanceFactory;

    private DataLoaderService dataLoaderService;


    @Test()
    @DisplayName("Exception thrown scenario test")
    void findDuplicateFromFileTest1() throws TransactionDuplicateAppException {
        String filePath = "src/test";
        File file = new File(filePath);
        assertThrows(TransactionDuplicateAppException.class, () -> duplicateFinderProcessor.findDuplicateFromFile(filePath));
    }
}