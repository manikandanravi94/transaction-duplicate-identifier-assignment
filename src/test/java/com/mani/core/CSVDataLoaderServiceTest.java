package com.mani.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CSVDataLoaderServiceTest {

    @Autowired
    private CSVDataLoaderService csvDataLoaderService;

    @Test
    @DisplayName("csv file loader test")
    void loadDataFromFileTest() {
        File file = new File("src/test/resource/records.csv");
        assertEquals(10, csvDataLoaderService.loadDataFromFile(file).size());
    }
}