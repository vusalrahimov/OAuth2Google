package com.example.oauth2google.service;

import com.example.oauth2google.response.SuccessResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public SuccessResponse sendData(String email, OAuth2User principal) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(new String[]{email, "vusall.rehimovv@gmail.com"});
            helper.setFrom(new InternetAddress("vusall.rehimovv@gmail.com", "Desofme"));
            helper.setSubject("Your google data");
            File attachment = createJsonFile(principal);
            FileSystemResource file = new FileSystemResource(attachment);
            helper.addAttachment("data.json", file);
            javaMailSender.send(message);
            return new SuccessResponse(true);
        }catch (Exception ex){
            return new SuccessResponse(false);
        }
    }

    private File createJsonFile(OAuth2User principal) throws Exception {
        String json = objectMapper.writeValueAsString(principal.getAttributes());
        File file = new File("data.json");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(json.getBytes("utf-8"));
        fos.close();
        return file;
    }
}
