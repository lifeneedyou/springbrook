package com.brook.datasource;

import com.google.common.collect.Maps;
import com.mongodb.MongoClientURI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xuequan
 * @Date: 2018/11/6 15:38
 * @Description:
 */
public class MongoDBTemplateRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Logger logger = LoggerFactory.getLogger(MongoDBTemplateRegister.class);

    //默认数据源
    private MongoTemplate defaultMongoTemplate;

    //用户自定义数据源
    private Map<DynamicMongoDBEnum, MongoTemplate> otherMongoTemplates = new HashMap<>();

    @Override
    public void setEnvironment(Environment environment) {
        initDefaultMongoTemplate(environment);
        initOtherMongoTemplates(environment);
    }

    /**
     * 初始化默认数据源
     *
     * @param env
     */
    private void initDefaultMongoTemplate(Environment env) {
        // 读取主数据源
        logger.debug("默认数据源：{}", DynamicMongoDBEnum.DEFAULT_SOURCE);
        String dynamicMongoDBEnum = DynamicMongoDBEnum.DEFAULT_SOURCE.getDbName();
        String uriStr = "spring.mongodb." + dynamicMongoDBEnum + ".uri";
        String uri = env.getProperty(uriStr);
        defaultMongoTemplate = buildMongoDB(uri);
    }

    /**
     * 初始化其他数据源
     *
     * @param env
     */
    private void initOtherMongoTemplates(Environment env) {
        // 读取配置文件获取更多数据源
//        String dsPrefixs = env.getProperty("spring.mongodb.names");
//        for (String dsPrefix : dsPrefixs.split(",")) {
//            String uri = env.getProperty("spring.mongodb." + dsPrefix + ".uri");
//            MongoTemplate ds = buildMongoDB(uri);
//            otherMongoTemplates.put(dsPrefix, ds);
//        }
        for (DynamicMongoDBEnum dynamicMongoDBEnum : DynamicMongoDBEnum.values()) {
            if (dynamicMongoDBEnum == DynamicMongoDBEnum.DEFAULT_SOURCE) {
                continue;
            }
            logger.debug("其他数据源：{}", dynamicMongoDBEnum);
            String uriStr = "spring.mongodb." + dynamicMongoDBEnum.getDbName() + ".uri";
            String uri = env.getProperty(uriStr);
            MongoTemplate ds = buildMongoDB(uri);
            otherMongoTemplates.put(dynamicMongoDBEnum, ds);
        }
    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map<Object, Object> targetMongoTemplate = Maps.newHashMap();
        //添加默认数据源
        DynamicMongoDBEnum defaultMonogoDBName = DynamicMongoDBEnum.DEFAULT_SOURCE;
        targetMongoTemplate.put(defaultMonogoDBName, this.defaultMongoTemplate);
        MongodbTemplateContextHolder.mongoDBIds.add(defaultMonogoDBName);
        //添加其他数据源
        targetMongoTemplate.putAll(otherMongoTemplates);
        for (DynamicMongoDBEnum key : otherMongoTemplates.keySet()) {
            MongodbTemplateContextHolder.mongoDBIds.add(key);
        }
        //创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(MongoDBTemplate.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetMongoTemplate", defaultMongoTemplate);
        mpv.addPropertyValue("targetMongoTemplates", targetMongoTemplate);
        //注册 - BeanDefinitionRegistry
        beanDefinitionRegistry.registerBeanDefinition("mongoDBTemplate", beanDefinition);

        logger.info("Dynamic MongoDB Registry");
    }

    /**
     * 创建数据源
     *
     * @param uri
     * @return
     */
    public MongoTemplate buildMongoDB(String uri) {
        MongoClientURI mongoClientURI = new MongoClientURI(uri);
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClientURI);
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
        return mongoTemplate;
    }

}
