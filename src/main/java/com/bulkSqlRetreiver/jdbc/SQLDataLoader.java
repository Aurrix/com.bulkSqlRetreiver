/**
 * 
 */
package com.bulkSqlRetreiver.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

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
public class SQLDataLoader {
	
	public Map <String, Integer> queryResults;
	public Map <String, String> sqlStatements;
	private Connection con;
 	private String connectionUrl;
 	private String username;
 	private String password;
	
	public SQLDataLoader() {
		
		setUpConnection();
		 
	}

	private void setUpConnection() {
		// Change Integrated Security to True for Windows Login
				 connectionUrl = "jdbc:sqlserver://DatabaseAddress:port;databaseName=master;integratedSecurity=false";
				 username = "unexposed login";
				 password = "unexposed password";
				 queryResults = new LinkedHashMap<String, Integer>();
	}
	
	
	public void runQueryStatements() {
		
		try  {
				
				//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
				con = DriverManager.getConnection(connectionUrl,username,password); 
		
				Statement stmt = con.createStatement(); {
				
			
				//System.out.println(sqlStatements.size());
					
				for (Map.Entry<String,String> entry : sqlStatements.entrySet())  {
				String query = entry.getValue();
				
				if(!query.isEmpty()) {
				//System.out.println(query);
				ResultSet rs = stmt.executeQuery(query);
				rs.next();
				int value = Integer.valueOf(rs.getString(1));
				queryResults.put(entry.getKey(),value);
				}
				else {
					queryResults.put(entry.getKey(), 0);
				}
			}
		}
	}
	        // Handle any errors that may have occurred.
	        catch (SQLException e) {
	        	e.printStackTrace();
	        }
		
	}
	
	public Map<String, Integer> getQueryResults() {
		return queryResults;
	}

	public void setSqlStatements(Map<String, String> sqlStatements) {
		this.sqlStatements = sqlStatements;
	}
	

}
