package com.brook.datasource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: xuequan
 * @Date: 2018/11/6 15:36
 * @Description:
 */
@Component
public class MongoDBTemplate extends AbstractMongoDBRoutingMongoTemplate {

    public MongoDBTemplate() {
    }

    public MongoTemplate getMongoTemplate() {
        return determineMongoTemplate();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return MongodbTemplateContextHolder.getMongoDBTemplate();
    }

}