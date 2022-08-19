package ru.learningApp.CRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.learningApp.CRUD.service.MailService;

@RestController
@RequestMapping(value = "/rest")
public class MailRestController {

    private MailService mailService;

    @Autowired
    public MailRestController(MailService MailService) {
        this.mailService = MailService;
    }

    @PostMapping("/sendMail/{mail}")
    public ResponseEntity<String> sendEmailToUser(@PathVariable String mail, @RequestBody String msg) {
        return ResponseEntity.ok(mailService.sendEmail(mail, msg));
    }
}
