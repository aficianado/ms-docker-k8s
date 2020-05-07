package com.db.ms.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user-post-service", url = "${USER_POST_SERVICE_URL}")
public interface UserPostServiceProxy {

    @GetMapping("/users")
    public List<?> retrieveAllUsers();

}