package com.adc.project_a.service.impl;

import com.adc.project_a.entity.User;
import com.adc.project_a.repository.UserRepository;
import com.adc.project_a.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User registerUser(String username, String email, String password, String fullName) throws IllegalArgumentException {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username '" + username + "' đã được sử dụng.");
        }

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email '" + email + "' đã được sử dụng.");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPasswordHash(password);
        newUser.setFullName(fullName);

        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> loginUser(String usernameOrEmail, String password) {
        Optional<User> userOptional = userRepository.findByUsername(usernameOrEmail);
        if (userOptional.isEmpty()) {
            userOptional = userRepository.findByEmail(usernameOrEmail);
        }

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (password.equals(user.getPasswordHash())) {
                if (user.getAccountStatus() == User.AccountStatus.ACTIVE || user.getAccountStatus() == User.AccountStatus.PENDING_VERIFICATION) {
                    return userOptional;
                }
            }
        }
        return Optional.empty();
    }
}
