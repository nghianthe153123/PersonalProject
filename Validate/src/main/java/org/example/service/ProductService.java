package org.example.service;

import org.example.model.Product;
import org.example.model.User;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public String saveProduct(Product product) {
        productRepository.save(product);
        return "User Added Successfully";
    }
//    public Product compleProduct(Long id){
//        Optional<Product> productOp = productRepository.findById(id);
//        Product product = productOp.get();
//        Product product1 = productRepository.save(product);
//
//    }
}
