package com.example.demo.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Users implements Serializable {

    @Id
    private String id;

    private String username;

    private String password;

    private Boolean delete = Boolean.FALSE;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
}
