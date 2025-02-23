package com.sparkkees.pk.product_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for PostgreSQL
    private int id;
    private String name;
    @Version
    private int version;
}
