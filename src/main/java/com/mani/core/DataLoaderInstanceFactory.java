package com.mani.core;

import com.mani.exception.TransactionDuplicateAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataLoaderInstanceFactory {

    public static final String CSV = "csv";
    public static final String XML = "xml";

    @Autowired
    private XMLDataLoaderService xmlDataLoaderService;

    @Autowired
    private CSVDataLoaderService csvDataLoaderService;

    /**
     * This method is to provide instance based on the file type present in the folder
     *
     * @param fileType
     * @return
     * @throws TransactionDuplicateAppException
     */
    public DataLoaderService getInstanceByFileType(String fileType) throws TransactionDuplicateAppException {
        DataLoaderService dataLoaderService = null;
        if (CSV.equalsIgnoreCase(fileType)) {
            dataLoaderService = csvDataLoaderService;
        } else if (XML.equalsIgnoreCase(fileType)) {
            dataLoaderService = xmlDataLoaderService;
        } else {
            throw new TransactionDuplicateAppException("Different file type found and hence instance cannot be created");
        }
        return dataLoaderService;
    }
}
