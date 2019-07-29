package com.brook.datasource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: xuequan
 * @Date: 2018/11/9 11:41
 * @Description:
 */
@Component
public class DynamicMongoDBControl {

    private static final Logger logger = LoggerFactory.getLogger(DynamicMongoDBControl.class);

//    @Autowired
//    private VcProjectMapper vcProjectMapper;


    public DynamicMongoDBEnum getMongoDBEnumByProjectKey(String projectKey) {
        if (StringUtils.isBlank(projectKey)) {
            return DynamicMongoDBEnum.DEFAULT_SOURCE;
        }
       return null;
    }

    private DynamicMongoDBEnum selectMongoDB(String projectPlatform) {
        DynamicMongoDBEnum dynamicMongoDBEnum = DynamicMongoDBEnum.DEFAULT_SOURCE;
        if (StringUtils.isBlank(projectPlatform)) {
            return dynamicMongoDBEnum;
        }
        projectPlatform = projectPlatform.toLowerCase();
        logger.info("使用的项目平台：{}", projectPlatform);
        switch (projectPlatform) {
            case "java":
                dynamicMongoDBEnum = DynamicMongoDBEnum.JAVA;
                break;
            case "android":
                dynamicMongoDBEnum = DynamicMongoDBEnum.ANDROID;
                break;
            case "php":
                dynamicMongoDBEnum = DynamicMongoDBEnum.ANDROID;
                break;
            default:
                logger.error("未存在项目平台，使用默认数据源");
                break;
        }
        return dynamicMongoDBEnum;
    }

}
