package com.brook.datasource;

import com.brook.utils.RequestUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: xuequan
 * @Date: 2018/11/6 16:57
 * @Description:
 */
@Aspect
@Order(-1)//保证在@Transactional之前执行
@Component
public class DynamicMongoDBAspect {

    private Logger logger = LoggerFactory.getLogger(DynamicMongoDBAspect.class);

    @Autowired
    private DynamicMongoDBControl dynamicMongoDBControl;

    private Long startTime;

    @Pointcut("execution(* com.brook.mongo.dao.*.*(..))")
    public void mongoAop() {
    }

    /**
     * 改变数据源
     *
     * @param joinPoint
     */
    @Before("mongoAop()")
    public void changeMongodb(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] paramNames = methodSignature.getParameterNames();
        Object[] methodArgs = joinPoint.getArgs();
        //获取参数名称和值
        Map<String, Object> params = RequestUtils.getRequestParams(paramNames, methodArgs);

        DynamicMongoDBEnum dynamicDataSourceEnum = DynamicMongoDBEnum.DEFAULT_SOURCE;
        if (MapUtils.isNotEmpty(params)) {
            Object value = params.get("projectKey");
            String projectKey = "";
            if (value != null) {
                projectKey = value.toString();
            }
            if (StringUtils.isNotBlank(projectKey)) {
                dynamicDataSourceEnum = dynamicMongoDBControl.getMongoDBEnumByProjectKey(projectKey);
            }

            if (dynamicDataSourceEnum == null) {
                logger.info("projectKey:{},设置的语音不存在数据源", projectKey);
            }
        }
        if (!MongodbTemplateContextHolder.isContainsMongoDBTemplate(dynamicDataSourceEnum)) {
            //joinPoint.getSignature() ：获取连接点的方法签名对象
            logger.error("MongoDB: {} is not exists -> {} ", dynamicDataSourceEnum, joinPoint.getSignature());
        } else {
            logger.debug("use MongoDB：{}", dynamicDataSourceEnum);
            MongodbTemplateContextHolder.setMongoDBTemplate(dynamicDataSourceEnum);
        }
        startTime = System.currentTimeMillis();
    }

    /**
     * 清除数据源
     *
     * @param joinPoint
     */
    @After("mongoAop()")
    public void clearMongodb(JoinPoint joinPoint) {
        Long endTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        logger.debug("------->>>> 方法：{},开始时间：{},结束时间：{},使用时间：{}", methodName, startTime, endTime, (endTime - startTime) / 1000 + "s");
        logger.debug("clear mongoDB：{}", MongodbTemplateContextHolder.getMongoDBTemplate());
        MongodbTemplateContextHolder.clearMongoDBTemplate();
    }

}
