package com.example.QuanLySinhVien.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "custom_materials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String partType;
    private String productType;
    private double price;
    private int stockQuantity;
    private String imageUrl;
    private String status;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "artisan_id")
    private User artisan;


}