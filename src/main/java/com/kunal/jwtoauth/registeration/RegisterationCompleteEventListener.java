package com.kunal.jwtoauth.registeration;

import com.kunal.jwtoauth.service.UserService;
import com.kunal.jwtoauth.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegisterationCompleteEventListener
        implements ApplicationListener<RegisterationCompleteEvent> {

    private final UserService userService;
    @Override
    public void onApplicationEvent(RegisterationCompleteEvent event) {
        //1. get the newly created user
        User createdUser = event.getUser();
        //2 Crete a verification token for the user
        String verificationToken = UUID.randomUUID().toString();
        //3.Save the verification token for the user
        userService.saveUserVerificationToker(createdUser, verificationToken);
        //4.Build the verification url to be sent to the user
        String url = event.getRegisterationCompleteUrl()+
                "/register/verifyEmail?token"+verificationToken;
        //5. send the email
        log.info("click the link to complete registeration: {}"+ url);
    }
}
