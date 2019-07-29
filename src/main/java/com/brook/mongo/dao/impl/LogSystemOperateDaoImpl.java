package com.brook.mongo.dao.impl;

import com.brook.datasource.MongoDBTemplate;
import com.brook.mongo.LogSystemOperateEntity;
import com.brook.mongo.dao.LogSystemOperateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: xuequan
 * @Date: 2019/6/26 09:56
 * @Description:
 */
@Component
public class LogSystemOperateDaoImpl implements LogSystemOperateDao {

    @Autowired
    private MongoDBTemplate mongoDBTemplate;


    @Override
    public boolean addSystemOperateLog(LogSystemOperateEntity logSystemOperateEntity) {
        MongoTemplate mongoTemplate = mongoDBTemplate.getMongoTemplate();
        String collectionName = "log_system_operate";
        mongoTemplate.insert(logSystemOperateEntity, collectionName);
        return true;
    }

}
