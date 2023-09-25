package com.project.Game.list.dto.user;

public class UserRequestDTO {

    private String name;
    private String Login;
    private String password;

    public UserRequestDTO(String name, String login, String password) {
        this.name = name;
        Login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return Login;
    }

    public String getPassword() {
        return password;
    }
}
