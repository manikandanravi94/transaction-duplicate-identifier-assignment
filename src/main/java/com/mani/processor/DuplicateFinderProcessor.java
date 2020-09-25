package com.mani.processor;

import com.mani.core.DataLoaderInstanceFactory;
import com.mani.core.DataLoaderService;
import com.mani.exception.TransactionDuplicateAppException;
import com.mani.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DuplicateFinderProcessor {


    @Autowired
    private DataLoaderInstanceFactory dataLoaderInstanceFactory;

    /**
     * this method gets the file path and logs the valid and invalid transactions
     *
     * @param filePath
     * @throws TransactionDuplicateAppException
     */
    public void findDuplicateFromFile(String filePath) throws TransactionDuplicateAppException {
        log.debug("Entered find Duplicate method");
        File folder = new File(filePath);
        File[] files = folder.listFiles();
        if (files == null) {
            throw new TransactionDuplicateAppException("Specified folder doesnot contain any file");
        }
        if(files.length > 1){
            throw new TransactionDuplicateAppException("Specified folder contains more files than the specified count " + files.length);
        }
        String fileName = files[0].getName();
        DataLoaderService dataLoaderService = dataLoaderInstanceFactory.getInstanceByFileType(fileName.substring(fileName.length() - 3));
        List<Transaction> completeTransactionList = dataLoaderService.loadDataFromFile(files[0]);
        if (completeTransactionList.isEmpty()) {
            throw new TransactionDuplicateAppException("Nothing to process in the given file");
        }
        Map<String, Collection<Transaction>> segregatedTransactions = dataLoaderService.segregateDuplicateTransactions(completeTransactionList);
        log.debug("Duplicate is segregated from the valid transaction :" + segregatedTransactions.toString());
    }


}
