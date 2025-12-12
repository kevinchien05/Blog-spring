package com.example.blog.event;

import org.springframework.context.ApplicationEvent;

import com.example.blog.model.User;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final User user;

    public OnRegistrationCompleteEvent(User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
