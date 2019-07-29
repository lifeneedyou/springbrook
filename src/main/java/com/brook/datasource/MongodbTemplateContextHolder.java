package com.brook.datasource;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author: xuequan
 * @Date: 2018/11/6 15:37
 * @Description:
 */
@Component
public class MongodbTemplateContextHolder {

    private static final ThreadLocal<DynamicMongoDBEnum> contextHolder = new ThreadLocal<>();


    //存放数据源id
    public static List<DynamicMongoDBEnum> mongoDBIds = Lists.newArrayList();

    /**
     * 设置当前使用的数据库
     *
     * @param mongodbTemplateType 对应xml配置targetMongoTemplates的key
     * @see AbstractMongoDBRoutingMongoTemplate#setTargetMongoTemplates(Map)
     */
    public static void setMongoDBTemplate(DynamicMongoDBEnum mongodbTemplateType) {
        if (mongodbTemplateType == null) {
            throw new NullPointerException("mongodbTemplateType is null");
        }
        contextHolder.set(mongodbTemplateType);
    }

    public static DynamicMongoDBEnum getMongoDBTemplate() {
        return contextHolder.get();
    }

    /**
     * 当一次完整调用结束后,需要清除当前线程上下文.
     */
    public static void clearMongoDBTemplate() {
        contextHolder.remove();
    }

    //判断当前数据源是否存在
    public static boolean isContainsMongoDBTemplate(DynamicMongoDBEnum dataSourceId) {
        return mongoDBIds.contains(dataSourceId);
    }

}