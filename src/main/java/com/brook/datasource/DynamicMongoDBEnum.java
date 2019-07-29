package com.brook.datasource;

/**
 * @Author: xuequan
 * @Date: 2018/11/6 20:00
 * @Description:
 */
public enum DynamicMongoDBEnum {

    LOG("vicrab_operate_log"),
    ANDROID("vicrab_android"),
    JAVA("vicrab_java"),
    PHP("vicrab_php");

    public static final DynamicMongoDBEnum DEFAULT_SOURCE = DynamicMongoDBEnum.LOG;

    private String dbName;

    DynamicMongoDBEnum(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }
}
