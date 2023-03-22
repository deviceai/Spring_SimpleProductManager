package com.devtime.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product get(long id){
        return productRepository.findById(id).get();
    }
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }

}
