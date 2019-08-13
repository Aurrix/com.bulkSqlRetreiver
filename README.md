# com.bulkSqlRetreiver
Makes mass calls to mssql database and saves responses into excel
## Installation
Requires: Java 1.8  
1.Add database address, username and password to SQLDataLoader class.   
2.Configure gradle repostiry URL (gradle.build)  
3.Add SQLQueries to queries file /src/main/resources/JSQL.txt   
4.Build gradle project by executing 'gradlew.bat'
## Usage
BulkSqlRetriever creates an excel 'results.xlsx' file with query results in the same folder. All queries are made in the background. Overall process usually takes around half a minute.  
Incase if excel with same name already exists in the folder it will be overwriten with new one. Generated tab will have todays date.  
JSQL.txt conitans queires to be called during execution. The format should be following:  
```bash
#######QueryName
*
SQLQueryGoesHere
```
Optionally monthes and year could be replaced with {JMonth} and {JYear}. The application will automaticall input current year and previous month.
## License
[Apache License 2](https://www.apache.org/licenses/LICENSE-2.0)
