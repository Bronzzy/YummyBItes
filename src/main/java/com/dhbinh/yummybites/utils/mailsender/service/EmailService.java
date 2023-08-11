package com.dhbinh.yummybites.utils.mailsender.service;

import com.dhbinh.yummybites.utils.mailsender.entity.EmailDetail;

public interface EmailService {

    String sendSimpleMail(EmailDetail details);

    String sendMailWithAttachment(EmailDetail details);
}
