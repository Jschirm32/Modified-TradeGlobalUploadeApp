package com.astralbrands.sage.x3.process;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.astralbrands.sage.x3.dao.BitBootRepository;

/*
	Processor class to insert a product's
	information into the Bit Boot database
 */

@Component
public class InsertProductInfoProcess implements Processor {

	/*
	 * @Autowired
	 * 
	 * @Qualifier("bitBootDataSource") DataSource bitBootDataSource;
	 * 
	 * private Connection connection;
	 */

	@Autowired
	BitBootRepository bitBootRepository;

	@Override
	public void process(Exchange exchange) throws Exception {
		List<String> sql = exchange.getProperty("INSERT_QUERIES", List.class);
		bitBootRepository.runQueries(sql);
		/*
		 * if (sql == null) { System.err.println("empty sql queries"); return; }
		 * exchange.setProperty("INSERT_QUERIES", null); if (bitBootDataSource != null)
		 * { try { if (connection == null) { connection =
		 * bitBootDataSource.getConnection(); connection.setAutoCommit(true); } final
		 * Statement statement = connection.createStatement();
		 * 
		 * sql.parallelStream().map(q -> { try { System.out.println("q:"+q); return
		 * statement.execute(q); } catch (SQLException e) { e.printStackTrace(); return
		 * false; } }).collect(Collectors.toList());
		 * 
		 * int[] count; int counter = 0; for (int i = 0; i < sql.size(); i++) { String
		 * query = sql.get(i); statement.addBatch(query); // if (counter == 10 || i ==
		 * sql.size() - 1) { try { count = statement.executeBatch();
		 * System.err.println("Number of rows inserted" + count); } catch (Exception e)
		 * { e.printStackTrace(); System.err.println(" Exception " + e.getMessage()); }
		 * 
		 * counter = 0; } counter++; }
		 * 
		 * } catch (SQLException ex) { ex.printStackTrace();
		 * System.err.println(ex.getMessage()); } } else {
		 * System.err.println("Bit boot data source is null"); }
		 */

	}

}
