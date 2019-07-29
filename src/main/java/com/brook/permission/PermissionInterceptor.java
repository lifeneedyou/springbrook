package com.brook.permission;

import com.brook.exception.PermissionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xuequan
 * @Date: 2019/5/7 14:57
 * @Description:
 */
@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 验证权限
        if (this.hasPermission(request, handler)) {
            return true;
        } else {
            throw new PermissionException("无权限");
        }
    }


    /**
     * 是否有权限
     *
     * @param request
     * @param handler
     * @return
     */
    private boolean hasPermission(HttpServletRequest request, Object handler) {

        String userKey = request.getHeader("user_key");
        String organizeKey = request.getHeader("organize_key");
        logger.debug("userKey:{},organizeKey:{}", userKey, organizeKey);

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

        }
        return true;
    }


}
