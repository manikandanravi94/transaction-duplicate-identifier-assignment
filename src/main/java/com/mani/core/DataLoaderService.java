package com.mani.core;

import com.mani.exception.TransactionDuplicateAppException;
import com.mani.model.Transaction;
import com.mani.util.TransactionUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
public abstract class DataLoaderService {

    public static final String VALID_TRANSACTIONS = "ValidTransactionList";
    public static final String IN_VALID_TRANSACTIONS = "InValidTransactionList";

    public abstract List<Transaction> loadDataFromFile(File file) throws TransactionDuplicateAppException;

    public Map<String, Collection<Transaction>> segregateDuplicateTransactions(List<Transaction> completeTransactionList) {
        log.info("Entered segregateDuplicateTransactions method with following transaction list:" + completeTransactionList.toString());
        List<Transaction> inValidTransaction = new ArrayList<>();
        Set<Transaction> transactionSet = new TreeSet<>((o1, o2) -> {
            if (o1.getReference().equals(o2.getReference())) {
                return 0;
            }
            return 1;
        });
        Map<String, Collection<Transaction>> segregatedTransactionMap = new HashMap<>();
        if (!completeTransactionList.isEmpty()) {
            for (Transaction transaction : completeTransactionList) {
                if (transactionSet.contains(transaction) || !validateTransactionAmount(transaction)) {
                    inValidTransaction.add(transaction);
                } else {
                    transactionSet.add(transaction);
                }
            }
        }
        segregatedTransactionMap.put(VALID_TRANSACTIONS, transactionSet);
        segregatedTransactionMap.put(IN_VALID_TRANSACTIONS, inValidTransaction);
        log.info("exit from segregateDuplicateTransactions method");
        return segregatedTransactionMap;
    }

    private static boolean validateTransactionAmount(Transaction transaction) {
        boolean flag = false;
        try {
            if (transaction.getMutation().startsWith("+")) {
                flag = transaction.getStartBalance().add(new BigDecimal(transaction.getMutation().substring(1))).compareTo(transaction.getEndBalance()) == 0;
            } else if (transaction.getMutation().startsWith("-")) {
                flag = transaction.getStartBalance().subtract(new BigDecimal(transaction.getMutation().substring(1))).compareTo(transaction.getEndBalance()) == 0;
            }
        } catch (Exception e) {
            log.error("Conversion exception happened", e);
        }
        return flag;
    }

    /**
     * java 8 implementaion can be done in the following way
     * //return transactionList.stream().filter(TransactionUtil::predicateValidation).collect(Collectors.toList());
     *
     * @param transactionList
     * @return
     */
    protected static List<Transaction> validatedTransactions(List<Transaction> transactionList) {
        int lineNo = 1;
        List<Transaction> transactions = new ArrayList<>(transactionList);
        for (Transaction transaction : transactionList) {
            lineNo++;
            if (!TransactionUtil.isAllTransactionFieldsVaild(transaction)) {
                transactions.remove(transaction);
                log.error("Errored inputs line number in file :" + lineNo);
            }
        }
        return transactions;
    }
}
