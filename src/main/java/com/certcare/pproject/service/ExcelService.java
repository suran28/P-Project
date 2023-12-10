package com.certcare.pproject.service;

import com.certcare.pproject.domain.CertInfo;
import com.certcare.pproject.repository.CertInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.io.File;
@Service
@AllArgsConstructor
public class ExcelService {
    private final CertInfoRepository certInfoRepository;
    public void readExcelFileAndToEntity(String excelFilePath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // 여러 시트 중 첫 번째 시트를 선택

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                // 첫 번째 행은 헤더일 경우 스킵
                if (currentRow.getRowNum() == 0) {
                    continue;
                }

                Iterator<Cell> cellIterator = currentRow.iterator();

                CertInfo entity = new CertInfo();

                // 각 셀을 가져오기 전에 hasNext()로 확인
                entity.setCode(getCellValue(cellIterator));
                entity.setMajorJobName(getCellValue(cellIterator));
                entity.setCertName(getCellValue(cellIterator));
                entity.setW_subject(getCellValue(cellIterator));
                entity.setP_subject(getCellValue(cellIterator));
                entity.setW_testInfo(getCellValue(cellIterator));
                entity.setP_testInfo(getCellValue(cellIterator));
                entity.setW_criteria(getCellValue(cellIterator));
                entity.setP_criteria(getCellValue(cellIterator));
                entity.setOverview(getCellValue(cellIterator));
                entity.setJobInfo(getCellValue(cellIterator));
                entity.setProspect(getCellValue(cellIterator));

                certInfoRepository.save(entity);
            }
        }
    }

    // 각 셀의 값을 가져오는 메서드
    private String getCellValue(Iterator<Cell> cellIterator) {
        if (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            return getCellValue(cell);
        }
        return null; // 더 이상 읽을 셀이 없을 때
    }

    // 셀의 값을 문자열로 변환하는 메서드
    private String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (cell.getNumericCellValue() % 1 == 0) { // 정수인 경우
                    return String.valueOf((int) cell.getNumericCellValue());
                } else { // 소수인 경우
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return null; // 더 이상 읽을 셀이 없을 때 또는 다른 타입의 셀인 경우
        }
    }
}
