package com.brook.bean;


public class HeaderInfo {
    private String organizeKey;
    private String userKey;
    private String token;
    private String ip;

    public String getOrganizeKey() {
        return organizeKey;
    }

    public void setOrganizeKey(String organizeKey) {
        this.organizeKey = organizeKey;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    @Override
    public String toString() {
        return "HeaderInfo{" +
                "organizeKey='" + organizeKey + '\'' +
                ", userKey='" + userKey + '\'' +
                ", token='" + token + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
