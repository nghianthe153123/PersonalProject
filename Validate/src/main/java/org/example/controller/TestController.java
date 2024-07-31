package org.example.controller;

import org.example.model.Product;
import org.example.model.ResponseEntityError;
import org.example.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    ProductService productService;
    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping("test")
    public String test(){
        return "success";
    }

    @GetMapping("test1")
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> test1(){
        LOGGER.trace("API: test1");
        if(productService.getAllProducts().isEmpty()){
            ResponseEntityError responseEntityError = new ResponseEntityError(HttpStatus.NOT_FOUND, "List does not have element", "v1/admin/download/usersInMyEntity");
            System.out.println(responseEntityError.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseEntityError);
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
        }

    }
}
