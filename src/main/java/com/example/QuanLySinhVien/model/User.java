package com.example.QuanLySinhVien.model;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
public class User {

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ArtisanProfile artisanProfile;
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String full_name;
    private String phone;
    private String avatarUrl;
    private String role;
    private LocalDateTime createdAt;

}
