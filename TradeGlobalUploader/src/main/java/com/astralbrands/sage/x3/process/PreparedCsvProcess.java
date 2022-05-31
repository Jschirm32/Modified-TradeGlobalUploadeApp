package com.astralbrands.sage.x3.process;

import java.util.ArrayList;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/*
	Prepares Csv file for brands
 */
@Component
public class PreparedCsvProcess implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		ArrayList<Map<String, Object>> brands = exchange.getIn().getBody(ArrayList.class);
		System.out.println("products  " + brands);		
	}

}
