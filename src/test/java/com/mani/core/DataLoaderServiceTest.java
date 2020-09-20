package com.mani.core;

import com.mani.exception.TransactionDuplicateAppException;
import com.mani.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataLoaderServiceTest {

    public static List<Transaction> testTransactions = Arrays.asList(
            new Transaction(12345L, "123456789012345678", "test 1", BigDecimal.valueOf(10.00), "+1.00", BigDecimal.valueOf(11.00)),
            new Transaction(12345L, "123456789012345678", "test 2", BigDecimal.valueOf(10.00), "-1.00", BigDecimal.valueOf(11.00))

    );

    @BeforeEach
    public void getDataLoaderInstance() {
        this.dataLoaderService = Mockito.mock(DataLoaderService.class, Mockito.CALLS_REAL_METHODS);
    }


    private DataLoaderService dataLoaderService;

    @Test
    @DisplayName("segregateDuplicateTransactionTest")
    void segregateDuplicateTransactionsTest() {
        Map<String, Collection<Transaction>> expectedMap = new HashMap<>();
        Set<Transaction> testTransactionSet = new HashSet<>();
        testTransactionSet.add(new Transaction(12345L, "123456789012345678", "test 2", BigDecimal.valueOf(10.00), "-1.00", BigDecimal.valueOf(11.00)));
        expectedMap.put("ValidTransactionList", Collections.singletonList(new Transaction(12345L, "123456789012345678", "test 1", BigDecimal.valueOf(10.00), "+1.00", BigDecimal.valueOf(11.00))));
        expectedMap.put("InValidTransactionList", testTransactionSet);
        assertEquals(dataLoaderService.segregateDuplicateTransactions(testTransactions).toString(), expectedMap.toString());
    }

    @Test
    @DisplayName("load data from file test")
    void loadDataFromFileTest() {
        File file = new File("src/test");
        DataLoaderService dataLoaderService = Mockito.mock(DataLoaderService.class);
        try {
            Mockito.when(dataLoaderService.loadDataFromFile(file)).thenReturn(testTransactions);
            assertEquals(testTransactions, dataLoaderService.loadDataFromFile(file));
        } catch (TransactionDuplicateAppException e) {
            e.printStackTrace();
        }
    }
}