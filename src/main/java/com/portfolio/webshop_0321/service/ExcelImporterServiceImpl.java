package com.portfolio.webshop_0321.service;

import com.portfolio.webshop_0321.entity.Product;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelImporterServiceImpl implements ExcelImporterService {
    @Autowired
    ProductService productService;
    private final Path root = Paths.get("./src/main/resources/");

    @Override
    public ResponseEntity importExcelFile(MultipartFile file) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<Product> productList = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
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
    }

    @Override
    public void saveExcel(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public InputStream deleteExcel(MultipartFile file) throws IOException {
        try {
            Files.delete(this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                throw new RuntimeException("A file of that name does not exists.");
            }
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
}
