package com.theinsideshine.models;

public class UserBasicDTO {

    private Long id;
    private String name;
    private String email;
    private UserInfoBasicDTO userInfoBasicDTO;

    public UserBasicDTO() {
    }

    public UserBasicDTO(UserInfoBasicDTO userInfoBasicDTO, String email, String name, Long id) {
        this.userInfoBasicDTO = userInfoBasicDTO;
        this.email = email;
        this.name = name;
        this.id = id;
    }

    public UserBasicDTO(User user, UserInfo userInfo) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.userInfoBasicDTO = new UserInfoBasicDTO(userInfo);
    }

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

    public UserInfoBasicDTO getUserInfoBasicDTO() {
        return userInfoBasicDTO;
    }

    public void setUserInfoBasicDTO(UserInfoBasicDTO userInfoBasicDTO) {
        this.userInfoBasicDTO = userInfoBasicDTO;
    }
}
