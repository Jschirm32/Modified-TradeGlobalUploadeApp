package com.astralbrands.sage.x3.router;

import javax.sql.DataSource;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.astralbrands.sage.x3.process.BrandListProcess;
import com.astralbrands.sage.x3.process.EmailProcessor;
import com.astralbrands.sage.x3.process.ExtractBrandProcess;
import com.astralbrands.sage.x3.process.InsertProductInfoProcess;
import com.astralbrands.sage.x3.process.PreparedCsvProcess;
import com.astralbrands.sage.x3.process.Reader;
import com.astralbrands.sage.x3.process.SftpProcessor;
import com.astralbrands.sage.x3.process.UpdateProductStatus;

/*
	Router class to route the Exchange message (product data)
	while using five Processor classes to the Exchange message in each
	process class.
 */
@Component
public class TGRouter extends RouteBuilder {

	@Autowired
	@Qualifier("x3DataSource")
	DataSource x3DataSource;

	@Autowired
	@Qualifier("bitBootDataSource")
	DataSource bitBootDataSource;

	@Autowired
	InsertProductInfoProcess insertProductInfoProcess;

	@Autowired
	BrandListProcess brandListProcess;

	@Autowired
	ExtractBrandProcess extractBrandProcess;

	@Autowired
	PreparedCsvProcess preparedCsvProcess;

	@Autowired
	SftpProcessor sftpProcess;

	@Autowired
	EmailProcessor emailProcessor;

	@Autowired
	Reader reader;

	@Autowired
	UpdateProductStatus updateProductStatus;
	
	@Value("${cron.schedule}")
	String cronScheduler;

	/*
		Configuration method for the route builder |||
		This method uses five process classes and
		initializes two database objects to retrieve product data
		stored in the X3 DB and routes the data through the BitBoot DB
		then uploads it to the TG DB.

	 */
	@Override
	public void configure() throws Exception {

		System.out.println("data source: " + x3DataSource.getConnection());
		System.out.println("bitBootDataSource source: " + bitBootDataSource.getConnection());
		onException(Throwable.class).log("Exception in router").end();
		from("timer://foo?fixedRate=true&period=600000").log("timer triggered").to("direct:query")
		.to("direct:sendEmail").end();
		//from("quartz://scheduler_name?cron=cronScheduler").id("timeId")
		/*from("quartz://myGroup/myTimerName?cron=cronScheduler").log("timer triggered").to("direct:query")
				.to("direct:sendEmail").end();*/

		from("direct:query").log("read product info from X3 datatbase")
				.to("sql:classpath:sql/SELECT_PRODUCT_INFO.sql?dataSource=#x3DataSource").id("queryId")
				.log("queryId triggered").process(reader).to("direct:insertToBitBoot").end();

		from("direct:insertToBitBoot").log("insert product info to bitboot").choice().when()
				.exchangeProperty("isProcessRequired").process(insertProductInfoProcess) // to avoid insertion
				.to("direct:selectBrand").otherwise().log("Empty product info list").endChoice().end();

		from("direct:selectBrand").id("selectBrand").log("Select list of brands")
				.to("sql:classpath:sql/SELECT_BRAND_LIST.sql?dataSource=#bitBootDataSource").process(brandListProcess)
				.choice().when().exchangeProperty("isBrandListPresent").log("brand list present")
				.to("direct:processBrandList").otherwise().log("Empty product info list").endChoice().end();

		from("direct:processBrandList").id("direct:processBrandList").loop(exchangeProperty("CamelLoopSize"))
				.log("process brand ${exchangeProperty.CamelLoopIndex}").process(extractBrandProcess).choice().when()
				.exchangeProperty("isCsvFilePresent").log("brand list present").to("direct:exportFile")
				.to("direct:updateProductIndex").otherwise().log("Empty brand info ").endChoice().end();

		from("direct:exportFile").id("exportFile").log("export file : ${header('CamelFileName')}")
				.to("file:{{direct-deposit.output.file-path}}").end();

		from("direct:updateProductIndex").id("updateProductIndex").log("updating product index..")
				.process(updateProductStatus).end();

		from("direct:sftp").id("sftp").log("move file to target location")//.process(sftpProcess)
		.end();

		from("direct:sendEmail").id("sendEmail").log("send email")
		//.process(emailProcessor)
		.log("email sent").end();

	}

}
