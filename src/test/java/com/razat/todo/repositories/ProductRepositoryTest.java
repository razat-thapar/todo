package com.razat.todo.repositories;

import com.razat.todo.models.Category;
import com.razat.todo.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    private Logger logger = LoggerFactory.getLogger(ProductRepositoryTest.class);
    @Test()
    @Order(1)
    @Transactional
    public void testSave(){
        List<Category> categoryList = List.of(
                Category.builder().categoryName("Smartphones").build(),
                Category.builder().categoryName("Technology").build()
        );
        Product p = Product.builder()
                .name("Iphone10")
                .price(200000.0)
                .categoryList(categoryList)
                .build();
        assertNotNull(productRepository.save(p));
    }
    @Test
    @Order(2)
    @Transactional
    public void testFindById(){
        Product savedProduct = productRepository.findById(1).orElseThrow(()->new RuntimeException("Product doesn't exist!"));
        logger.info(savedProduct.toString());
        assertNotNull(savedProduct);
    }
    @Test
    @Order(3)
    @Transactional
    public void testFindByName(){
        List<Product> productList = productRepository.findByName("Iphone10");
        logger.info(productList.toString());
        assertTrue(productList.size()>0);
    }

    @Test
    @Order(4)
    @Transactional
    public void testUpdateProductById(){
        productRepository.updateProductById(1L,130000.0);
        Product updatedProduct = productRepository.findById(1).orElseThrow(()->new RuntimeException("Product doesn't exist!"));
        logger.info(updatedProduct.toString());
        assertEquals(updatedProduct.getPrice(),130000.0);
    }

}
