package com.zap_dashboard.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zap_dashboard.Repository.UserRepository;
import com.zap_dashboard.entity.usersEntity;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean checkLogin(String empId, String password) {
        Optional<usersEntity> user = userRepository.findById(empId);
        return user.isPresent() && user.get().getPassword().equals(password);
    }
}