package com.example.QuanLySinhVien.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhongBan {
    private int id;
    private String ten;
    private List<User> userList;

}
