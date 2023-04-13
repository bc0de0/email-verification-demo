package com.kunal.jwtoauth.controller;

import com.kunal.jwtoauth.registeration.RegisterationCompleteEvent;
import com.kunal.jwtoauth.registeration.RegisterationRequest;
import com.kunal.jwtoauth.service.UserService;
import com.kunal.jwtoauth.service.UserServiceImpl;
import com.kunal.jwtoauth.user.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterationRequest registerationRequest, final HttpServletRequest request){
        User user = userService.registerUser(registerationRequest);
        publisher.publishEvent(new RegisterationCompleteEvent(user, applicationUrl(request)));
        return "User created successfully, please check email to complete registeration";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
