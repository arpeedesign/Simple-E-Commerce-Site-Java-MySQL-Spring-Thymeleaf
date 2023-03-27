package com.portfolio.webshop_0321.controller;

import com.portfolio.webshop_0321.entity.Product;
import com.portfolio.webshop_0321.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping({"/showproducts", "/list"})
    public ModelAndView showProducts() {
        ModelAndView mav = new ModelAndView("list-products");
        List<Product> list = productService.findAll();
        mav.addObject("products", list);
        return mav;
    }

    @GetMapping("/addProductForm")
    public ModelAndView addProductForm() {
        ModelAndView mav = new ModelAndView("add-product-form");
        Product newProduct = new Product();
        mav.addObject("product", newProduct);
        return mav;
    }
    @GetMapping("/addBulkProductForm")
    public ModelAndView addBulkProductForm() {
        ModelAndView mav = new ModelAndView("add-bulk-product-form");
        Product newProduct = new Product();
        mav.addObject("product", newProduct);
        return mav;
    }

    @PostMapping("/saveProduct")
    public ModelAndView saveProduct(Product product) {
        productService.saveProduct(product);
        return new ModelAndView("redirect:/list");
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long productId) {
        ModelAndView mav = new ModelAndView("add-product-form");
        Product product = productService.findID(productId);
        mav.addObject("product", product);
        return mav;
    }

    @GetMapping("/deleteProduct")
    public ModelAndView deleteEmployee(@RequestParam Long productId) {
        productService.deleteProduct(productId);
        return new ModelAndView("redirect:/list");
    }

    @GetMapping("/findById")
    public Product findById(@RequestParam Long Id) {
        return productService.findID(Id);
    }
}
