package com.brook.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.brook.bean.VcUser;

import java.util.List;

@Component
public interface VcUserMapper {

    /**
     * 添加用户
     *
     * @param record
     * @return
     */
    int addUser(VcUser record);


    /**
     * 更新用户信息
     *
     * @param record
     * @return
     */
    int updateUserBase(VcUser record);

    /**
     * 根据邮箱查询用户信息
     *
     * @param userEmail
     * @return
     */
    VcUser getUserByUserEmail(@Param("userEmail") String userEmail);

    /**
     * 获取用户信息
     *
     * @param userKey
     * @return
     */
    VcUser getUser(String userKey);

    /**
     * 根据userKey获取用户信息
     *
     * @param userKeys
     * @return
     */
    List<VcUser> getUserListByUserKeys(@Param("userKeys") List<String> userKeys);

    /**
     * 设置用户密码
     *
     * @param userEmail
     * @param userPwd
     * @param userRealname
     * @return
     */
    int setUserByUserEmail(@Param("userEmail") String userEmail, @Param("userPwd") String userPwd, @Param("userRealname") String userRealname);

    /**
     * 更新用户密码
     *
     * @param userKey
     * @param userPwd
     * @return
     */
    int updateUserPassword(@Param("userKey") String userKey, @Param("userPwd") String userPwd);

    /**
     * 更新用户邮箱
     *
     * @param userKey
     * @param userEmail
     * @return
     */
    int updateUserEmail(@Param("userKey") String userKey, @Param("userEmail") String userEmail);

    /**
     * 更新用户状态
     *
     * @param userKey
     * @param userStatus
     * @return
     */
    int updateUserStatus(@Param("userKey") String userKey, @Param("userStatus") String userStatus);

}