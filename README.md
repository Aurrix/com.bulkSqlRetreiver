# com.bulkSqlRetreiver
Makes mass calls to mssql database and saves responses into excel
## Installation
Requires: Java 1.8
1.
2.
3.
## Usage
BulkSqlRetriever creates an excel 'results.xlsx' file with query results in the same folder. All queries are made in the background. Overall process usually takes around half a minute.
Incase if excel with same name already exists in the folder it will be overwriten with new one. Generated tab will have todays date.
JSQL.txt conitans queires to be called during execution.
## License
[Apache License 2](https://www.apache.org/licenses/LICENSE-2.0)
