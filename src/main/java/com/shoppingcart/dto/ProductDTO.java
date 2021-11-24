package com.shoppingcart.dto;

import com.shoppingcart.model.Category;
import lombok.Data;

import javax.persistence.*;

@Data
public class ProductDTO {

    private Long id;

    private String name;
    private int categoryId;

    private double price;

    private double weight;

    private String description;

    private String imageName;
}

