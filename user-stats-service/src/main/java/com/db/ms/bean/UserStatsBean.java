package com.db.ms.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Map;

public class UserStatsBean {

    @JsonIgnore
    private List<UserDataBean> userData;
    private Map<?, ?> userDataStats;
    private int totalUserCount;

    public UserStatsBean(List<UserDataBean> userData, Map<String, String> userDataStats) {
        this.userData = userData;
        this.userDataStats = userDataStats;
    }

    public UserStatsBean() {
    }

    public int getTotalUserCount() {
        return totalUserCount;
    }

    public void setTotalUserCount(int totalUserCount) {
        this.totalUserCount = totalUserCount;
    }

    public Map<?, ?> getUserDataStats() {
        return userDataStats;
    }

    public void setUserDataStats(Map<?, ?> userDataStats) {
        this.userDataStats = userDataStats;
    }

    public List<UserDataBean> getUserData() {
        return userData;
    }

    public void setUserData(List<UserDataBean> userData) {
        this.userData = userData;
    }
}
