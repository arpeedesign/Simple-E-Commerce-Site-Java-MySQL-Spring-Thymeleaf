package com.portfolio.webshop_0321.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;

public interface ExcelImporterService {
    ResponseEntity importExcelProductFile(MultipartFile file) throws IOException;
    ResponseEntity importExcelDataFile(MultipartFile file) throws IOException;
    void saveExcel(MultipartFile file);
    InputStream deleteExcel(MultipartFile file) throws IOException;
}
