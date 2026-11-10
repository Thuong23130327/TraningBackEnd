package com.example.QuanLySinhVien.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double price;
    private double oldPrice;
    private int stockQuantity;
    private String color;
    private String status;
    private LocalDateTime createdAt;

    //Danh mục
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    //  Thợ làm ra sp
    @ManyToOne
    @JoinColumn(name = "artisan_id")
    private User artisan;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> images;


}