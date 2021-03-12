package com.ctfcervice.authentication.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Users {

    public Users(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String mail;

    @NotBlank
    @Size(max = 120)
    private String password_hash;

    private boolean admin;

    public Users(String username, String email, String password) {
        this.username = username;
        this.mail = email;
        this.password_hash = password;
        this.admin = false;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public boolean isAdmin() {
        return admin;
    }
}
