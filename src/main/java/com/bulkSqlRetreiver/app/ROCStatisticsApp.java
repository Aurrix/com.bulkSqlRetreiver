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
package com.bulkSqlRetreiver.app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import com.bulkSqlRetreiver.dao.ExcelSerializer;
import com.bulkSqlRetreiver.dao.SQLQuerryLoader;
import com.bulkSqlRetreiver.jdbc.SQLDataLoader;

public class ROCStatisticsApp {

	public static void main(String[] args) throws URISyntaxException, IOException {
		
		// Load queries
		SQLQuerryLoader sqlLoader = new SQLQuerryLoader();
		//Loading Queries
		sqlLoader.loadFile();
		sqlLoader.loadSQLStatements();
		// Setting up properties
		SQLDataLoader sqlDataLoader = new SQLDataLoader();
		// Pass sql statements
		sqlDataLoader.setSqlStatements(sqlLoader.getSqlStatements());
		// Query database
		sqlDataLoader.runQueryStatements();
		// Getting Results
		Map<String, Integer> results = sqlDataLoader.getQueryResults();
		// Writing to excel
		ExcelSerializer excelSerializer = new ExcelSerializer();
		excelSerializer.saveToWorkbook(results);
	    
	}

}
