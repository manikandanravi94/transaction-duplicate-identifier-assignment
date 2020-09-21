package com.mani.core;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mani.exception.TransactionDuplicateAppException;
import com.mani.model.Records;
import com.mani.model.Transaction;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class XMLDataLoaderService extends DataLoaderService {

    /**
     * this method gets the file as input and parse the xml return the transaction list
     *
     * @param file
     * @return
     * @throws TransactionDuplicateAppException
     */
    public List<Transaction> loadDataFromFile(File file) throws TransactionDuplicateAppException {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
            Records iterator = xmlMapper.readValue(file, Records.class);
            return validatedTransactions(iterator.getRecord());
        } catch (JsonParseException e) {
            throw new TransactionDuplicateAppException("Exception occured while parsing data", e);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
