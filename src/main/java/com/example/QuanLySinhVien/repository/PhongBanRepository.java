package com.example.QuanLySinhVien.repository;

import com.example.QuanLySinhVien.model.PhongBan;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PhongBanRepository {
    private int currentId = 1;
    private List<PhongBan> listPhongBan = new ArrayList<>();

    public List<PhongBan> getPhongBans() {
        return listPhongBan;
    }

    public PhongBan getOnePhongBan(int id) {
        for (PhongBan p : listPhongBan) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public PhongBan addPhongBan(PhongBan newPhongBan) {
        newPhongBan.setId(currentId++);
        listPhongBan.add(newPhongBan);
        return newPhongBan;
    }
    public boolean deletePhongBan(int id) {
        return listPhongBan.removeIf(p -> p.getId() == id);
    }
   public PhongBan updatePhongBan(PhongBan newPhongBan) {
        for (int i = 0; i < listPhongBan.size(); i++) {
            if (listPhongBan.get(i).getId() == newPhongBan.getId()) {
                listPhongBan.set(i, newPhongBan);
                return newPhongBan;
            }
        }
        return null;
    }
}
