package com.db.ms.com.db.ms.rest;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("/")
    public String defaultResp() {
        return "{\"message\":\"MS-DOCKER-K8s-TUTORIAL V1\"}";
    }
}
