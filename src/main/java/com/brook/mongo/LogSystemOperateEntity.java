package com.brook.mongo;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: xuequan
 * @Date: 2019/6/25 20:04
 * @Description:
 */
public class LogSystemOperateEntity implements Serializable {
    private String id;
    private String env;
    private String user_key;
    private String token;
    private String organize_key;
    private String request_url;
    private String request_query;
    private String request_method;
    private String request_method_name;
    private Map<String, String> request_header;
    private Map<String, Object> requeest_params;
    private String request_resutl;
    private String ip;
    private Long request_duration; // 请求时长，单位毫秒
    private String operate_time; // 操作时间 YYYY-mm-dd HH:mm:ss

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrganize_key() {
        return organize_key;
    }

    public void setOrganize_key(String organize_key) {
        this.organize_key = organize_key;
    }

    public String getRequest_url() {
        return request_url;
    }

    public void setRequest_url(String request_url) {
        this.request_url = request_url;
    }

    public String getRequest_query() {
        return request_query;
    }

    public void setRequest_query(String request_query) {
        this.request_query = request_query;
    }

    public String getRequest_method() {
        return request_method;
    }

    public void setRequest_method(String request_method) {
        this.request_method = request_method;
    }

    public String getRequest_method_name() {
        return request_method_name;
    }

    public void setRequest_method_name(String request_method_name) {
        this.request_method_name = request_method_name;
    }

    public Map<String, String> getRequest_header() {
        return request_header;
    }

    public void setRequest_header(Map<String, String> request_header) {
        this.request_header = request_header;
    }

    public Map<String, Object> getRequeest_params() {
        return requeest_params;
    }

    public void setRequeest_params(Map<String, Object> requeest_params) {
        this.requeest_params = requeest_params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getRequest_duration() {
        return request_duration;
    }

    public void setRequest_duration(Long request_duration) {
        this.request_duration = request_duration;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }

    public String getRequest_resutl() {
        return request_resutl;
    }

    public void setRequest_resutl(String request_resutl) {
        this.request_resutl = request_resutl;
    }
}
