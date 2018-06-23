package com.example.ldaptest.model;

import lombok.Data;

@Data
public class User {
    private String cn;
    private String sn;
    private String uid;
    private String userPassword;
}
