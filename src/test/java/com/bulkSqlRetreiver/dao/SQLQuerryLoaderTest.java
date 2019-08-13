/**
 * 
 */
package com.bulkSqlRetreiver.dao;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bulkSqlRetreiver.dao.SQLQuerryLoader;

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
 *
 */
public class SQLQuerryLoaderTest {

	private SQLQuerryLoader queryLoader;
		
	@Before
	public void setUp() throws Exception {
		
		queryLoader = new SQLQuerryLoader();
		queryLoader.setSqlStatementsFile(new File("./src/test/resources/JSQL.txt"));
		queryLoader.loadSQLStatements();
		
	}

	@Test
	public void testQueryLoadingName() {
		Assert.assertTrue(queryLoader.getSqlStatements().containsKey("Testing Name"));
	}

	@Test
	public void testQueryLoadingStatement() {
		Assert.assertEquals("Testing Query load", queryLoader.getSqlStatements().get("Testing Name"));
	}
	
	@Test
	public void testQueryFormatting() {
		Assert.assertTrue(queryLoader.getSqlStatements().containsKey("Testing Name Formating."));
		Assert.assertEquals("Testing Query Formatting",queryLoader.getSqlStatements().get("Testing Name Formating."));
		
	}
	
	@Test
	public void testQueryLoadingDateAndYearInsert() {
				//Getting current date
				Date date = new Date();
				LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int month = localDate.getMonthValue();
				int year = localDate.getYear();
				
				//Changing date to previous month
				if (month == 1 ) {
					month = 12;
					year--;
				}
				else {month --;}
				
		Assert.assertEquals(String.valueOf(month), queryLoader.getSqlStatements().get("Testing Date Insert"));
		Assert.assertEquals(String.valueOf(year), queryLoader.getSqlStatements().get("Testing Year Insert"));
		
	}
	
}
