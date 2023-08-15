package com.amplifierconsultancy.ugmk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;

@SpringBootApplication(exclude = {SolrAutoConfiguration.class})
public class UgmkApplication {

	public static void main(String[] args) {
		SpringApplication.run(UgmkApplication.class, args);
	}

}
