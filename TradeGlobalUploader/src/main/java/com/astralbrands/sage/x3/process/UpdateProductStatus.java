package com.astralbrands.sage.x3.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.astralbrands.sage.x3.dao.BitBootRepository;
import com.astralbrands.sage.x3.util.ProductInfoUtil;

/*
	Process class to
 */
@Component
public class UpdateProductStatus implements Processor {

	@Autowired
	private BitBootRepository bitBootRepository;

	@Override
	public void process(Exchange exchange) throws Exception {

		String brand = exchange.getProperty("BRAND_NAME", String.class);
		System.out.println("updating index for brand name :" + brand);
		bitBootRepository.runQuery(ProductInfoUtil.getUpdateQuery(brand));
	}

}
