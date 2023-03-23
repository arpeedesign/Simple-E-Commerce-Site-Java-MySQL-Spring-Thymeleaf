package com.portfolio.webshop_0321.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelImporterService {
    ResponseEntity importExcelFile(MultipartFile file) throws IOException;
}
