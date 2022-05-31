package com.astralbrands.sage.x3.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/*
	Configuration class for the Spring boot framework
	Beans for X3 and bitBoot to configure databases with
	datasource objects ||| x3 has a higher preference
 */
@Configuration
public class AppConfiguration {


	/*
		Bean for a datasource object that's configured
		to connect to the X3 SQL database server |||
		@Primary - Higher Precedence bean in Spring Container
	 */
	@Bean(name = "x3DataSource")
	@Primary
	public DataSource dataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url("jdbc:sqlserver://AB-SAGEDB-01\\X3:1433;DatabaseName=x3;user=bitBoot;password=pluJVT8IEGG");
		return dataSourceBuilder.build();
	}

	/*
		Bean for a datasource object that's configured
		to connect to the bitBoot SQL database server
	 */
	@Bean(name = "bitBootDataSource")
	public DataSource bitBootDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url("jdbc:sqlserver://AB-SAGEDB-01\\X3:1433;DatabaseName=bitBoot;user=bitBoot;password=pluJVT8IEGG");
		return dataSourceBuilder.build();
	}
}
