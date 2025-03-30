package com.adc.project_a.service;

import com.adc.project_a.entity.User;
import java.util.Optional;

public interface UserService {
    User registerUser(String username, String email, String password, String fullName) throws IllegalArgumentException;
    Optional<User> loginUser(String username, String password);
}
