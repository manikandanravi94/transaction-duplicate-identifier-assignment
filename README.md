# Transaction-duplicate-identifier-assignment
Application reads the CSV or XML file whichever is placed in the following path "src/main/resources/test" and takes out the transaction list from the file. Segregate the valid and invalid transaction from the transaction and prints it in the log file.  
# Current version of the application
0.0.1-SNAPSHOT
# Technologies used
spring-boot
# External Dependencies used
To read a CSV file 

#Maven Dependency

<dependency>
			<groupId>com.fasterxml.jackson.dataformat</groupId>
			<artifactId>jackson-dataformat-csv</artifactId>
			<version>2.11.2</version>
		</dependency>

To read a xml file

<dependency>
			<groupId>com.fasterxml.jackson.dataformat</groupId>
			<artifactId>jackson-dataformat-xml</artifactId>
			<version>2.11.2</version>
		</dependency>

#Java version used for development
1.8

#Configurable Validations

written validations of a transaction in a predicate. If any new validation need to be added can be added since it is easily configurable.

Limitaions

#CSV reader can be written in the following format too.

File csvFile = new File(fileName);
CsvMapper mapper = new CsvMapper();
CsvSchema schema = CsvSchema.emptySchema().withHeader(); // use first row as header; otherwise defaults are fine
MappingIterator<Map<String,String>> it = mapper.readerFor(Map.class)
   .with(schema)
   .readValues(csvFile);
while (it.hasNext()) {
  Map<String,String> rowAsMap = it.next();
  // access by column name, as defined in the header row...
}

We can have our custom validation while iterating through the field itself.

