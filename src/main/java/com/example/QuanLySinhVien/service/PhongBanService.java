package com.example.QuanLySinhVien.service;

import com.example.QuanLySinhVien.dto.ErrorCode;
import com.example.QuanLySinhVien.dto.PhongBanDto;
import com.example.QuanLySinhVien.dto.ApiResponse;
import com.example.QuanLySinhVien.model.PhongBan;
import com.example.QuanLySinhVien.repository.PhongBanRepository;
import com.example.QuanLySinhVien.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhongBanService {
    @Autowired
    private PhongBanRepository phongBanRepository;
    @Autowired
    private UserRepository userRepository;

    public ApiResponse<List<PhongBanDto>> getPhongBans() {
        List<PhongBan> list = phongBanRepository.getPhongBans();
        List<PhongBanDto> dtos = new ArrayList<>();
        for (PhongBan pb : list) {
            dtos.add(mapToDto(pb));
        }
        return ApiResponse.success(dtos, "Lấy danh sách phòng ban thành công");
    }

    public ApiResponse<PhongBanDto> getOnePhongBan(int id) {
        PhongBan pb = phongBanRepository.getOnePhongBan(id);
        if (pb == null) {
            return ApiResponse.error(404, ErrorCode.PHONGBAN_ERROR_NOT_FOUND, "Không tìm thấy phòng ban với id: " + id);
        }
        return ApiResponse.success(mapToDto(pb), "Lấy thông tin phòng ban thành công");
    }

    public ApiResponse<PhongBanDto> addPhongBan(PhongBanDto dto) {
        if (dto.getTen() == null || dto.getTen().trim().isEmpty()) {
            return ApiResponse.error(400, ErrorCode.PHONGBAN_ERROR_NAME_EMPTY, "Tên phòng ban không được để trống");
        }
        PhongBan pb = new PhongBan();
        pb.setTen(dto.getTen().trim());
        PhongBan saved = phongBanRepository.addPhongBan(pb);
        return ApiResponse.created(mapToDto(saved), "Thêm phòng ban thành công");
    }

    public ApiResponse<PhongBanDto> updatePhongBan(int id, PhongBanDto dto) {
        PhongBan pbHienTai = phongBanRepository.getOnePhongBan(id);
        if (pbHienTai == null) {
            return ApiResponse.error(404, ErrorCode.PHONGBAN_ERROR_NOT_FOUND, "Không tìm thấy phòng ban với id: " + id);
        }
        if (dto.getTen() == null || dto.getTen().trim().isEmpty()) {
            return ApiResponse.error(400, ErrorCode.PHONGBAN_ERROR_NAME_EMPTY, "Tên phòng ban không được để trống");
        }
        pbHienTai.setTen(dto.getTen().trim());
        PhongBan updated = phongBanRepository.updatePhongBan(pbHienTai);
        return ApiResponse.success(mapToDto(updated), "Cập nhật phòng ban thành công");
    }

    public ApiResponse<Void> deletePhongBan(int id) {
        if (phongBanRepository.getOnePhongBan(id) == null) {
            return ApiResponse.error(404, ErrorCode.PHONGBAN_ERROR_NOT_FOUND, "Không tìm thấy phòng ban với id: " + id);
        }
          boolean dangCoUser = userRepository.getUsers().stream().anyMatch(u -> u.getPhongBanId() == id);
        if (dangCoUser) {
            return ApiResponse.error(400, ErrorCode.GENERAL_ERROR_INVALID_DATA, "Không thể xóa phòng ban đang có nhân viên!");
        }
        phongBanRepository.deletePhongBan(id);
        return ApiResponse.success(null, "Xóa phòng ban thành công");
    }

    private PhongBanDto mapToDto(PhongBan pb) {
        return PhongBanDto.builder()
                .id(pb.getId())
                .ten(pb.getTen())
                .build();
    }
}
