package com.brook.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.brook.bean.HeaderInfo;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * @Author: xuequan
 * @Date: 2019/6/14 21:05
 * @Description:
 */
public class RequestUtils {

    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    /**
     * 获取自定义的头信息
     *
     * @param request
     * @return
     */
    public static HeaderInfo getHeaderInfo(HttpServletRequest request) {
        HeaderInfo headerInfo = new HeaderInfo();

        // 获取组织编号
        String organizeKey = request.getHeader("organize_key");
        // 获取用户编号
        String userKey = request.getHeader("user_key");
        // 获取token 信息
        String token = request.getHeader("token");
        // 获取ip 信息
        String ip = IPUtils.getIpAddr(request);

        headerInfo.setOrganizeKey(organizeKey == null ? "" : organizeKey);
        headerInfo.setUserKey(userKey == null ? "" : userKey);
        headerInfo.setToken(token);
        headerInfo.setIp(ip);

        logger.debug("headerInfo->{}", headerInfo.toString());

        return headerInfo;
    }

    /**
     * 获取详细请求头
     *
     * @param request
     * @return
     */
    public static Map<String, String> getHeaderData(HttpServletRequest request) {
        Map<String, String> headerMap = Maps.newHashMap();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            headerMap.put(key, value);
        }
        return headerMap;
    }

    /**
     * 打印方法参数值  基本类型直接打印，非基本类型需要重写toString方法
     *
     * @param paramsArgsName  方法参数名数组
     * @param paramsArgsValue 方法参数值数组
     */
    public static Map<String, String> getRequestAllParams(String[] paramsArgsName, Object[] paramsArgsValue) {
        Map<String, String> map = Maps.newHashMap();
        if (ArrayUtils.isEmpty(paramsArgsName) || ArrayUtils.isEmpty(paramsArgsValue)) {
            return map;
        }
        for (int i = 0; i < paramsArgsName.length; i++) {
            //参数名
            String name = paramsArgsName[i];
            //参数值
            Object value = paramsArgsValue[i];

            if (value == null) {
                map.put(name, "");
                continue;
            }

            if (!(value instanceof String) && !(value instanceof Integer)) {
                String jsonStr = JSONObject.toJSONString(value);
                Map maps = (Map) JSON.parse(jsonStr);
                map.putAll(maps);
            } else {
                map.put(name, value.toString());
            }

        }
        return map;
    }

    /**
     * 打印方法参数值  基本类型直接打印，非基本类型需要重写toString方法
     *
     * @param paramsArgsName  方法参数名数组
     * @param paramsArgsValue 方法参数值数组
     */
    public static Map<String, Object> getRequestParams(String[] paramsArgsName, Object[] paramsArgsValue) {
        Map<String, Object> map = Maps.newHashMap();
        if (ArrayUtils.isEmpty(paramsArgsName) || ArrayUtils.isEmpty(paramsArgsValue)) {
            return map;
        }
        for (int i = 0; i < paramsArgsName.length; i++) {
            //参数名
            String name = paramsArgsName[i];
            //参数值
            Object value = paramsArgsValue[i];
            map.put(name, value);
        }
        return map;
    }
}
