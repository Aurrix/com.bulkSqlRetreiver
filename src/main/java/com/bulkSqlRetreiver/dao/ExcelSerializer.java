package com.bulkSqlRetreiver.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
public class ExcelSerializer {

	Workbook workBook;

	FileOutputStream outputFile;
	
	public ExcelSerializer() {
	
	//Initiate Excel WorkBook	
		
	workBook = new XSSFWorkbook();
		
		
}
	
	public void saveToWorkbook(Map<String, Integer> statistics) {
		
		if (statistics==null) {return;}
		
		// Getting current date for sheet tab name
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		// Creating new sheet
		Sheet sheet = workBook.createSheet("Statistics for " + localDate.toString());
		
		// Pasting over values to excel
		int rowCount = 0;
		for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
			
			Row row = sheet.createRow(rowCount);
			
			row.createCell(0).setCellValue(entry.getKey());
			
			row.createCell(1).setCellValue(entry.getValue());
			
			rowCount++;
		}
		
		// Writing Excel Workbook
		
		try {
			outputFile = new FileOutputStream("./results.xlsx");
			workBook.write(outputFile);
			outputFile.close();
			workBook.close();
		} catch ( IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
