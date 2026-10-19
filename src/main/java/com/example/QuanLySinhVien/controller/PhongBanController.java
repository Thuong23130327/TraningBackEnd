package com.example.QuanLySinhVien.controller;

import com.example.QuanLySinhVien.dto.PhongBanDto;
import com.example.QuanLySinhVien.dto.ApiResponse;
import com.example.QuanLySinhVien.service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phongban")
public class PhongBanController {

    @Autowired
    private PhongBanService phongBanService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PhongBanDto>>> getPhongBans() {
        ApiResponse<List<PhongBanDto>> res = phongBanService.getPhongBans();
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PhongBanDto>> getOnePhongBan(@PathVariable int id) {
        ApiResponse<PhongBanDto> res = phongBanService.getOnePhongBan(id);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PhongBanDto>> addPhongBan(@RequestBody PhongBanDto request) {
        ApiResponse<PhongBanDto> res = phongBanService.addPhongBan(request);
        return ResponseEntity.status(res.getStatus()).body(res);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PhongBanDto>> updatePhongBan(@PathVariable int id, @RequestBody PhongBanDto request) {
        ApiResponse<PhongBanDto> res = phongBanService.updatePhongBan(id, request);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePhongBan(@PathVariable int id) {
        ApiResponse<Void> res = phongBanService.deletePhongBan(id);
        return ResponseEntity.status(res.getStatus()).body(res);
    }
}
