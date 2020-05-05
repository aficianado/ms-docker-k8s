package com.db.ms.rest;


import com.db.ms.bean.UserDataBean;
import com.db.ms.bean.UserStatsBean;
import com.db.ms.exception.UserNotFoundException;
import com.db.ms.proxy.UserPostServiceProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableConfigurationProperties
public class UserStatsController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserPostServiceProxy proxy;

    private ObjectMapper mapper = new ObjectMapper();
    private Gson gson = new Gson();

    @GetMapping("/")
    public Map<String, String> defaultPage() {
        Map<String, String> data = new HashMap<>();
        data.put("message", "user-stats-service v1");
        log.info("defaultPage -> {}", data);
        return data;
    }

    @GetMapping("/class")
    public String test() {
        return this.getClass().getSimpleName();
    }

    @GetMapping("/stats-static")
    public UserStatsBean getStatsStatic() {
        log.info("getStatsStatic START");
        UserStatsBean usrStatsBeanResp = new UserStatsBean();

        ResponseEntity<List> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:9010/users",
                List.class);

        Type type = new TypeToken<List<UserDataBean>>() {
        }.getType();
        List<UserDataBean> userData = gson.fromJson(gson.toJson(responseEntity.getBody()), type);
        Map<Integer, Integer> stats = new HashMap<>();

        if (userData != null && !userData.isEmpty()) {
            usrStatsBeanResp.setUserData(userData);
            usrStatsBeanResp.setTotalUserCount(userData.size());
            userData.forEach(e -> {
                stats.put(e.getId(), e.getPosts() != null ? e.getPosts().size() : 0);
            });
            usrStatsBeanResp.setUserDataStats(stats);
        } else {
            throw new UserNotFoundException("Got Response but userData is empty");
        }

        log.info("getStatsStatic -> {}", gson.toJson(usrStatsBeanResp));
        return usrStatsBeanResp;
    }

    @GetMapping("/stats-dynamic")
    public UserStatsBean getStatsViaProxy() {
        log.info("getStatsViaProxy START");
        UserStatsBean usrStatsBeanResp = new UserStatsBean();

        List<?> resp = proxy.retrieveAllUsers();

        Type type = new TypeToken<List<UserDataBean>>() {
        }.getType();
        List<UserDataBean> userData = gson.fromJson(gson.toJson(resp), type);
        Map<Integer, Integer> stats = new HashMap<>();

        if (userData != null && !userData.isEmpty()) {
            usrStatsBeanResp.setUserData(userData);
            usrStatsBeanResp.setTotalUserCount(userData.size());
            userData.forEach(e -> {
                stats.put(e.getId(), e.getPosts() != null ? e.getPosts().size() : 0);
            });
            usrStatsBeanResp.setUserDataStats(stats);
        } else {
            throw new UserNotFoundException("Got Response but userData is empty");
        }

        log.info("getStatsViaProxy -> {}", gson.toJson(usrStatsBeanResp));
        log.info("getStatsViaProxy END");
        return usrStatsBeanResp;
    }

    //@GetMapping("/stats")
    @GetMapping("/user-post-service/stats")
    public UserStatsBean getStats() {
        log.info("getStats START");
        UserStatsBean usrStatsBeanResp = new UserStatsBean();

        List<?> resp = proxy.retrieveAllUsers();

        Type type = new TypeToken<List<UserDataBean>>() {
        }.getType();
        List<UserDataBean> userData = gson.fromJson(gson.toJson(resp), type);
        Map<Integer, Integer> stats = new HashMap<>();

        if (userData != null && !userData.isEmpty()) {
            usrStatsBeanResp.setUserData(userData);
            usrStatsBeanResp.setTotalUserCount(userData.size());
            userData.forEach(e -> {
                stats.put(e.getId(), e.getPosts() != null ? e.getPosts().size() : 0);
            });
            usrStatsBeanResp.setUserDataStats(stats);
        } else {
            throw new UserNotFoundException("Got Response but userData is empty");
        }

        log.info("getStats -> {}", gson.toJson(usrStatsBeanResp));
        log.info("getStats END");
        return usrStatsBeanResp;
    }


}
