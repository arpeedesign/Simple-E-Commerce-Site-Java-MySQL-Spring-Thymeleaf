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
        excelImporterService.importExcelFile(file);
        return "Done";
    }

/*     @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public ModelAndView importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<Product> productList = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        ModelAndView mav = new ModelAndView("list-products");
        for (int index = 1; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Product product = new Product();
                XSSFRow row = worksheet.getRow(index);
                product.setProductName(row.getCell(0).getStringCellValue());
                product.setProductType(row.getCell(1).getStringCellValue());
                productList.add(product);
                productService.saveProduct(product);
                mav.addObject("product", productList);
            }
        }
        return mav;
    }
   @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public ResponseEntity<List<Product>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<Product> productList = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 1; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Product product = new Product();
                XSSFRow row = worksheet.getRow(index);
                product.setProductName(row.getCell(0).getStringCellValue());
                product.setProductType(row.getCell(1).getStringCellValue());
                productList.add(product);
                productService.saveProduct(product);
            }
        }
        return new ResponseEntity<>(productList, status);
    }*/
}
