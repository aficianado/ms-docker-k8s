package com.db.ms.bean;

import java.util.Date;
import java.util.List;

public class UserDataBean {

    public UserDataBean() {
    }

    public UserDataBean(Integer id, String name, Date birthDate, List<UserPost> posts) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<UserPost> getPosts() {
        return posts;
    }

    public void setPosts(List<UserPost> posts) {
        this.posts = posts;
    }

    private Integer id;

    private String name;

    private Date birthDate;

    private List<UserPost> posts;
}
