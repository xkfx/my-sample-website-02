package org.sample.website.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

    private Long id;
    private String username;
    @JsonIgnore
    private String pwd;

    public User() {
    }

    public User(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public User(Long id, String username, String pwd) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
