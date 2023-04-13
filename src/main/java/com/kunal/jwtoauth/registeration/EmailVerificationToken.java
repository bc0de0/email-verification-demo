package com.kunal.jwtoauth.registeration;

import com.kunal.jwtoauth.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmailVerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expiry;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private static final int EXPIRATIONTIME = 10;
    public EmailVerificationToken(String token, User user) {
        super();
        this.token = token;
        this.user = user;
        this.expiry = this.getTokenExpirationTime();
    }

    public EmailVerificationToken(String token) {
        super();
        this.token = token;
        this.expiry = this.getTokenExpirationTime();
    }

    public Date getTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATIONTIME);
        return new Date(calendar.getTime().getTime());
    }
}
