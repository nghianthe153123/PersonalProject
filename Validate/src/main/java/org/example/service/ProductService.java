package org.example.service;

import jakarta.annotation.PostConstruct;
import org.example.model.Product;
import org.example.model.User;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public String saveProduct(Product product) {
        productRepository.save(product);
        return "User Added Successfully";
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> findProductsWithSorting(String field){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Product> findProductsWithPagination(int offset,int pageSize){
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize));
        return products;
    }

    public Page<Product> findProductsWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return products;
    }

//    @PostConstruct
//    public void initDB(){
//        List<Product> products = IntStream.rangeClosed(1, 200)
//                .mapToObj(i -> new Product("product" + i, new Random().nextInt(100), new Random().nextInt(50000)))
//                .collect(Collectors.toList());
//        productRepository.saveAll(products);
//    }
//    public Product compleProduct(Long id){
//        Optional<Product> productOp = productRepository.findById(id);
//        Product product = productOp.get();
//        Product product1 = productRepository.save(product);
//
//    }
}
