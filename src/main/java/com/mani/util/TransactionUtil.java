package com.mani.util;

import com.mani.model.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TransactionUtil {


    private static final List<Predicate<Transaction>> predicateList = Arrays.asList(
            transaction -> transaction.getAccountNumber() != null && transaction.getAccountNumber().length() == 18,
            transaction -> transaction.getStartBalance() != null || transaction.getEndBalance() != null,
            transaction -> transaction.getMutation() != null && (transaction.getMutation().startsWith("+") || transaction.getMutation().startsWith("-"))
    );

    public static boolean isAllTransactionFieldsVaild(Transaction transaction) {
        for (Predicate<Transaction> p : predicateList) {
            if (!p.test(transaction)) {
                return false;
            }
        }
        return true;
    }
}
