package com.example.ldaptest.controller;

import com.example.ldaptest.model.User;
import com.example.ldaptest.service.LdapService;
import org.springframework.web.bind.annotation.*;

@RestController
public class LdapController {

    private final LdapService ldapService;

    public LdapController(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @GetMapping("/all_person_name")
    public Object getAllPersonName(){
        return ldapService.getAllPersonNames();
    }

    @PostMapping("/user")
    public void create(@ModelAttribute User user){
        ldapService.create(user);

    }

    @PostMapping("/user/login")
    public boolean authentication(User user){
        return ldapService.authenticate(user);
    }
}
