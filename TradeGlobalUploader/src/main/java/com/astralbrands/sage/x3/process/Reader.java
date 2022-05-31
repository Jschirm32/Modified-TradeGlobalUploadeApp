package com.astralbrands.sage.x3.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import com.astralbrands.sage.x3.util.ProductInfoUtil;

@Component
public class Reader implements Processor {

	/*
		This process retrieves a products' information from the X3 database

	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Map<String, Object>> products = exchange.getIn().getBody(ArrayList.class);
		if (products == null) {
			System.out.println("empty products, skip the process");
			exchange.setProperty("isProcessRequired", false);
			return;
		}
		List<String> queries = generateQuery(products);
		int size = queries.size();
		System.out.println(" >>> Number of query statements: " + size);
		if (queries != null && !queries.isEmpty()) {
			exchange.setProperty("isProcessRequired", true);
			exchange.setProperty("INSERT_QUERIES", queries);
		} else {
			exchange.setProperty("isProcessRequired", false);
		}

	}

	/*
		 Returns a new List of type String formatted based on
		 ProductInfoUtil's 'getInsertQuery()' function
		 to generate a valid SQL query statement to the Database
	 */
	private List<String> generateQuery(ArrayList<Map<String, Object>> products) {
		return products.parallelStream().map(product -> {
			return ProductInfoUtil.getInsertQuery(product);
		}).filter(value -> value != null).collect(Collectors.toList());

		/*
		 * List<String> queries = new ArrayList<>();
		 * 
		 * for(Map<String,Object> info: products) { String query =
		 * ProductInfoUtil.getInsertQuery(info); System.out.println("query:"+query);
		 * queries.add(query); } return queries;
		 */
	}

}
