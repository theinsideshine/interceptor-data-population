package com.theinsideshine.models;

public class UserFullDTO {
    private Long id;
    private String name;
    private String email;
    private UserInfo userInfo;

    public UserFullDTO() {
    }

    public UserFullDTO(Long id, String name, String email, UserInfo userInfo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userInfo = userInfo;
    }

    public UserFullDTO(User user, UserInfo userInfo) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.userInfo = userInfo;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
