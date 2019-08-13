/**
 * 
 */
package com.bulkSqlRetreiver.app;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.bulkSqlRetreiver.dao.ExcelSerializerTest;
import com.bulkSqlRetreiver.dao.SQLQuerryLoader;
import com.bulkSqlRetreiver.dao.SQLQuerryLoaderTest;
import com.bulkSqlRetreiver.jdbc.SQLDataLoader;

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
public class ROCStatisticsAppTest {

	public static void main(String[] args) throws URISyntaxException, IOException {

		Result result = JUnitCore.runClasses(
				ExcelSerializerTest.class, 
				SQLDataLoader.class,
				SQLQuerryLoaderTest.class
				);
		
		for (Failure failure : result.getFailures()) {
			
			System.out.println(failure.toString());
		}
		
	}

}
