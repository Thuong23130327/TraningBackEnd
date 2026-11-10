package com.example.QuanLySinhVien.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "custom_design_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomDesignItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;
    private String positionNote;

    @ManyToOne
    @JoinColumn(name = "custom_design_id")
    private CustomDesign customDesign;

    // Chọn nguyên vật liệu nào
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    private CustomMaterial material;
}