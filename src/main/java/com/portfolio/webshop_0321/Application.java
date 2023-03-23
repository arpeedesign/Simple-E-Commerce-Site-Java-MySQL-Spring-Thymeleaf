package com.portfolio.webshop_0321;

import com.portfolio.webshop_0321.controller.ExcelImporterController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.IOException;

@SpringBootApplication
@EntityScan
public class Application {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
	}

}
