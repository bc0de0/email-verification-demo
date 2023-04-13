package com.kunal.jwtoauth.repository;

import com.kunal.jwtoauth.registeration.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationTokenRepository
        extends JpaRepository<EmailVerificationToken, Long> {
}
