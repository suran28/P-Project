package com.certcare.pproject;

import com.certcare.pproject.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class PProjectApplication implements CommandLineRunner {

	@Autowired
	private ExcelService excelService;
	public static void main(String[] args) {
		SpringApplication.run(PProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
		File currentDirectory = new File(System.getProperty("user.dir"));
		String target = "data&AI" + File.separator + "data" + File.separator + "cert-info.xlsx";
		File targetDirectory = new File(currentDirectory, target);
		String filePath = String.valueOf(targetDirectory);
		excelService.readExcelFileAndToEntity(filePath);
	}
}
