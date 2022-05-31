package com.astralbrands.sage.x3.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BitBootRepository {
	@Autowired
	@Qualifier("bitBootDataSource")
	DataSource bitBootDataSource;

	private Connection connection;

	@Value("${batch.size}")
	private int batchSize;

	/*
		Method to run queries - SQL statements - to a database server
		Parameter should be formatted SQL stmt based on ProductInfoUtil.getInsertQuery()
		Throws exception || Ensures a connection has been
		established with the database
	 */
	public void runQueries(List<String> sql) throws Exception {
		if (sql == null) {
			System.err.println("empty sql queries");
			return;
		}
		if (bitBootDataSource != null) {
			try {
				if (connection == null) {
					connection = bitBootDataSource.getConnection();
					connection.setAutoCommit(true);
				}
				final Statement statement = connection.createStatement();

				System.err.println("Number of rows inserted : ");

				int occurs = 0;
				int[] count;
				int counter = 0;
				for (int i = 0; i < sql.size(); i++) {
					String query = sql.get(i);
					statement.addBatch(query); //
					if (counter == batchSize || i == sql.size() - 1) {
						try {
							count = statement.executeBatch();
							for(int b : count) {
								occurs++;
							}
						} catch (Exception e) {
							e.printStackTrace();
							System.err.println(" Exception " + e.getMessage());
						}
						counter = 0;
					}
					counter++;
				}
				System.err.println(occurs);

			} catch (SQLException ex) {
				ex.printStackTrace();
				System.err.println(ex.getMessage());
			}
		} else {
			System.err.println("Bit boot data source is null");
		}

	}

	public void runQuery(String query) {
		if (bitBootDataSource != null) {
			try {
				if (connection == null) {
					connection = bitBootDataSource.getConnection();
				}
				Statement statement = connection.createStatement();
				statement.executeUpdate(query);
				statement.close();
			} catch (SQLException bE) {
				String message = "ERROR - could not update index , query " + query;
				System.err.println(message);
				bE.printStackTrace();
			}
		} else {
			System.err.println("Bit boot data source is null");
		}
	}
}
