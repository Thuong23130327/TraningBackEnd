package com.example.QuanLySinhVien.model;

import lombok.*;

import javax.persistence.*;

@Table(name = "artisan_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class ArtisanProfile {
    @Id
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String introduction;
    private String portfolio_url;
    private double experience_years;
}
