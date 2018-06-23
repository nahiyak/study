package com.example.ldaptest.controller;

import com.example.ldaptest.LdapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
