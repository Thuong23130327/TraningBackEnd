package com.example.QuanLySinhVien.repository;

import com.example.QuanLySinhVien.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();
    private int currentId = 1;


    public List<User> getUsers() {
        return users;
    }

    public User getUser(int id) {
        for (User u : users)
            if (u.getId() == id) return u;
        return null;
    }

    public User addUser(User newUser) {
        newUser.setId(currentId);
        currentId++;
        users.add(newUser);
        return newUser;
    }

    public boolean deleteUser(int id) {
        return users.removeIf(u -> u.getId() == id);
    }

    public User updateUser( User userMoi) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userMoi.getId()) {
                users.set(i, userMoi);
                return userMoi;
            }
        }
        return null;

    }




}
