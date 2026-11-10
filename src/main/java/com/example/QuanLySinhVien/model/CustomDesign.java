package com.example.QuanLySinhVien.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "custom_designs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomDesign {

    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String productType;
    private String previewImageUrl;

    @Column(columnDefinition = "TEXT")
    private String configurationJson;

    private double totalPrice;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "base_product_id")
    private Product baseProduct;

    @OneToMany(mappedBy = "customDesign", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomDesignItem> items;


}