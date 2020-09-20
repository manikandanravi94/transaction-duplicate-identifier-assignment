package com.mani.core;

import com.mani.exception.TransactionDuplicateAppException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataLoaderInstanceFactoryTest {

    @BeforeEach
    public void instantiateBeans() {
        this.csvDataLoaderService = Mockito.mock(CSVDataLoaderService.class);
        this.xmlDataLoaderService = Mockito.mock(XMLDataLoaderService.class);
    }

    private XMLDataLoaderService xmlDataLoaderService;

    @Autowired
    private DataLoaderInstanceFactory dataLoaderInstanceFactory;

    private CSVDataLoaderService csvDataLoaderService;

    @Test
    @DisplayName("getInstanceTest")
    void getInstanceByFileTypeTest() throws TransactionDuplicateAppException {
        assertAll(() -> assertTrue(dataLoaderInstanceFactory.getInstanceByFileType("csv") instanceof CSVDataLoaderService),
                () -> assertTrue(dataLoaderInstanceFactory.getInstanceByFileType("xml") instanceof XMLDataLoaderService));
    }

    @Test
    @DisplayName("Exception check for different input file")
    void exceptionCheckTest() throws TransactionDuplicateAppException {
        assertThrows(TransactionDuplicateAppException.class, () -> dataLoaderInstanceFactory.getInstanceByFileType("mkn"));
    }
}