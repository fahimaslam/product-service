package com.sparkkees.pk.product_service.dto;

import lombok.Data;

@Data
public class ProductDTO {
    public Long id;
    public String name;
    public String description;
    public double price;
    public int stock;
}
