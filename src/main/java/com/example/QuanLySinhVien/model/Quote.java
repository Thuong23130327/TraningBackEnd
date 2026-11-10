package com.example.QuanLySinhVien.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quotes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price;
    private int estimatedDays;
    @Column(columnDefinition = "TEXT")
    private String message;

    private String status;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private CustomRequest request;

    @ManyToOne
    @JoinColumn(name = "artisan_id")
    private User artisan;
    @OneToOne(mappedBy = "quote")
    private CustomOrder customOrder;


}