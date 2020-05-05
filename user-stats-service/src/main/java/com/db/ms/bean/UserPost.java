package com.db.ms.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPost {

    private Integer id;
    private String description;
    @JsonIgnore
    private UserDataBean employee;

    public UserPost(Integer id, String description, UserDataBean employee) {
        this.id = id;
        this.description = description;
        this.employee = employee;
    }

    public UserPost() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDataBean getEmployee() {
        return employee;
    }

    public void setEmployee(UserDataBean employee) {
        this.employee = employee;
    }
}
