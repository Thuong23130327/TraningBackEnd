package com.example.QuanLySinhVien.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "custom_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double agreedPrice;
    private LocalDateTime deadline;
    private String status;
    private String paymentStatus;
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "quote_id", unique = true)
    private Quote quote;

    @OneToMany(mappedBy = "customOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderMilestone> milestones;

}