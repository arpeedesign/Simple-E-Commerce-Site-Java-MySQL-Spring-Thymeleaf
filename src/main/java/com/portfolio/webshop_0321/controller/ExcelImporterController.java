package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.service.ExcelImporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
public class ExcelImporterController {
    @Autowired
    ExcelImporterService excelImporterService;

    @PostMapping("/import-excel")
    public ModelAndView importExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        ModelAndView mav = new ModelAndView("redirect:/list");
        excelImporterService.saveExcel(file);
        System.out.println("Saved: "+file.getOriginalFilename());
        excelImporterService.importExcelProductFile(file);
        System.out.println("Added: "+file.getOriginalFilename());
        excelImporterService.deleteExcel(file);
        System.out.println("Deleted: "+file.getOriginalFilename());
        return mav;
    }

    @PostMapping("/import-data-excel")
    public ModelAndView importExcelDataFile(@RequestParam("file") MultipartFile file) throws IOException {
        ModelAndView mav = new ModelAndView("redirect:/dashboard");
        return mav;
    }

}
