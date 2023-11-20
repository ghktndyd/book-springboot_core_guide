package com.springboot.advanced_jpa.data.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void sortingAndPagingTest() {
        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(5000);
        product1.setStock(300);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());


        Product product2 = new Product();
        product2.setName("펜");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setCreatedAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());

        Product product3 = new Product();
        product3.setName("펜");
        product3.setPrice(500);
        product3.setStock(50);
        product3.setCreatedAt(LocalDateTime.now());
        product3.setUpdatedAt(LocalDateTime.now());

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);

        System.out.println(productRepository.findByNameOrderByNumberAsc("펜"));
        System.out.println(productRepository.findByNameOrderByNumberDesc("펜"));

        System.out.println(productRepository.findByNameOrderByPriceAscStockDesc("펜"));

        System.out.println(productRepository.findByName("펜", Sort.by(Order.asc("price"))));
        System.out.println(productRepository.findByName("펜", Sort.by(Order.asc("price"), Order.desc("stock"))));

        System.out.println(productRepository.findByName("펜", getSort()));

        System.out.println(productRepository.findByName("펜", PageRequest.of(0, 2)));

        // 페이징 쿼리 메서드 호출 방법
        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0, 2));

        System.out.println(productPage.getContent());

        System.out.println(productRepository.findByName("펜", PageRequest.of(0, 2, Sort.by(Order.asc("price")))));
        System.out.println(productRepository.findByName("펜", PageRequest.of(0, 2, Sort.by(Order.asc("price")))).getContent());
    }

    private Sort getSort() {
        return Sort.by(
                Order.asc("price"),
                Order.desc("stock")
        );
    }

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void queryDslTest() {
        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query.
                from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList) {
            System.out.println("-------------");
            System.out.println();
            System.out.println("product.getNumber() = " + product.getNumber());
            System.out.println("product.getName() = " + product.getName());
            System.out.println("product.getPrice() = " + product.getPrice());
            System.out.println("product.getStock() = " + product.getStock());
            System.out.println();
            System.out.println("-------------");
        }
    }

    @Test
    void queryDslTest2() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList) {
            System.out.println("-------------");
            System.out.println();
            System.out.println("product.getNumber() = " + product.getNumber());
            System.out.println("product.getName() = " + product.getName());
            System.out.println("product.getPrice() = " + product.getPrice());
            System.out.println("product.getStock() = " + product.getStock());
            System.out.println();
            System.out.println("-------------");
        }
    }
}