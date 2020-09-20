package com.mani.util;

import com.mani.model.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionUtilTest {

    @Test
    @DisplayName(value = " transaction Utility tester")
    @Tag("Transaction util test")
    void isAllTransactionFieldsVaild() {
        Transaction testTransaction = new Transaction();
        testTransaction.setAccountNumber("123456789012345678");
        BigDecimal db= new BigDecimal("1.0");
        testTransaction.setStartBalance(db);
        testTransaction.setEndBalance(db);
        testTransaction.setMutation("+10.00");
        assertTrue(TransactionUtil.isAllTransactionFieldsVaild(testTransaction));
    }
}