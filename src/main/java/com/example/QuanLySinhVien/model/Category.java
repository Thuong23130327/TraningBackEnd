package com.example.QuanLySinhVien.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    private String slug;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}