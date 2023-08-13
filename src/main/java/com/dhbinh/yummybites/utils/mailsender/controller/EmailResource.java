package com.dhbinh.yummybites.utils.mailsender.controller;

import com.dhbinh.yummybites.utils.mailsender.entity.EmailDetail;
import com.dhbinh.yummybites.utils.mailsender.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('OWNER')")
@RequestMapping(value = "/email")
public class EmailResource {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendMail(@RequestBody EmailDetail details) {

        return emailService.sendSimpleMail(details);
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetail details) {
        return emailService.sendMailWithAttachment(details);
    }
}
