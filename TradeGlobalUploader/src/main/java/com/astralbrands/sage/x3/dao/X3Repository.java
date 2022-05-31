package com.astralbrands.sage.x3.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class X3Repository {

	@Autowired
	@Qualifier("x3DataSource")
	DataSource x3DataSource;

	private Connection connection;

	/*
	 Boolean expression to check for VALID SQL statement with populated product entry fields
	 POSSIBLE IMPROVEMENT : Format the string 'query' to hold different ways of formatting
	 the SQL query statement code
	 */
	public boolean checkWrongDataEntry() {
		String query = "SELECT COUNT(YPHASE_0) AS CNT FROM PROD.ITMMASTER where YPHASE_0=2 and ( ITMREF_0 is null or ITMDES1_0 is null or ITMDES2_0 is null or YCRYORI_0 <= 1 or VOU_0 is null or XA_DTH_0 = 0.0 or XA_WID_0 =0.0 or XA_HEI_0=0.0 or ITMWEI_0 = 0.0000 or WEU_0 is null )";
		if (runQuery(query) > 0) {
			return true;
		}
		return false;
	}

	/*
		Method to run a query to the 'x3' database using an Object
		of the x3 database ||| Connection Object used to connect
		the x3 database Object to the DB
	 */
	public int runQuery(String query) {
		int count = 0;
		if (x3DataSource != null) {
			try {
				if (connection == null) {
					connection = x3DataSource.getConnection();
				}
				try (Statement statement = connection.createStatement();) {
					ResultSet result = statement.executeQuery(query);
					while (result.next()) {
						count = result.getInt("CNT");
					}
				} catch (SQLException bE) {
					String message = "ERROR - could not update index , query " + query;
					System.err.println(message);
					bE.printStackTrace();
				}
			} catch (SQLException bE) {
				String message = "ERROR - could not update index , query " + query;
				System.err.println(message);
				bE.printStackTrace();
			}
		} else {
			System.err.println("x3DataSource data source is null");
		}
		return count;
	}

}
