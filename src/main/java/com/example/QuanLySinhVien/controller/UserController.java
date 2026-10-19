package com.example.QuanLySinhVien.controller;

import com.example.QuanLySinhVien.dto.UserDto;
import com.example.QuanLySinhVien.dto.ApiResponse;
import com.example.QuanLySinhVien.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getUsers() {
        ApiResponse<List<UserDto>> res = userService.getUsers();
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUser(@PathVariable int id) {
        ApiResponse<UserDto> res = userService.getUser(id);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> addUser(@RequestBody UserDto request) {
        ApiResponse<UserDto> res = userService.addUser(request);
        return ResponseEntity.status(res.getStatus()).body(res);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable int id, @RequestBody UserDto request) {
        ApiResponse<UserDto> res = userService.updateUser(id, request);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable int id) {
        ApiResponse<Void> res = userService.delete(id);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

}
