package com.example.QuanLySinhVien.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "product_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String imageUrl;
    private boolean isPrimary;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}