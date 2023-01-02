package com.example.demo.controller;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.example.demo.entity.PaymentSuccess;



@RestController
@RequestMapping("/sendEmail")
@CrossOrigin(origins="http://localhost:4200")
public class PaymentSuccessController {

 

    @Autowired
    SpringTemplateEngine templateEngine;

 

    @Autowired
    private JavaMailSender sender;

    @RequestMapping("/getdetails")
    public @ResponseBody PaymentSuccess sendMail(@RequestBody PaymentSuccess emailNotification) throws Exception {

 

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

 

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("email",emailNotification.getEmail());

        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("email-template",context);

        try {
            helper.setTo(emailNotification.getEmail());

            helper.setText(html,true);
            helper.setSubject("Welcome to Udaan family");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);

 

        return emailNotification;

 

    }

    }