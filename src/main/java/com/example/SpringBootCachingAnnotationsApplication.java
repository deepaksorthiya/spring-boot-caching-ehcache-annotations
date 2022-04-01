package com.example;

import com.example.model.Product;
import com.example.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCachingAnnotationsApplication implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootCachingAnnotationsApplication.class);

    @Autowired
    ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCachingAnnotationsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("IPhone ->" + productService.getByName("IPhone"));
        logger.info("IPhone ->" + productService.getByName("IPhone"));
        logger.info("IPhone ->" + productService.getByName("IPhone"));


        logger.info("HTC ->" + productService.getByName("HTC"));
        logger.info("HTC ->" + productService.getByName("HTC"));
        logger.info("HTC ->" + productService.getByName("HTC"));

        Product product = new Product("IPhone", 550);
        productService.updateProduct(product);

        logger.info("IPhone ->" + productService.getByName("IPhone"));
        logger.info("IPhone ->" + productService.getByName("IPhone"));
        logger.info("IPhone ->" + productService.getByName("IPhone"));


        logger.info("Refreshing all products");

        productService.refreshAllProducts();
        logger.info("IPhone [after refresh]->" + productService.getByName("IPhone"));
        logger.info("IPhone [after refresh]->" + productService.getByName("IPhone"));
        logger.info("IPhone [after refresh]->" + productService.getByName("IPhone"));
    }
}
