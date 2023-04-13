package com.kunal.jwtoauth.registeration;

import com.kunal.jwtoauth.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class RegisterationCompleteEvent extends ApplicationEvent {

    private User user;
    private String registerationCompleteUrl;

    public RegisterationCompleteEvent(User user, String registerationCompleteUrl) {
        super(user);
        this.user = user;
        this.registerationCompleteUrl = registerationCompleteUrl;
    }
}
