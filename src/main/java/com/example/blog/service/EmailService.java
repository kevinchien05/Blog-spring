package com.example.blog.service;

public interface EmailService {

    public void sendEmail(String to, String subject, String text);
}
