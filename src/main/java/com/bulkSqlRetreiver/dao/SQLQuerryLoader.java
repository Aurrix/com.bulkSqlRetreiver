/**
 * @Author: Alisher Urunov
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.bulkSqlRetreiver.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;

public class SQLQuerryLoader {

	private File sqlStatementsFile;
	
	public LinkedHashMap<String,String> sqlStatements;
	
	private String queries;
	
	private int month, year;
	
	/**
	 * The class responsible for loading SQL statements from 
	 * from JSQL.txt file. File loaded then to a Map.
	 * and post formated.
	 */
		
	public SQLQuerryLoader() {
		
		// Using Linked map to maintain order
		sqlStatements = new LinkedHashMap<String, String>();
		
		//Getting current date
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		month = localDate.getMonthValue();
		year = localDate.getYear();
		
		//Changing date to previous month
		if (month == 1 ) {
			month = 12;
			year--;
		}
		else {month --;}
}

	public void loadFile() throws URISyntaxException {
		// Getting file address
		URI	uri = new URI (getClass().getClassLoader().getResource("JSQL.txt").getFile());
		sqlStatementsFile = new File(uri.getPath());
	}
	
	public void loadSQLStatements () throws URISyntaxException, IOException {
		
		queries = new String(Files.readAllBytes(sqlStatementsFile.toPath()));
		
		//System.out.println(queries);
		
		//File split into header and query 
		
		String [] separatedQueries = queries.split("#######");
		
		for (String string : separatedQueries) {
			// Doing formatting 
			string = string
					.replaceAll("\r", "")
					.replaceAll("\n", "")
					.replaceAll(" +"," ")
					.replaceAll("#######", "")
					.replaceAll("\\{JMonth\\}", String.valueOf(month))
					.replaceAll("\\{JYear\\}", String.valueOf(year));
			
			String [] asteriskSplit = string.split("\\*");
			
			if(asteriskSplit.length>1) {sqlStatements.put(asteriskSplit[0], asteriskSplit[1]);}
			else {sqlStatements.put(asteriskSplit[0],"");}
			
		}
		
		// Removing empty entries
		sqlStatements.remove("");
	}

	public LinkedHashMap<String, String> getSqlStatements() {
		return sqlStatements;
	}

	public void setSqlStatementsFile(File sqlStatementsFile) {
		this.sqlStatementsFile = sqlStatementsFile;
	}

	
}
