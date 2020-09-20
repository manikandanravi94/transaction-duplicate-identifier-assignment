package com.mani.core;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.mani.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CSVDataLoaderService extends DataLoaderService {

    private static final String FILE_NAME = "records.csv";

    /**
     * This method fetches data from csv file and returns a validated transaction list
     *
     * @return
     */
    public List<Transaction> loadDataFromFile(File file) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            MappingIterator<Transaction> readValues = getTransactionMappingIterator(file, bootstrapSchema);
            return validatedTransactions(readValues.readAll());
        } catch (IOException e) {
            //logger and throw our custom error
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private MappingIterator<Transaction> getTransactionMappingIterator(File file, CsvSchema bootstrapSchema) throws IOException {
        CsvMapper mapper = new CsvMapper();
        mapper.enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE);
        MappingIterator<Transaction> readValues =
                mapper.readerWithSchemaFor(Transaction.class).with(bootstrapSchema).readValues(file);
        return readValues;
    }
}
