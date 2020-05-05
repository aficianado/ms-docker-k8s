package com.db.ms.proxy;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@FeignClient(name = "user-post-service", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:9010")
//@FeignClient(name = "user-post-service")
//@FeignClient(name = "zuul-api-gateway-server") //zuul api server
//@RibbonClient(name = "user-post-service")
public interface UserPostServiceProxy {

    //@GetMapping("/users")
    @GetMapping("/user-post-service/users")
    public List<?> retrieveAllUsers();

}
