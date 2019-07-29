package com.brook.mongo.dao;


import com.brook.mongo.LogSystemOperateEntity;

/**
 * @Author: xuequan
 * @Date: 2019/6/25 22:21
 * @Description:
 */
public interface LogSystemOperateDao {

    /**
     * 添加系统日志
     *
     * @param logSystemOperateEntity
     * @return
     */
    boolean addSystemOperateLog(LogSystemOperateEntity logSystemOperateEntity);
}
