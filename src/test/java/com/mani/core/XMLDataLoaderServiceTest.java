package com.mani.core;

import com.mani.exception.TransactionDuplicateAppException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class XMLDataLoaderServiceTest {

    @Autowired
    private XMLDataLoaderService xmlDataLoaderService;

    @Test
    @DisplayName("xml file loader test")
    void loadDataFromFileTest() throws TransactionDuplicateAppException {
        File file = new File("src/test/resource/xml/records.xml");
        assertEquals(10, xmlDataLoaderService.loadDataFromFile(file).size());
    }
}