package com.brook.datasource;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @Author: xuequan
 * @Date: 2018/11/6 15:35
 * @Description:
 */
public abstract class AbstractMongoDBRoutingMongoTemplate implements InitializingBean{

    private Map<Object, Object> targetMongoTemplates;

    private Object defaultTargetMongoTemplate;

    private Map<Object, MongoTemplate> resolvedMongoTemplates;

    private MongoTemplate resolvedDefaultMongoTemplate;

    @Override
    public void afterPropertiesSet() {
        if (this.targetMongoTemplates == null) {
            throw new IllegalArgumentException("Property 'targetMongoTemplates' is required");
        }
        this.resolvedMongoTemplates = Maps.newHashMap();
        for (Map.Entry<Object, Object> entry : this.targetMongoTemplates.entrySet()) {
            Object lookupKey = resolveSpecifiedLookupKey(entry.getKey());
            MongoTemplate mongoTemplate = resolveSpecifiedMongoTemplate(entry.getValue());
            this.resolvedMongoTemplates.put(lookupKey, mongoTemplate);
        }

        if (this.defaultTargetMongoTemplate != null) {
            this.resolvedDefaultMongoTemplate = resolveSpecifiedMongoTemplate(this.defaultTargetMongoTemplate);
        }
    }

    protected Object resolveSpecifiedLookupKey(Object lookupKey) {
        return lookupKey;
    }

    protected MongoTemplate resolveSpecifiedMongoTemplate(Object mongoTemplate) throws IllegalArgumentException {
        if (mongoTemplate instanceof MongoTemplate) {
            return (MongoTemplate) mongoTemplate;
        } else {
            throw new IllegalArgumentException(
                    "Illegal data source value - only [org.springframework.data.mongodb.core.MongoTemplate] and String supported: "
                            + mongoTemplate);
        }
    }

    protected MongoTemplate determineMongoTemplate() {
        Assert.notNull(this.resolvedMongoTemplates, "mongoTemplate router not initialized");
        Object lookupKey = determineCurrentLookupKey();
        MongoTemplate mongoTemplate = this.resolvedMongoTemplates.get(lookupKey);
        if (mongoTemplate == null && (lookupKey == null)) {
            mongoTemplate = this.resolvedDefaultMongoTemplate;
        }
        if (mongoTemplate == null) {
            throw new IllegalStateException("Cannot determine target MongoTemplate for lookup key [" + lookupKey + "]");
        }
        return mongoTemplate;
    }

    public Object getDefaultTargetMongoTemplate() {
        return defaultTargetMongoTemplate;
    }

    public void setDefaultTargetMongoTemplate(Object defaultTargetMongoTemplate) {
        this.defaultTargetMongoTemplate = defaultTargetMongoTemplate;
    }

    public Map<Object, Object> getTargetMongoTemplates() {
        return targetMongoTemplates;
    }

    public void setTargetMongoTemplates(Map<Object, Object> targetMongoTemplates) {
        this.targetMongoTemplates = targetMongoTemplates;
    }

    protected abstract Object determineCurrentLookupKey();
}