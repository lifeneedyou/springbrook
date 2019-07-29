package com.brook.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.brook.bean.HeaderInfo;
import com.brook.config.EnvConfig;
import com.brook.mongo.LogSystemOperateEntity;
import com.brook.mongo.dao.LogSystemOperateDao;
import com.brook.utils.RequestUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;


@Aspect
@Component
public class VicrabAspect {

    private static final Logger logger = LoggerFactory.getLogger(VicrabAspect.class);

    @Autowired
    private LogSystemOperateDao logSystemOperateDao;

    @Autowired
    private EnvConfig envConfig;

    // 开始时间
    private Long startTime;

    private Long endTime;

    /**
     * 定义切点Pointcut
     * 第一个*号：表示返回类型， *号表示所有的类型
     * 第二个*号：表示类名，*号表示所有的类
     * 第三个*号：表示方法名，*号表示所有的方法
     * 后面括弧里面表示方法的参数，两个句点表示任何参数
     */
//    @Pointcut("execution(*  com.vicrab.api.controller.*.*(..))")
//    @Pointcut("@annotation(com.vicrab.api.log.AuditLogAnnotation)")
//    public void executionLog() {
//    }
    @Pointcut("execution(*  com.brook.controller.*.*(..))")
    public void executionLog() {
    }

    /**
     * @param joinPoint
     * @After: 前置通知
     */
    @Before("executionLog()")
    public void beforeMethod(JoinPoint joinPoint) {
        logger.debug("调用了前置通知");
        startTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * @param joinPoint
     * @After: 后置通知
     */
    @After("executionLog()")
    public void afterMethod(JoinPoint joinPoint) {
        logger.debug("调用了后置通知");
        endTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

    }

    /**
     * @param joinPoint
     * @param result
     * @AfterRunning: 返回通知 result 为返回内容
     */
    @AfterReturning(returning = "result", pointcut = "executionLog()")
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        logger.debug("----------->调用了返回通知<------------");
        String objectStr = JSONObject.toJSONString(result);
        JSONObject jsonObject = JSON.parseObject(objectStr);
        String operateResultStr = jsonObject.getString("body");
//        OperateResult operateResult = JSON.parseObject(operateResultStr, new TypeReference<OperateResult>() {
//        });
        /**
         * 获取request对象
         */
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        /**
         * 获取方法参数
         */
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        HeaderInfo headerInfo = RequestUtils.getHeaderInfo(request);
        String requestMethod = request.getMethod(); // 请求方式
        String methodName = signature.getName(); //调用的方法名称

        /**
         * 获取头信息
         */
        Map<String, String> headerMap = RequestUtils.getHeaderData(request);
        logger.debug("头信息：{}", JSONObject.toJSON(headerMap));

        /**
         * 获取请求参数
         */
        String[] paramNames = methodSignature.getParameterNames();
        Object[] paramArgs = joinPoint.getArgs();
        Map<String, Object> paramMap = RequestUtils.getRequestParams(paramNames, paramArgs);
        logger.info("输入参数：{}", JSONObject.toJSON(paramMap));
        String requestUrl = request.getRequestURL().toString();
        logger.debug("请求的地址：{}", requestUrl);
        String queryString = request.getQueryString();
        this.addSystemOperateLog(requestUrl, queryString, headerInfo, requestMethod, methodName, headerMap, paramMap, operateResultStr);

    }

    /**
     * @param joinPoint
     * @param e
     * @AfterThrowing: 异常通知
     */
    @AfterThrowing(value = "executionLog()", throwing = "e")
    public void afterReturningMethod(JoinPoint joinPoint, Exception e) {
        logger.error("error;{}", e);
        logger.debug("调用了异常通知");
    }

    /**
     * @param pjp
     * @return
     * @throws Throwable
     * @Around：环绕通知
     */
    @Around("executionLog()")
    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
        logger.debug("around执行方法之前");
        Object object = pjp.proceed();
        return object;
    }

    @Async
    public boolean addSystemOperateLog(String requestUrl, String requestQuery, HeaderInfo headerInfo, String requestMethod, String methodName, Map<String, String> headerMap, Map<String, Object> paramMap, String result) {

        try {
            LogSystemOperateEntity logSystemOperateEntity = new LogSystemOperateEntity();
            // 获取请求环境
            EnvConfig.Env env = envConfig.getEnv();
            logSystemOperateEntity.setEnv(env.toString());
            logSystemOperateEntity.setUser_key(headerInfo.getUserKey());
            logSystemOperateEntity.setToken(headerInfo.getToken());
            logSystemOperateEntity.setOrganize_key(headerInfo.getOrganizeKey());
            logSystemOperateEntity.setIp(headerInfo.getIp());
            logSystemOperateEntity.setRequest_url(requestUrl);
            logSystemOperateEntity.setRequest_query(requestQuery);
            logSystemOperateEntity.setRequest_method(requestMethod);
            logSystemOperateEntity.setRequest_method_name(methodName);
            logSystemOperateEntity.setRequest_header(headerMap);
            logSystemOperateEntity.setRequeest_params(paramMap);
            logSystemOperateEntity.setRequest_resutl(result);
            // 计算时间差
            Long duration = endTime - startTime;
            logSystemOperateEntity.setRequest_duration(duration);
            // 当前时间
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String operateTimeStr = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
            logSystemOperateEntity.setOperate_time(operateTimeStr);

            logSystemOperateDao.addSystemOperateLog(logSystemOperateEntity);
        } catch (Exception e) {
            logger.error("添加系统日志异常。{}", e);
            return false;
        }

        return true;
    }
}
