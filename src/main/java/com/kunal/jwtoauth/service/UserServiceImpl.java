package com.kunal.jwtoauth.service;

import com.kunal.jwtoauth.exceptions.UserAlreadyExistsException;
import com.kunal.jwtoauth.registeration.EmailVerificationToken;
import com.kunal.jwtoauth.registeration.RegisterationRequest;
import com.kunal.jwtoauth.repository.EmailVerificationTokenRepository;
import com.kunal.jwtoauth.repository.UserRepository;
import com.kunal.jwtoauth.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerificationTokenRepository tokenRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(RegisterationRequest request) {
        Optional<User> user = this.findByEmail(request.email());
        if(user.isPresent()){
            throw new UserAlreadyExistsException("Email " + request.email() + " is already registered");
        }
        var newUser = new User();
        newUser.setName(request.name());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(request.role());
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUserVerificationToker(User createdUser, String verificationToken) {
        var token = new EmailVerificationToken(verificationToken, createdUser);
        tokenRepository.save(token);
    }
}
