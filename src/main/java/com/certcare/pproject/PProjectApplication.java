package com.certcare.pproject;

import com.certcare.pproject.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
@Slf4j
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

		// 배포용
//		File parentDirectory = currentDirectory.getParentFile();
//		if (parentDirectory != null) {
//			parentDirectory = parentDirectory.getParentFile();
//			if (parentDirectory != null) {
//				String target = "data&AI" + File.separator + "data" + File.separator + "cert-info.xlsx";
//				File targetDirectory = new File(parentDirectory, target);
//				String filePath = String.valueOf(targetDirectory);
//				excelService.readExcelFileAndToEntity(filePath);
//			} else {
//				log.info("상위 디렉토리가 없습니다.");
//			}
//		} else {
//			log.info("상위 디렉토리가 없습니다.");
//		}

		// 로컬용
		String target = "data&AI" + File.separator + "data" + File.separator + "cert-info.xlsx";
		File targetDirectory = new File(currentDirectory, target);
		String filePath = String.valueOf(targetDirectory);
		excelService.readExcelFileAndToEntity(filePath);

	}
}
