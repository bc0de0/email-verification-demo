package com.kunal.jwtoauth.service;

import com.kunal.jwtoauth.registeration.RegisterationRequest;
import com.kunal.jwtoauth.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User registerUser(RegisterationRequest request);

    Optional<User> findByEmail(String email);

    void saveUserVerificationToker(User createdUser, String verificationToken);
}
