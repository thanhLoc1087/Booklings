package com.loc.notification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.loc.notification.dto.request.EmailRequest;
import com.loc.notification.dto.request.EmailSender;
import com.loc.notification.dto.request.SendEmailRequest;
import com.loc.notification.dto.response.EmailResponse;
import com.loc.notification.exception.AppException;
import com.loc.notification.exception.ErrorCode;
import com.loc.notification.repository.httpclient.EmailClient;

import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    EmailClient emailClient;

    @NonFinal
    @Value("${notification.email.brevo-apikey}")
    String apiKey;

    public EmailResponse sendEmail(SendEmailRequest request) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(EmailSender.builder()
                        .name("ThanhLoc")
                        .email("21521087@gm.uit.edu.vn")
                        .build())
                .to(List.of(request.getTo()))
                .subject(request.getSubject())
                .htmlContent(request.getHtmlContent())
                .build();
        try {
            return emailClient.sendEmail(apiKey, emailRequest);
        } catch (FeignException e) {
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }
}
