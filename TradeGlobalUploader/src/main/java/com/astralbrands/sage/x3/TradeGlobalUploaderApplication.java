package com.astralbrands.sage.x3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 	Main Spring boot program to execute the entire TradeGlobalUploader program
 	Uses Spring boot annotations for dependencies on other classes
 */
@SpringBootApplication
public class TradeGlobalUploaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeGlobalUploaderApplication.class, args);
	}

}
