package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.service.ExcelImporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ExcelImporterController {
    @Autowired
    ExcelImporterService excelImporterService;

    @PostMapping("/import-excel")
    public String importExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        excelImporterService.saveExcel(file);
        System.out.println("Mentve: "+file.getOriginalFilename());
        excelImporterService.importExcelFile(file);
        System.out.println("Hozzáadva: "+file.getName());
        return "Done";
    }

}
