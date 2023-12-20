package com.example.db_project.model;

import com.example.db_project.entity.UserEntity;

public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private String nickname;
    private String email;

    public static User toModel(UserEntity entity){
        User model = new User();
        model.setId(entity.getId());
        model.setFirstname(entity.getFirstname());
        model.setLastname(entity.getLastname());
        model.setNickname(entity.getNickname());
        model.setEmail(entity.getEmail());
        return model;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
