package com.springboot.test.data.repository;

import com.springboot.test.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 제네릭 안에 들어가있는 것은 대상 엔티티와 대상 엔티티의 @Id 필드 타입이다.
}
