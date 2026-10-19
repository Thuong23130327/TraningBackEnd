package com.example.QuanLySinhVien.service;

import com.example.QuanLySinhVien.dto.ErrorCode;
import com.example.QuanLySinhVien.dto.UserDto;
import com.example.QuanLySinhVien.dto.ApiResponse;
import com.example.QuanLySinhVien.model.PhongBan;
import com.example.QuanLySinhVien.model.User;
import com.example.QuanLySinhVien.repository.PhongBanRepository;
import com.example.QuanLySinhVien.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhongBanRepository phongBanRepository;

    public ApiResponse<List<UserDto>> getUsers() {
        List<User> users = userRepository.getUsers();
        List<UserDto> listDto = new ArrayList<>();
        for (User u : users) {
            listDto.add(mapToDto(u));
        }
        return ApiResponse.success(listDto, "Lấy danh sách người dùng thành công");
    }


    public ApiResponse<UserDto> getUser(int id) {
        User user = userRepository.getUser(id);
        if (user == null) {
            return ApiResponse.error(404, ErrorCode.USER_ERROR_NOT_FOUND, "Không tìm thấy user với id: " + id);
        }
        return ApiResponse.success(mapToDto(user), "Lấy user thành công");
    }


    public ApiResponse<Void> delete(int id) {
        if (userRepository.getUser(id) == null) {
            return ApiResponse.error(404, "Khong tim thay user theo id " + id, null);
        }
        userRepository.deleteUser(id);
        return ApiResponse.success(null, "Xóa người dùng thành công");
    }

    public ApiResponse<UserDto> addUser(UserDto dto) {
        if (dto.getTen() == null || dto.getTen().trim().isEmpty()) {
            return ApiResponse.error(400, ErrorCode.USER_ERROR_NAME_EMPTY, "Tên không được để trống");
        }
        PhongBan pb = phongBanRepository.getOnePhongBan(dto.getPhongBanId());
        if (pb == null) {
            return ApiResponse.error(404, ErrorCode.PHONGBAN_ERROR_NOT_FOUND, "Phòng ban không tồn tại");
        }
        User user = User.builder()
                .ten(dto.getTen().trim())
                .phongBanId(dto.getPhongBanId())
                .build();
        User saved = userRepository.addUser(user);
        return ApiResponse.created(mapToDto(saved), "Thêm user thành công");
    }


    public ApiResponse<UserDto> updateUser(int id, UserDto dto) {
        // 1. Kiểm tra user tồn tại không
        User userHienTai = userRepository.getUser(id);
        if (userHienTai == null) {
            return ApiResponse.error(404, ErrorCode.USER_ERROR_NOT_FOUND, "User không tồn tại");
        }
        // 2. Kiểm tra tên
        if (dto.getTen() == null || dto.getTen().trim().isEmpty()) {
            return ApiResponse.error(400, ErrorCode.USER_ERROR_NAME_EMPTY, "Tên không được để trống");
        }
        // 3. Kiểm tra phòng ban
        PhongBan pb = phongBanRepository.getOnePhongBan(dto.getPhongBanId());
        if (pb == null) {
            return ApiResponse.error(404, ErrorCode.PHONGBAN_ERROR_NOT_FOUND, "Phòng ban không tồn tại");
        }
        // 4. Gán dữ liệu đúng chuẩn vào đối tượng
        userHienTai.setTen(dto.getTen().trim());
        userHienTai.setPhongBanId(dto.getPhongBanId());
        // 5. Đưa xuống Repo để lưu lại
        User updated = userRepository.updateUser(userHienTai);
        return ApiResponse.success(mapToDto(updated), "Cập nhật thành công");
    }

    private UserDto mapToDto(User user) {
        PhongBan pb = phongBanRepository.getOnePhongBan(user.getPhongBanId());
        String tenPhongBan = (pb != null) ? pb.getTen() : "Không xác định";
        return UserDto.builder()
                .id(user.getId())
                .ten(user.getTen())
                .phongBanId(user.getPhongBanId())
                .tenPhongBan(tenPhongBan)
                .build();
    }
}
