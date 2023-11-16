package com.springboot.jpa.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter @Setter
@Table(name = "product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer  price;

    @Column(nullable = false)
    private Integer  stock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
