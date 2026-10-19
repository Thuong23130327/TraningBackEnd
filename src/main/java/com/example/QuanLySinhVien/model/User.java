package com.example.QuanLySinhVien.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private int id;
    private String ten;
    private int phongBanId;
}
