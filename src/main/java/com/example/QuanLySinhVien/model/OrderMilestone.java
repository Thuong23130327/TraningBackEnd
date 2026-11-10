package com.example.QuanLySinhVien.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_milestones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderMilestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;
    private String photoUrl;

    private boolean isCompleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @ManyToOne

    @JoinColumn(name = "custom_order_id")
    private CustomOrder customOrder;

}