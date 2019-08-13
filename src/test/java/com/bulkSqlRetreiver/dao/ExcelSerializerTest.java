/**
 * 
 */
package com.bulkSqlRetreiver.dao;

import static org.junit.Assert.assertArrayEquals;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bulkSqlRetreiver.dao.ExcelSerializer;



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
public class ExcelSerializerTest {

	ExcelSerializer excelSerializer;
	File excelFile ;
	
	@Before
	public void setUp() throws URISyntaxException {
		
		excelSerializer = new ExcelSerializer();
		excelFile = new File("./results.xlsx");
	}
	
	@Test
	public void testFileExists() {
		excelFile.delete();
		excelSerializer.saveToWorkbook(new HashMap<String, Integer>(){{
			put("Testing Line", 0);
		}});
		Assert.assertTrue(excelFile.exists());
				
	}
	
	@Test
	public void testWritingValuesToExcel () throws InvalidFormatException, IOException {
		excelSerializer.saveToWorkbook(new LinkedHashMap<String, Integer>(){{
			put("Testing Line", 0);
			put("Testing Line 4", 4);
			put("Testing Line 11", 11);
		}});
		
		Workbook testWorkbook = WorkbookFactory.create(excelFile);
		
		Sheet sheet =  testWorkbook.getSheetAt(0);
		
		Assert.assertEquals("Testing Line", sheet.getRow(0).getCell(0).getStringCellValue());
		Assert.assertEquals(0, (int) sheet.getRow(0).getCell(1).getNumericCellValue());
		Assert.assertEquals("Testing Line 4", sheet.getRow(1).getCell(0).getStringCellValue());
		Assert.assertEquals(4 ,(int) sheet.getRow(1).getCell(1).getNumericCellValue());
		Assert.assertEquals("Testing Line 11", sheet.getRow(2).getCell(0).getStringCellValue());
		Assert.assertEquals(11,(int) sheet.getRow(2).getCell(1).getNumericCellValue());
		
		testWorkbook.close();
	}
}
